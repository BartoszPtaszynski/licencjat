package com.bartoszptaszynski.football_club_carrier.player.model.entity;


import com.bartoszptaszynski.football_club_carrier.club.model.entity.Club;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "players")
@Getter
@Setter
@NoArgsConstructor

public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    @OneToOne()
    @JoinColumn(name="club_id",referencedColumnName = "id")
   private Club club;

    public Player( String email, String userName, String password) {
        this.username = userName;
        this.password = password;
        this.email = email;
    }
}

