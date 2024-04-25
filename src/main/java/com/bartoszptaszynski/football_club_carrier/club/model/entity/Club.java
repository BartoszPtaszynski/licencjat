package com.bartoszptaszynski.football_club_carrier.club.model.entity;


import com.bartoszptaszynski.football_club_carrier.club.model.FormationEnum;
import com.bartoszptaszynski.football_club_carrier.player.model.entity.Player;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.EnumType.STRING;


@Entity(name = "clubs")
@Getter
@Setter
@NoArgsConstructor
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int rating;
    private int value;
    private int funds;

    private String crest;
    @Enumerated(STRING)
    private FormationEnum formation;

    @OneToOne()
    @JoinColumn(name="player_id",referencedColumnName = "id")
    private Player player;


    @OneToMany(mappedBy = "club")
    private List<ClubFootballers> footballers;


    public Club(String name, int funds, Player player) {
        this.name = name;
        this.funds = funds;
        this.player = player;
    }
    public Club(String name, String crest, FormationEnum formation, Player player) {
        this.name = name;
       this.crest = crest;
       this.formation = formation;

       value = 0;
       funds = 0;
       this.player = player;
    }

}
