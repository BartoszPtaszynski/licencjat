package com.bartoszptaszynski.football_club_carrier.player;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PlayerLoginCommand {
    private final String username;
    private final String password;

    public PlayerLoginCommand(String username, String password) {
        this.username = username;
        this.password = password;
    }


}
