package com.bartoszptaszynski.football_club_carrier.player;

import com.bartoszptaszynski.football_club_carrier.model.Club;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return  this.playerService.register(player);
    }

    @PostMapping()
    public ResponseEntity<?> login(@RequestBody PlayerLoginCommand playerReq) {
       return playerService.login(playerReq);
    }



}
