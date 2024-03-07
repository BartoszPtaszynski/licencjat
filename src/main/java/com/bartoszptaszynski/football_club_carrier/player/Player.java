package com.bartoszptaszynski.football_club_carrier.player;


import com.bartoszptaszynski.football_club_carrier.model.Club;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity(name = "players")
@Getter
@Setter
@NoArgsConstructor

public class Player implements UserDetails {
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

    @Override
    public List getAuthorities() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

