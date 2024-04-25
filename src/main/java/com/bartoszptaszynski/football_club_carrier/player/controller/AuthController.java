package com.bartoszptaszynski.football_club_carrier.player.controller;

import com.bartoszptaszynski.football_club_carrier.ErrorJsonResponse;
import com.bartoszptaszynski.football_club_carrier.player.command.PlayerLoginCommand;
import com.bartoszptaszynski.football_club_carrier.player.command.PlayerRegistrationCommand;
import com.bartoszptaszynski.football_club_carrier.player.PlayerService;
import com.bartoszptaszynski.football_club_carrier.player.exceptions.UserExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
            return this.playerService.register(player);

        } catch (UserExistsException | DataIntegrityViolationException | IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
        }
    }

    @PostMapping()
    public ResponseEntity<?> login(@RequestBody PlayerLoginCommand playerReq) {
       return playerService.login(playerReq);
    }



}
