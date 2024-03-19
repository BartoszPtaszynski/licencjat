package com.bartoszptaszynski.football_club_carrier.player;


import com.bartoszptaszynski.football_club_carrier.model.Club;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity(name = "players")
@Getter
@Setter
@NoArgsConstructor

public class Player {
    @Id
    @GeneratedValue
    private UUID id;
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

