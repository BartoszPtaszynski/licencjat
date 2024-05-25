package com.bartoszptaszynski.football_club_carrier.club.model.entity;

import com.bartoszptaszynski.football_club_carrier.footballer.model.entity.Footballer;
import com.bartoszptaszynski.football_club_carrier.footballer.model.entity.Position;
import com.bartoszptaszynski.football_club_carrier.footballer.repository.PositionRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity (name = "club_footballers")
@RequiredArgsConstructor
@Getter
@Setter

public class ClubFootballers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="club_id",referencedColumnName = "id")
    private Club club;

    @ManyToOne
    @JoinColumn(name="footballer_id",referencedColumnName = "id")
    private Footballer footballer;

    @ManyToOne
    @JoinColumn(name="position_id",referencedColumnName = "id")
    private Position position;

    public ClubFootballers(Club club, Footballer footballer, Position position) {
        this.club = club;
        this.footballer = footballer;
        this.position = position;
    }
}
