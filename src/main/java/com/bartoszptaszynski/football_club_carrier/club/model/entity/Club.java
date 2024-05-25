package com.bartoszptaszynski.football_club_carrier.club.model.entity;



import com.bartoszptaszynski.football_club_carrier.club.model.FormationEnum;
import com.bartoszptaszynski.football_club_carrier.player.model.entity.Player;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.EnumType.STRING;


@Entity(name = "clubs")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

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
    public int getLeague() {
        if(value >=0 && value<2000) {
           return  5;
        } else if (value>=2000 && value<4500) {
            return 4;
        } else if(value>=4500 && value <7500) {
            return 3;
        } else if(value>=7500 && value <11000) {
            return 2;
        } else {
            return 1;
        }
    }
}
