package com.bartoszptaszynski.football_club_carrier.footballer;

import com.bartoszptaszynski.football_club_carrier.footballer.model.entity.Footballer;
import com.bartoszptaszynski.football_club_carrier.footballer.repository.FootballerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FootballerService {
    @Autowired
    private FootballerRepository footballerRepository;

    public void getAllFootballers() {
        List<Footballer> footballers = footballerRepository.findAll();
        footballers.stream().forEach(s-> System.out.println(s));
        footballers.stream().forEach(s->log.info(s.toString()));
    }


}
