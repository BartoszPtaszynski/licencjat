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

    public ResponseEntity<?> register(PlayerRegistrationCommand command) throws UserExistsException {
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

        return new ResponseEntity<Long>(player.getId(),HttpStatus.CREATED);
    }

    public ResponseEntity<String> login(PlayerLoginCommand command) {
        try {
            Player player = playerRepository.findPlayerByUsername(command.getUsername()).orElseThrow(()-> new UserNotFoundException("not found"));
            if(passwordEncoder.matches(command.getPassword(),player.getPassword())) {
                return new ResponseEntity<>(player.getId().toString(),HttpStatus.OK);

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
        }
    } catch (UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
    }
}

    public ResponseEntity<?> findPlayerInfoById(String id) {
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
}
