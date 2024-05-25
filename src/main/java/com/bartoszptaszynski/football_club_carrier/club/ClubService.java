package com.bartoszptaszynski.football_club_carrier.club;

import com.bartoszptaszynski.football_club_carrier.club.Exception.ClubNotFoundException;
import com.bartoszptaszynski.football_club_carrier.club.command.ClubCommand;
import com.bartoszptaszynski.football_club_carrier.club.model.ClubInformationDto;
import com.bartoszptaszynski.football_club_carrier.club.model.entity.Club;
import com.bartoszptaszynski.football_club_carrier.club.model.entity.ClubFootballers;
import com.bartoszptaszynski.football_club_carrier.club.model.entity.Match;
import com.bartoszptaszynski.football_club_carrier.club.repository.ClubFootballersRepository;
import com.bartoszptaszynski.football_club_carrier.club.repository.ClubRepository;
import com.bartoszptaszynski.football_club_carrier.club.repository.MatchRepository;
import com.bartoszptaszynski.football_club_carrier.footballer.FootballerNotFoundException;
import com.bartoszptaszynski.football_club_carrier.footballer.model.FootballerClubDto;
import com.bartoszptaszynski.football_club_carrier.footballer.model.FootballerDto;
import com.bartoszptaszynski.football_club_carrier.footballer.model.entity.Footballer;
import com.bartoszptaszynski.football_club_carrier.footballer.repository.FootballerRepository;
import com.bartoszptaszynski.football_club_carrier.footballer.repository.PositionRepository;
import com.bartoszptaszynski.football_club_carrier.player.model.entity.Player;
import com.bartoszptaszynski.football_club_carrier.player.repository.PlayerRepository;
import com.bartoszptaszynski.football_club_carrier.player.exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;
    private final PlayerRepository playerRepository;
    private final FootballerRepository footballerRepository;
    private final ClubFootballersRepository clubFootballersRepository;
    private final MatchRepository matchRepository;
    private final PositionRepository positionRepository;

    @Transactional
    public void addClub(ClubCommand command,Long playerId){
        Player player = playerRepository.findPlayerById(playerId).orElseThrow(()->new UserNotFoundException("player not found"));

        if(player.getClub() != null) {
            throw new RuntimeException("player has already club");
        }

        Club club = new Club(command.getName(),command.getCrest(),(command.getFormation()),player);
        clubRepository.save(club);

        player.setClub(club);
        playerRepository.save(player);

        List<ClubFootballers> clubFootballers = club.getFormation().getListOfPositions().stream()
                                                .map(position->new ClubFootballers(club,footballerRepository.findByPosition(position),positionRepository.findByShortcut(position)))
                                                .toList();
        clubFootballersRepository.saveAll(clubFootballers);
    }
    public List<FootballerClubDto> getClubFootballers(Long id)
    {
        Player player = playerRepository.findPlayerById(id).orElseThrow(()->new UserNotFoundException(id.toString()));
        Long clubId ;
        if(player.getClub()==null ) {
            throw new ClubNotFoundException("id of user"+ id);
        } else {
            clubId = player.getClub().getId();
        }
        return clubFootballersRepository.allClubFootballers(clubId);
    }

    public ClubInformationDto getInformationAboutClub(Long idOfUser) {
        Player player = playerRepository.findPlayerById(idOfUser).orElseThrow(() -> new UserNotFoundException(idOfUser.toString()));
        Club club;
        if (player.getClub() == null) {
            throw new ClubNotFoundException("id of user" + idOfUser);
        } else {
            club = player.getClub();

            return ClubInformationDto.builder()
                    .name(club.getName())
                    .rating(clubFootballersRepository.getRatingOfSquad(club.getId()))
                    .league(club.getLeague())
                    .value(club.getValue())
                    .funds(club.getFunds())
                    .crest(club.getCrest())
                    .build();
        }
    }

    public String buyFootballer(Long idOfUser, Long idOfFootballer)  {
        Player player = playerRepository.findPlayerById(idOfUser).orElseThrow(() -> new UserNotFoundException(idOfUser.toString()));
        Footballer footballer = footballerRepository.findById(idOfFootballer).orElseThrow(() -> new FootballerNotFoundException(idOfFootballer));

        Club club;
        if(player.getClub() == null) throw new ClubNotFoundException(" player id "+idOfUser);
        club = player.getClub();
        if(clubFootballersRepository.existsByFootballerAndClub(footballer,club)) throw new IllegalStateException("footballer already in club");

        if(club.getFunds()<footballer.getValue()) {
            throw new IllegalStateException("not enough money");
        }
        else {
            club.setFunds(club.getFunds()-footballer.getValue());
            clubRepository.save(club);
            clubFootballersRepository.save(new ClubFootballers(club,footballer,positionRepository.findByShortcut("R")));
        }
        return footballer +" wysłany na ławkę rezerwowych";
    }

    public Footballer sellFootballer(Long idOfUser, Long idOfFootballer)  {
        Player player = playerRepository.findPlayerById(idOfUser).orElseThrow(() -> new UserNotFoundException(idOfUser.toString()));
        Footballer footballer = footballerRepository.findById(idOfFootballer).orElseThrow(() -> new FootballerNotFoundException(idOfFootballer));

        Club club;
        if(player.getClub() == null) throw new ClubNotFoundException(" player id "+idOfUser);

        club = player.getClub();

        ClubFootballers clubFootballers = clubFootballersRepository.findByFootballerAndClub(footballer,club).orElseThrow(()->new IllegalStateException("footballer is not in the club"));

        club.setFunds(club.getFunds()+footballer.getValue());
        clubRepository.save(club);
        clubFootballersRepository.delete(clubFootballers);

        return footballer;
    }

}
