package com.bartoszptaszynski.football_club_carrier.club.controller;

import com.bartoszptaszynski.football_club_carrier.club.ClubService;
import com.bartoszptaszynski.football_club_carrier.club.Exception.ClubNotFoundException;
import com.bartoszptaszynski.football_club_carrier.club.command.ClubCommand;
import com.bartoszptaszynski.football_club_carrier.club.model.FormationEnum;
import com.bartoszptaszynski.football_club_carrier.footballer.expetion.FootballerHasNotThatPosition;
import com.bartoszptaszynski.football_club_carrier.footballer.expetion.FootballerNotFoundException;
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
    @GetMapping("/footballersToChange")
    public ResponseEntity<?>getFootballersToChange( @RequestParam Long footballerId, Long userId) {
        try {
            return ResponseEntity.ok(clubService.footballersToChange(footballerId, userId));
        }
        catch (UserNotFoundException| ClubNotFoundException | FootballerNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/changeFootballers")
    public ResponseEntity<String>changeFootballers( @RequestParam Long footballer1Id,Long footballer2Id, Long userId) {
        try {
            clubService.changeFootballers(footballer1Id,footballer2Id, userId);
            return ResponseEntity.ok("footballers changed");
        }
        catch (UserNotFoundException | ClubNotFoundException | FootballerNotFoundException |
               FootballerHasNotThatPosition e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @PutMapping("/changeEmptyFootballer")
    public ResponseEntity<String>changeFootballerWithEmptyPosition( @RequestParam Long footballerId,String positionShortcut, Long userId) {
        try {
            clubService.changeFootballerWithEmptyPosition(footballerId,positionShortcut, userId);
            return ResponseEntity.ok("footballer position changed");
        }
        catch (UserNotFoundException | ClubNotFoundException | FootballerNotFoundException |
               FootballerHasNotThatPosition e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/changeFormation")
    public ResponseEntity<?> changeFootballers(@RequestParam FormationEnum formation, Long userId) {
        try {
            clubService.changeFormation(formation, userId);
            return ResponseEntity.ok("formation changed");
        }catch (UserNotFoundException|ClubNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/playMatch")
    public ResponseEntity<?> playMatch(@RequestParam Long userId) {
        try {

            return ResponseEntity.ok(clubService.playMatch(userId));
        }catch (UserNotFoundException | ClubNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/results")
    public ResponseEntity<?> getResults(@RequestParam Long userId) {
        return ResponseEntity.ok(clubService.getResults(userId));
    }
    @GetMapping("/allFootballersInSquad")
    public ResponseEntity<?> areAllFootballersInSquad(@RequestParam Long userId) {
        return ResponseEntity.ok(clubService.allFootballersInSquad(userId));
    }

}
