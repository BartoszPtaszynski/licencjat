package com.bartoszptaszynski.football_club_carrier.player.controller;

import com.bartoszptaszynski.football_club_carrier.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/player")
public class ApiPlayerFriendsController {

    @Autowired
    private PlayerService playerService;
    @PostMapping("/addFriend")//?id1=x&id2=y
    public ResponseEntity<String> addFriend(@RequestParam Long id1, Long id2) {
        try {
            playerService.addFriend(id1,id2);
            return new ResponseEntity<>((id2 + id2+ " are friends now"), HttpStatus.OK);
        }catch ( IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
