package com.bartoszptaszynski.football_club_carrier.player.controller;

import com.bartoszptaszynski.football_club_carrier.ErrorJsonResponse;
import com.bartoszptaszynski.football_club_carrier.player.command.PlayerLoginCommand;
import com.bartoszptaszynski.football_club_carrier.player.command.PlayerRegistrationCommand;
import com.bartoszptaszynski.football_club_carrier.player.PlayerService;
import com.bartoszptaszynski.football_club_carrier.player.exceptions.UserExistsException;
import com.bartoszptaszynski.football_club_carrier.player.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authenticate")
public class AuthController {

    @Autowired
    private PlayerService playerService;

    public AuthController(PlayerService playerService) {
        this.playerService = playerService;

    }

    @PostMapping("/register")
    public ResponseEntity<?> addPlayer(@RequestBody PlayerRegistrationCommand player) {
        try {
            return new ResponseEntity<>(this.playerService.register(player).toString(),HttpStatus.CREATED);

        } catch (UserExistsException | DataIntegrityViolationException | IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
        }
    }

    @PostMapping()
    public ResponseEntity<?> login(@RequestBody PlayerLoginCommand playerReq) {
        try {
            return new ResponseEntity<>(playerService.login(playerReq).toString(), HttpStatus.OK);
        }
        catch (UserNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
        }
    }



}
