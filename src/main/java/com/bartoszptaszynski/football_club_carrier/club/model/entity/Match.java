package com.bartoszptaszynski.football_club_carrier.club.model.entity;

import com.bartoszptaszynski.football_club_carrier.club.model.entity.Club;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "matches")
@Getter
@Setter
@ToString
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int hostTeamScore;
    private int guestTeamScore;
    @ManyToOne
    @JoinColumn(name="host_club_id",referencedColumnName = "id")
    private Club hostClub;
    @ManyToOne
    @JoinColumn(name="guest_club_id",referencedColumnName = "id")
    private Club guestClub;
    private int league;
    private int hostCollectedMoney;
    private int guestCollectedMoney;
    private int hostClubRating;
    private int guestClubRating;
    private int hostCollectedPoints;
    private int guestCollectedPoints;


    public Match(int hostTeamScore, int guestTeamScore, Club hostClub, Club guestClub, int league, int hostCollectedMoney, int guestCollectedMoney, int hostClubRating, int guestClubRating, int hostCollectedPoints, int guestCollectedPoints) {
        this.hostTeamScore = hostTeamScore;
        this.guestTeamScore = guestTeamScore;
        this.hostClub = hostClub;
        this.guestClub = guestClub;
        this.league = league;
        this.hostCollectedMoney = hostCollectedMoney;
        this.guestCollectedMoney = guestCollectedMoney;
        this.hostClubRating = hostClubRating;
        this.guestClubRating = guestClubRating;
        this.hostCollectedPoints = hostCollectedPoints;
        this.guestCollectedPoints = guestCollectedPoints;
    }

    public Match() {

    }
}
