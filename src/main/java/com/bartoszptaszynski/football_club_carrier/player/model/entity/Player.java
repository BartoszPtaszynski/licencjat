package com.bartoszptaszynski.football_club_carrier.player.model.entity;


import com.bartoszptaszynski.football_club_carrier.club.model.entity.Club;
import com.bartoszptaszynski.football_club_carrier.footballer.model.entity.Position;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @ManyToMany
    @JoinTable(
            name = "player_friends",
            joinColumns = @JoinColumn(name = "player_id_1"),
            inverseJoinColumns = @JoinColumn(name = "player_id_2"))
    private List<Player> playerFriends;

    public Player( String email, String userName, String password) {
        this.username = userName;
        this.password = password;
        this.email = email;
    }


    public void addFriend(Player player2) {
        if(this == player2 ) {
            throw new IllegalArgumentException("impossible to add own account to friends list");
        }
        else if (playerFriends.contains(player2)) {
            throw new IllegalArgumentException(id + " has already "+player2.getId()+" in friendList");
        }
        playerFriends.add(player2);
    }

    public Player deleteFriend(Player player2) {
        if(this == player2 ) {
            throw new IllegalArgumentException("impossible to add own account to friends list");
        }
        else if (!playerFriends.contains(player2)) {
            throw new IllegalArgumentException(id + " has not  "+player2.getId()+" in friendList");
        }
        playerFriends.remove(player2);
        return player2;
    }
}

