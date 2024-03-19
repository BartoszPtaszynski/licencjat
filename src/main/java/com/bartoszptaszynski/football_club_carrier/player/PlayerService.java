package com.bartoszptaszynski.football_club_carrier.player;


import com.bartoszptaszynski.football_club_carrier.player.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    public ResponseEntity<?> register(PlayerRegistrationCommand command)
    {
        if(playerRepository.existsByUsername(command.getUsername())) {
            return new ResponseEntity<>("user not found", HttpStatus.BAD_REQUEST);
        }
        Player player = new Player(
                command.getEmail(),command.getUsername(),passwordEncoder.encode(command.getPassword())
        );
        playerRepository.save(player);

        return new ResponseEntity<>("registered",HttpStatus.CREATED);
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

    public ResponseEntity<?> findPlayerInfoById(UUID id) {
        try {
            Player player = playerRepository.findPlayerById(id).orElseThrow(()->new UserNotFoundException("player not found"));
            PlayerInfo playerInfo = PlayerInfo.builder()
                    .id(player.getId())
                    .username(player.getUsername())
                    .email(player.getEmail())
                    .club_id(player.getClub() == null?null:player.getClub().getId())
                    .build();
            return new ResponseEntity<>(playerInfo,HttpStatus.ACCEPTED);
        }catch (UserNotFoundException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>("ERROR",HttpStatus.BAD_REQUEST);
        }
    }
}
