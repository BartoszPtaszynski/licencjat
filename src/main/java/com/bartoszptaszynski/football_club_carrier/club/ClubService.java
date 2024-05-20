package com.bartoszptaszynski.football_club_carrier.club;

import com.bartoszptaszynski.football_club_carrier.club.command.ClubCommand;
import com.bartoszptaszynski.football_club_carrier.club.model.entity.Club;
import com.bartoszptaszynski.football_club_carrier.club.model.entity.ClubFootballers;
import com.bartoszptaszynski.football_club_carrier.club.model.entity.Match;
import com.bartoszptaszynski.football_club_carrier.club.repository.ClubFootballersRepository;
import com.bartoszptaszynski.football_club_carrier.club.repository.ClubRepository;
import com.bartoszptaszynski.football_club_carrier.club.repository.MatchRepository;
import com.bartoszptaszynski.football_club_carrier.footballer.repository.FootballerRepository;
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
                                                .map(position->new ClubFootballers(club,footballerRepository.findByPosition(position),position))
                                                .toList();
        clubFootballersRepository.saveAll(clubFootballers);

    }

}
