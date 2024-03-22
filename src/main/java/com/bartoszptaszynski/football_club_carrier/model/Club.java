package com.bartoszptaszynski.football_club_carrier.model;


import com.bartoszptaszynski.football_club_carrier.player.Player;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "clubs")
@Getter
@Setter
@NoArgsConstructor
public class Club {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int rating;
    private int value;
    private int funds;

    @OneToOne()
    @JoinColumn(name="player_id",referencedColumnName = "id")
    private Player player;



    public Club(String name, int funds, Player player) {
        this.name = name;
        this.funds = funds;
        this.player = player;
    }
}
