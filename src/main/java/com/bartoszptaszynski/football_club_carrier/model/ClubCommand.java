package com.bartoszptaszynski.football_club_carrier.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ClubCommand {
    private String name;
    private String crest;
    private String formation;
    private Long playerId;

}
