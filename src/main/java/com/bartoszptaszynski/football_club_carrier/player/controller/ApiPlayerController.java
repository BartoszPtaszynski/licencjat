package com.bartoszptaszynski.football_club_carrier.player.controller;

import com.bartoszptaszynski.football_club_carrier.player.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/player")
@RequiredArgsConstructor
public class ApiPlayerController {
    @Autowired
    private PlayerService playerService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getPlayerInfo(@PathVariable String id) {
        return  playerService.findPlayerInfoById(id);
    }
}
