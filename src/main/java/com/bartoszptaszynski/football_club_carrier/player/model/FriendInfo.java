package com.bartoszptaszynski.football_club_carrier.player.model;

import com.bartoszptaszynski.football_club_carrier.club.model.entity.Club;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FriendInfo {
    private Long id;
    private String username;
    private String clubName;
    private String league;
    private String results;
    private String clubRating;
}
