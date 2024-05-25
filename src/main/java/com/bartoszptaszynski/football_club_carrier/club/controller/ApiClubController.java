package com.bartoszptaszynski.football_club_carrier.club.controller;

import com.bartoszptaszynski.football_club_carrier.club.ClubService;
import com.bartoszptaszynski.football_club_carrier.club.Exception.ClubNotFoundException;
import com.bartoszptaszynski.football_club_carrier.club.command.ClubCommand;
import com.bartoszptaszynski.football_club_carrier.footballer.FootballerNotFoundException;
import com.bartoszptaszynski.football_club_carrier.player.exceptions.UserNotFoundException;
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

    @GetMapping("/allFootballers")
    public ResponseEntity<?> getAllFootballers(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(clubService.getClubFootballers(id));
        }
        catch (UserNotFoundException| ClubNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/info")
    public ResponseEntity<?> getClubInformation(@RequestParam Long idOfUser) {
        try {
            return ResponseEntity.ok(clubService.getInformationAboutClub(idOfUser));
        }
        catch (UserNotFoundException| ClubNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/buyFootballer")
    public ResponseEntity<String> buyFootballer(@RequestParam Long idOfUser, Long idOfFootballer) {
        try{
            return ResponseEntity.ok(clubService.buyFootballer(idOfUser,idOfFootballer));
        }
        catch (ClubNotFoundException| FootballerNotFoundException|UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
        catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/sellFootballer")
    public ResponseEntity<?> sellFootballer(@RequestParam Long idOfUser, Long idOfFootballer) {
        try{
            return ResponseEntity.ok(clubService.sellFootballer(idOfUser,idOfFootballer));
        }
        catch (ClubNotFoundException| FootballerNotFoundException|UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
        catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
        }
    }

}
