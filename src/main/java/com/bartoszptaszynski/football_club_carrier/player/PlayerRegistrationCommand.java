package com.bartoszptaszynski.football_club_carrier.player;

import lombok.Data;

@Data
public class PlayerRegistrationCommand {
    private String email;
    private String username;
    private String password;
}
