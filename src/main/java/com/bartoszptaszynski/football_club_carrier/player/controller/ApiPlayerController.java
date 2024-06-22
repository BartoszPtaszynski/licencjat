package com.bartoszptaszynski.football_club_carrier.player.controller;

import com.bartoszptaszynski.football_club_carrier.player.PlayerService;
import com.bartoszptaszynski.football_club_carrier.player.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/player")
@RequiredArgsConstructor
public class ApiPlayerController {
    @Autowired
    private PlayerService playerService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getPlayerInfo(@PathVariable String id) {
        try {
            return new ResponseEntity<>(playerService.findPlayerInfoById(id), HttpStatus.OK);
        }catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
