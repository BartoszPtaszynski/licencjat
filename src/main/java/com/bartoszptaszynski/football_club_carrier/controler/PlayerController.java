package com.bartoszptaszynski.football_club_carrier.controler;

import com.bartoszptaszynski.football_club_carrier.model.Club;
import com.bartoszptaszynski.football_club_carrier.player.Player;
import com.bartoszptaszynski.football_club_carrier.player.PlayerLoginReq;
import com.bartoszptaszynski.football_club_carrier.service.ClubService;
import com.bartoszptaszynski.football_club_carrier.player.PlayerService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    @Autowired
    private PlayerService playerService;
    @Autowired
    private ClubService clubService;

    public PlayerController(PlayerService playerService, ClubService clubService) {
        this.playerService = playerService;
        this.clubService = clubService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> addPlayer(@RequestBody Player player) {
        this.playerService.addPlayer(player);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

//    @RequestMapping("/login")
//    public ResponseEntity<?> login(@RequestBody PlayerLoginReq playerReq) {
//        playerService.login(playerReq);
//
//        return new ResponseEntity<>("success", HttpStatus.CREATED);
//    }

    @PostMapping("/addClub")
    public ResponseEntity<?> addClub( @RequestBody Club club) {
        this.clubService.createClub(club);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

}
