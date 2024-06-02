package com.bartoszptaszynski.football_club_carrier.club.model;

import lombok.*;

@Data
@Getter
@Setter
@Builder
public class MatchDto {
    private int hostTeamScore;
    private int guestTeamScore;
    private String hostClubName;
    private String guestClubName;
    private int hostSquadRating;
    private  int guestSquadRating;
    private int league;
    private int collectedMoney;
    private int collectedPoints;
}
