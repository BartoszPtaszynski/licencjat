package com.bartoszptaszynski.football_club_carrier.footballer;

import com.bartoszptaszynski.football_club_carrier.footballer.model.FootballerDto;
import com.bartoszptaszynski.football_club_carrier.footballer.model.entity.Footballer;
import com.bartoszptaszynski.football_club_carrier.footballer.model.entity.Position;
import com.bartoszptaszynski.football_club_carrier.footballer.repository.FootballerRepository;
import com.bartoszptaszynski.football_club_carrier.footballer.repository.PositionRepository;
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
    @Autowired
    private PositionRepository positionRepository;



    public List<FootballerDto>  getFilteredFootballers( int priceFrom,int priceTo,int ratingForm, int ratingTo, Long positionId) {
       return footballerRepository.findByFilters(  priceFrom, priceTo, ratingForm,  ratingTo,positionId).stream()
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
    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }


}
