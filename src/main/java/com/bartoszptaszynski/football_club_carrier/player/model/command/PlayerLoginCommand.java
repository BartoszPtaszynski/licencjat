package com.bartoszptaszynski.football_club_carrier.player.model.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerLoginCommand {
    private final String username;
    private final String password;

    @JsonCreator
    public PlayerLoginCommand(@JsonProperty("username") String username, @JsonProperty("password") String password) {
        this.username = username;
        this.password = password;
    }


}
