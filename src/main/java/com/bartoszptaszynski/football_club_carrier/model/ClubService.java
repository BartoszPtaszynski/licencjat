package com.bartoszptaszynski.football_club_carrier.model;

import com.bartoszptaszynski.football_club_carrier.player.Player;
import com.bartoszptaszynski.football_club_carrier.player.PlayerRepository;
import com.bartoszptaszynski.football_club_carrier.player.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;
    private final PlayerRepository playerRepository;

    public void addClub(ClubCommand command){
        Player player = playerRepository.findPlayerById(command.getPlayerId()).orElseThrow(()->new UserNotFoundException("player not found"));

        Club club = new Club(command.getName(),command.getCrest(),command.getFormation(),player);
        clubRepository.save(club);

        player.setClub(club);
        playerRepository.save(player);


    }

}
