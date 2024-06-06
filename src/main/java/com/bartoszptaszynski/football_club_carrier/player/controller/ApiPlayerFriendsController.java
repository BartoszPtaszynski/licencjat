package com.bartoszptaszynski.football_club_carrier.player.controller;

import com.bartoszptaszynski.football_club_carrier.club.repository.ClubFootballersRepository;
import com.bartoszptaszynski.football_club_carrier.club.repository.ClubRepository;
import com.bartoszptaszynski.football_club_carrier.club.repository.MatchRepository;
import com.bartoszptaszynski.football_club_carrier.player.PlayerService;
import com.bartoszptaszynski.football_club_carrier.player.exceptions.FriendExistsException;
import com.bartoszptaszynski.football_club_carrier.player.exceptions.FriendNotExistsException;
import com.bartoszptaszynski.football_club_carrier.player.exceptions.UserNotFoundException;
import com.bartoszptaszynski.football_club_carrier.player.model.entity.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
@Slf4j
public class ApiPlayerFriendsController {

    @Autowired
    private PlayerService playerService;
    @Autowired
    private ClubFootballersRepository matchRepository;
    @PostMapping("/addFriend")//?id1=x&id2=y
    public ResponseEntity<String> addFriend(@RequestParam Long id1, Long id2) {
        try {
            playerService.addFriend(id1,id2);
            return new ResponseEntity<>((id2+" " + id2+ " are friends now"), HttpStatus.OK);
        }catch (UserNotFoundException | FriendExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
    @DeleteMapping("/deleteFriend")
    public ResponseEntity<String> deleteFriend(@RequestParam Long id1, Long id2) {
        try {
            return new ResponseEntity<>( playerService.deleteFriend(id1,id2).getUsername(), HttpStatus.OK);
        }catch (UserNotFoundException | FriendNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getFriendsInfo")
    public ResponseEntity<?> getFriends(@RequestParam Long id) {
        return new ResponseEntity<>(playerService.getFriends(id),HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam Long id) {
    log.info(String.valueOf(matchRepository.getRatingOfSquad(id)));
        return new ResponseEntity<>("ok",HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchPlayersByPattern(@RequestParam Long id, String searchType,String pattern)
    {
        return new ResponseEntity<>(playerService.findPlayersByPattern(id,searchType,pattern),HttpStatus.OK);
    }



}
