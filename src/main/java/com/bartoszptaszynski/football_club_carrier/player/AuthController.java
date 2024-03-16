package com.bartoszptaszynski.football_club_carrier.player;

import com.bartoszptaszynski.football_club_carrier.model.Club;
import com.bartoszptaszynski.football_club_carrier.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authenticate")
public class AuthController {

    @Autowired
    private PlayerService playerService;
    @Autowired
    private ClubService clubService;

    public AuthController(PlayerService playerService, ClubService clubService) {
        this.playerService = playerService;
        this.clubService = clubService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> addPlayer(@RequestBody PlayerRegistrationCommand player) {
        return  this.playerService.register(player);
    }

    @RequestMapping()
    public ResponseEntity<?> login(@RequestBody PlayerLoginCommand playerReq) {
       return playerService.login(playerReq);
    }

    @PostMapping("/addClub")
    public ResponseEntity<?> addClub( @RequestBody Club club) {
        this.clubService.createClub(club);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

}