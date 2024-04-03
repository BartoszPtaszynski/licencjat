package com.bartoszptaszynski.football_club_carrier.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/club")
public class ApiClubController {
    @Autowired
    private ClubService clubService;

    @PostMapping("/add")
    public ResponseEntity<?> addClub(@RequestBody ClubCommand command) {
        clubService.addClub(command);
        return new ResponseEntity<>("created", HttpStatus.OK);
    }
}
