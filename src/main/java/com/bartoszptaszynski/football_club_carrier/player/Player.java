package com.bartoszptaszynski.football_club_carrier.player;


import com.bartoszptaszynski.football_club_carrier.model.Club;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Entity(name = "players")
@Getter
@Setter
@NoArgsConstructor

public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_seq")
    @SequenceGenerator(name = "player_seq", sequenceName = "players_seq", allocationSize = 1 )
    private Long id;
    private String username;
    private String password;
    private String email;
   @OneToOne(mappedBy = "player")
   private Club club;

    public Player( String email, String userName, String password) {
        this.username = userName;
        this.password = password;
        this.email = email;
    }
}

