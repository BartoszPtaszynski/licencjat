package com.bartoszptaszynski.football_club_carrier.club;

import com.bartoszptaszynski.football_club_carrier.club.Exception.ClubNotFoundException;
import com.bartoszptaszynski.football_club_carrier.club.command.ClubCommand;
import com.bartoszptaszynski.football_club_carrier.club.model.ClubInformationDto;
import com.bartoszptaszynski.football_club_carrier.club.model.FormationEnum;
import com.bartoszptaszynski.football_club_carrier.club.model.entity.Club;
import com.bartoszptaszynski.football_club_carrier.club.model.entity.ClubFootballers;
import com.bartoszptaszynski.football_club_carrier.club.repository.ClubFootballersRepository;
import com.bartoszptaszynski.football_club_carrier.club.repository.ClubRepository;
import com.bartoszptaszynski.football_club_carrier.club.repository.MatchRepository;
import com.bartoszptaszynski.football_club_carrier.footballer.expetion.FootballerHasNotThatPosition;
import com.bartoszptaszynski.football_club_carrier.footballer.expetion.FootballerNotFoundException;
import com.bartoszptaszynski.football_club_carrier.footballer.model.FootballerClubDto;
import com.bartoszptaszynski.football_club_carrier.footballer.model.entity.Footballer;
import com.bartoszptaszynski.football_club_carrier.footballer.model.entity.Position;
import com.bartoszptaszynski.football_club_carrier.footballer.repository.FootballerRepository;
import com.bartoszptaszynski.football_club_carrier.footballer.repository.PositionRepository;
import com.bartoszptaszynski.football_club_carrier.player.model.entity.Player;
import com.bartoszptaszynski.football_club_carrier.player.repository.PlayerRepository;
import com.bartoszptaszynski.football_club_carrier.player.exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
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
        List<FootballerClubDto> footballerClubDtos = clubFootballersRepository.allClubFootballers(clubId);
        List<Position> activePositions = footballerClubDtos.stream().map(FootballerClubDto::getActivePosition).toList();


        List<Position> positions = positionRepository.findByListOfShortcut(player.getClub().getFormation().getListOfPositions());
        for(Position position:positions) {
            if(! activePositions.contains(position)) {
                footballerClubDtos.add(new FootballerClubDto(null,"BRAK ZAWODNIKA",null,0,0,null,position));
            }
        }

        return footballerClubDtos;
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
                    .formation( club.getFormation().getFormation(club.getFormation()))
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

    public List<FootballerClubDto> footballersToChange(Long footballerId, Long userId) {
        List<FootballerClubDto> clubFootballers = getClubFootballers(userId);
        Club club = playerRepository.findPlayerById(userId).get().getClub();;

        FootballerClubDto footballerDto = clubFootballersRepository.findByFootballerId(footballerId,club).orElseThrow(()-> new FootballerNotFoundException(footballerId));

        if(footballerDto.getActivePosition().getShortcut().equals("R")) {

            return clubFootballers.stream()
                    .filter(footballer->!footballer.getActivePosition().getShortcut().equals("R"))
                    .filter(footballer->footballerDto.getFootballerPositions().contains(footballer.getActivePosition()))
                    .toList();
        } else {

            return clubFootballers.stream()
                    .filter(footballer->footballer.getActivePosition().getShortcut().equals("R"))
                    .filter(footballer -> footballer.getFootballerPositions().contains(footballerDto.getActivePosition()))
                    .toList();
        }
    }

    public void changeFootballers(Long footballer1Id, Long footballer2Id, Long userId) {
        Club club = playerRepository.findPlayerById(userId).orElseThrow(()->  new UserNotFoundException(userId.toString())).getClub();
        if(club == null) {
            throw new ClubNotFoundException("player id = "+userId);
        }
        ClubFootballers footballer1 = clubFootballersRepository.clubFootballer(club.getId(),footballer1Id);
        ClubFootballers footballer2 = clubFootballersRepository.clubFootballer(club.getId(),footballer2Id);
        if(! footballer1.getFootballer().getFootballerPositions().contains(footballer2.getPosition())) {
            throw new FootballerHasNotThatPosition(footballer2.getPosition().getNameOfPosition());
        }
        if(! footballer2.getFootballer().getFootballerPositions().contains(footballer1.getPosition())) {
            throw new FootballerHasNotThatPosition(footballer1.getPosition().getNameOfPosition());
        }

        Position positionToChange = footballer1.getPosition();
        footballer1.setPosition(footballer2.getPosition());
        footballer2.setPosition(positionToChange);

        clubFootballersRepository.save(footballer1);
        clubFootballersRepository.save(footballer2);

    }

    public void changeFootballerWithEmptyPosition(Long footballerId, String positionShortcut, Long userId) {
        Club club = playerRepository.findPlayerById(userId).orElseThrow(()->  new UserNotFoundException(userId.toString())).getClub();
        if(club == null) {
            throw new ClubNotFoundException("player id = "+userId);
        }
        ClubFootballers footballer1 = clubFootballersRepository.clubFootballer(club.getId(),footballerId);
        Position position = positionRepository.findByShortcut(positionShortcut);
        if(! footballer1.getFootballer().getFootballerPositions().contains(position)) {
            throw new FootballerHasNotThatPosition(position.getNameOfPosition());
        }
        else {
            footballer1.setPosition(position);
            clubFootballersRepository.save(footballer1);
        }
    }

    @Transactional
    public void changeFormation(FormationEnum formationEnum,Long userId) {
        Club club = playerRepository.findPlayerById(userId).orElseThrow(()->  new UserNotFoundException(userId.toString())).getClub();
        if(club == null) {
            throw new ClubNotFoundException("player id = "+userId);
        }
        ArrayList<Position> positions = new ArrayList<>(positionRepository.findByListOfShortcut(formationEnum.getListOfPositions()));
        List<ClubFootballers> clubFootballers = clubFootballersRepository.clubFootballers(club.getId());
        Position reserve = positionRepository.findByShortcut("R");
        clubFootballers.forEach(
                clubFootballer->{
                    if(! positions.contains(clubFootballer.getPosition())) {
                        clubFootballer.setPosition(reserve);
                    } else {
                        positions.remove(clubFootballer.getPosition());
                    }
                }
        );
        ArrayList<ClubFootballers> availableFootballers = new ArrayList(clubFootballers.stream().filter(footballer->footballer.getPosition().equals(reserve)).toList());

        positions.forEach(position -> {
            availableFootballers.stream()
                    .filter(footballer -> footballer.getFootballer().getFootballerPositions().contains(position))
                    .findFirst()
                    .ifPresent(footballer -> footballer.setPosition(position));
        });

        club.setFormation(formationEnum);
        clubRepository.save(club);
        clubFootballersRepository.saveAll(clubFootballers);
    }

}
