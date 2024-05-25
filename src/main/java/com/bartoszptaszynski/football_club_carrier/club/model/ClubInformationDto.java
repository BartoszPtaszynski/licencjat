package com.bartoszptaszynski.football_club_carrier.club.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClubInformationDto {
    private String name;
    private int league;
    private int rating;
    private int value;
    private int funds;
    private String crest;



}
