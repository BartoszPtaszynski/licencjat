package com.bartoszptaszynski.football_club_carrier.footballer.model;

import com.bartoszptaszynski.football_club_carrier.footballer.model.entity.Footballer;
import com.bartoszptaszynski.football_club_carrier.footballer.model.entity.Position;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Data
@RequiredArgsConstructor
public class FootballerClubDto extends Footballer {
    private Position activePosition;

    public FootballerClubDto(Footballer footballer, Position position) {
        super(footballer.getId(),footballer.getName(),footballer.getSurname(),footballer.getRating(),footballer.getValue(),footballer.getFootballerPositions());
        this.activePosition = position;
    }
}
