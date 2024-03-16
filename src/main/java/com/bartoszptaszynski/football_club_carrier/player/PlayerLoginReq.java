package com.bartoszptaszynski.football_club_carrier.player;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class PlayerLoginReq {
    private final String username;
    private final String password;

    public PlayerLoginReq(String username,String password) {
        this.username = username;
        this.password = password;
    }


}
