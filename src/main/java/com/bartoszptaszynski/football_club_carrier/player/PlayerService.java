package com.bartoszptaszynski.football_club_carrier.player;


import com.bartoszptaszynski.football_club_carrier.player.command.PlayerLoginCommand;
import com.bartoszptaszynski.football_club_carrier.player.command.PlayerRegistrationCommand;
import com.bartoszptaszynski.football_club_carrier.player.exceptions.UserExistsException;
import com.bartoszptaszynski.football_club_carrier.player.exceptions.UserNotFoundException;
import com.bartoszptaszynski.football_club_carrier.player.model.entity.Player;
import com.bartoszptaszynski.football_club_carrier.player.model.PlayerInfo;
import com.bartoszptaszynski.football_club_carrier.player.repository.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    public PlayerService(PlayerRepository playerRepository, PasswordEncoder passwordEncoder) {
        this.playerRepository = playerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Long register(PlayerRegistrationCommand command) throws UserExistsException {
        if(playerRepository.existsByUsername(command.getUsername())) {
            throw new UserExistsException("username");
        }

        if(playerRepository.existsByEmail(command.getEmail())) {
            throw new UserExistsException("email");
        }

        Player player = new Player(
                command.getEmail(),command.getUsername(),passwordEncoder.encode(command.getPassword())
        );

        playerRepository.save(player);

        return player.getId();
    }

    public Long login(PlayerLoginCommand command) {

        Player player = playerRepository.findPlayerByUsername(command.getUsername()).orElseThrow(()-> new UserNotFoundException("not found"));
        if(passwordEncoder.matches(command.getPassword(),player.getPassword())) {
            return player.getId();
        } else {
            throw new UserNotFoundException("password incorrect");
        }

}

    public ResponseEntity<?>  findPlayerInfoById(String id) {
        try {
            Player player = playerRepository.findPlayerById(Long.valueOf(id)).orElseThrow(()->new UserNotFoundException("player not found"));
            PlayerInfo playerInfo = PlayerInfo.builder()
                    .id(player.getId())
                    .username(player.getUsername())
                    .email(player.getEmail())
                    .clubId(player.getClub() == null?null:player.getClub().getId())
                    .build();
            return new ResponseEntity<>(playerInfo,HttpStatus.ACCEPTED);
        }catch (UserNotFoundException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>("ERROR",HttpStatus.BAD_REQUEST);
        }
    }


    public String addFriend(Long id1,Long id2) {
        Player player1=playerRepository.findPlayerById(id1).orElseThrow( () -> new UserNotFoundException("user with id "+id1+" not found"));
        Player player2=playerRepository.findPlayerById(id2).orElseThrow( () -> new UserNotFoundException("user with id "+id2+" not found"));

        player1.addFriend(player2);
        player2.addFriend(player1);

        playerRepository.saveAll(List.of(player1,player2));
        return player1.getPlayerFriends().toString();
    }


}
