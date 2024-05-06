package com.bartoszptaszynski.football_club_carrier.footballer;

import com.bartoszptaszynski.football_club_carrier.footballer.model.FootballerDto;
import com.bartoszptaszynski.football_club_carrier.footballer.model.entity.Footballer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public List<FootballerDto> getFilteredFootballers() {
        return footballerService.getFilteredFootballers();

    }
}
