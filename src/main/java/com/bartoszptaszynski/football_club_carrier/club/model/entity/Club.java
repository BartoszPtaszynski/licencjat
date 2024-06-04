package com.bartoszptaszynski.football_club_carrier.club.model.entity;



import com.bartoszptaszynski.football_club_carrier.club.model.FormationEnum;
import com.bartoszptaszynski.football_club_carrier.player.model.entity.Player;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private int league;
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
        this.league = 5;
    }
    public Club(String name, FormationEnum formation, Player player) {
        this.name = name;
       this.formation = formation;
       value = 0;
       funds = 0;
       this.player = player;
       this.league = 5;
    }
    public void setLeague() {
        if(value >=0 && value<2000) {
           league =   5;
        } else if (value>=2000 && value<4500) {
            league =  4;
        } else if(value>=4500 && value <7500) {
            league =  3;
        } else if(value>=7500 && value <11000) {
            league =  2;
        } else {
            league =  1;
        }
    }
    public Map<String,Integer> setValueAndFundsAfterMatch(boolean isWon) {

        int newValue=0;
        int newFunds=0;
        if(isWon) {
            switch (league) {
                case 5 -> {
                    newValue=150;
                    newFunds=2000;
                }
                case 4 -> {
                    newValue=250;
                    newFunds=5000;
                }
                case 3 -> {
                    newValue=500;
                    newFunds=7500;
                }
                case 2 -> {
                    newValue=800;
                    newFunds=10000;
                }
                case 1 -> {
                    newValue=1000;
                    newFunds=12500;
                }
            }
        }
        else {
            switch (league) {
                case 5 -> {
                    newValue=-100;
                    newFunds=-500;
                }
                case 4 -> {
                    newValue=-200;
                    newFunds=-750;
                }
                case 3 -> {
                    newValue=-350;
                    newFunds=-1500;
                }
                case 2 -> {
                    newValue=-500;
                    newFunds=-2000;
                }
                case 1 -> {
                    newValue=-750;
                    newFunds=-4000;
                }
            }

        }
        value+=newValue;
        funds+=newFunds;
        if(value<=0) value=0;
        if(funds<=0) funds=0;
        setLeague();
        Map<String,Integer> newValues=new HashMap<>();
        newValues.put("value",newValue);
        newValues.put("funds",newFunds);
        return  newValues;
    }
}
