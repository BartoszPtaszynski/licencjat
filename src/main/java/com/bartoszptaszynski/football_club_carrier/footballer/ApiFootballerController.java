package com.bartoszptaszynski.football_club_carrier.footballer;

import com.bartoszptaszynski.football_club_carrier.footballer.model.FootballerDto;
import com.bartoszptaszynski.football_club_carrier.footballer.model.entity.Footballer;
import com.bartoszptaszynski.football_club_carrier.footballer.model.entity.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/footballers")
public class ApiFootballerController {
    @Autowired
    private FootballerService footballerService;


    @GetMapping()
    public List<FootballerDto> getFilteredFootballers(@RequestParam int priceFrom,int priceTo,int ratingForm,
                                                      int ratingTo, Long positionId ) {
        return footballerService.getFilteredFootballers(  priceFrom, priceTo, ratingForm,  ratingTo,  positionId);
    }
    @GetMapping("/positions")
    public List<Position> getPositions( ) {
        return footballerService.getAllPositions();
    }
}
