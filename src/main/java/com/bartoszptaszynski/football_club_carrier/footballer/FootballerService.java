package com.bartoszptaszynski.football_club_carrier.footballer;

import com.bartoszptaszynski.football_club_carrier.footballer.model.FootballerDto;
import com.bartoszptaszynski.football_club_carrier.footballer.model.entity.Footballer;
import com.bartoszptaszynski.football_club_carrier.footballer.model.entity.Position;
import com.bartoszptaszynski.football_club_carrier.footballer.repository.FootballerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<FootballerDto>  getFilteredFootballers() {
       return footballerRepository.findByFilters().stream()
               .map(footballer -> FootballerDto.builder()
                       .id(footballer.getId())
                       .name(footballer.getName())
                       .surname(footballer.getSurname())
                       .rating(footballer.getRating())
                       .value(footballer.getValue())
                       .positions(footballer.getFootballerPositions().stream()
                               .map(Position::getShortcut)
                               .collect(Collectors.joining(", ")))
                       .build()).collect(Collectors.toList());
    }


}
