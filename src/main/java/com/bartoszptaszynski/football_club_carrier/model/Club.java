package com.bartoszptaszynski.football_club_carrier.model;


import com.bartoszptaszynski.football_club_carrier.player.Player;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


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
    private String formation;

    @OneToOne()
    @JoinColumn(name="player_id",referencedColumnName = "id")
    private Player player;



    public Club(String name, int funds, Player player) {
        this.name = name;
        this.funds = funds;
        this.player = player;
    }
    public Club(String name, String crest, String formation, Player player) {
        this.name = name;
       this.crest = crest;
       this.formation = formation;

       value = 0;
       funds = 0;
       this.player = player;


    }
}
