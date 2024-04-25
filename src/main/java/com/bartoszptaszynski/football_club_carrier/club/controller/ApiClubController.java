package com.bartoszptaszynski.football_club_carrier.club.controller;

import com.bartoszptaszynski.football_club_carrier.club.ClubService;
import com.bartoszptaszynski.football_club_carrier.club.command.ClubCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/club")
public class ApiClubController {
    @Autowired
    private ClubService clubService;

    @PostMapping("/add/{playerId}")
    public ResponseEntity<?> addClub(@RequestBody ClubCommand command,@PathVariable Long playerId) {
        try {
            clubService.addClub(command, playerId);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }

}
