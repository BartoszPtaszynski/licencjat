package com.bartoszptaszynski.football_club_carrier.player;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/player")
@RequiredArgsConstructor
public class ApiPlayerController {
    private final PlayerService playerService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getPlayerInfo(@PathVariable UUID id) {
        return  playerService.findPlayerInfoById(id);
    }
}
