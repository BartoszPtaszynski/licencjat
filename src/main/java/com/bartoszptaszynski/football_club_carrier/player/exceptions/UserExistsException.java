package com.bartoszptaszynski.football_club_carrier.player.exceptions;

public class UserExistsException extends Exception{
    public UserExistsException(String message) {
        super(message + " exists");
    }
}
