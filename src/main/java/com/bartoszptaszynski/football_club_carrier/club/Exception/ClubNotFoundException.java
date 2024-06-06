package com.bartoszptaszynski.football_club_carrier.club.Exception;

public class ClubNotFoundException extends RuntimeException{
    public ClubNotFoundException(String message) {
        super("club with "+message + " not found");
    }
}
