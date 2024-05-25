package com.bartoszptaszynski.football_club_carrier.footballer.model;

import com.bartoszptaszynski.football_club_carrier.footballer.model.entity.Position;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FootballerDto {
    Long id;
    private String name;
    private String surname;
    private int rating;
    private int value;
    private String positions;
}
