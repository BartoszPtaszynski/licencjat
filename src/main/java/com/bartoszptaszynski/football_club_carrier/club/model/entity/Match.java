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
}
