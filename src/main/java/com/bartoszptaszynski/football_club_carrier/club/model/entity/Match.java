package com.bartoszptaszynski.football_club_carrier.club.match;

import com.bartoszptaszynski.football_club_carrier.club.model.entity.Club;
import jakarta.persistence.*;

@Entity
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
