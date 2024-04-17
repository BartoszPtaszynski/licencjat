package com.bartoszptaszynski.football_club_carrier;

import com.bartoszptaszynski.football_club_carrier.club.model.FormationEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class infoController {

    @GetMapping ("/formations")
    public List<Map<String,Object>> getAllFormations()
    {
        return FormationEnum.getFormations();
    }

}
