package com.bartoszptaszynski.football_club_carrier.player.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super("User "+message + "not found");
    }
}
