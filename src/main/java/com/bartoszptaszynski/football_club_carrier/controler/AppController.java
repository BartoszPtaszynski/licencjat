package com.bartoszptaszynski.football_club_carrier.controler;

import com.bartoszptaszynski.football_club_carrier.footballer.repository.FootballerRepository;
import com.bartoszptaszynski.football_club_carrier.footballer.FootballerService;
import com.bartoszptaszynski.football_club_carrier.club.repository.ClubRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AppController {
    @Autowired
    FootballerService footballerService;
    @Autowired
    FootballerRepository footballerRepository;
    @Autowired
    ClubRepository clubRepository;
    @GetMapping("/xD")
    public ResponseEntity<String> login(){
       //footballerService.getAllFootballers();
clubRepository.findById(10L).ifPresent(s->s.getFootballers().stream().forEach(x->log.warn(x.getPosition())));
        clubRepository.findById(10L).ifPresent(s->log.warn(s.getFootballers().size()+""));
        return new ResponseEntity<String>("XDDD", HttpStatus.OK);
    }
}
