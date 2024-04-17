package com.bartoszptaszynski.football_club_carrier.club.command;

import com.bartoszptaszynski.football_club_carrier.club.model.FormationEnum;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.EnumType.STRING;

@AllArgsConstructor
@Getter
@Setter
public class ClubCommand {
    private String name;
    private String crest;
    @Enumerated(STRING)
    private FormationEnum formation;

}
