package com.bartoszptaszynski.football_club_carrier.player.exceptions;

public class FriendExistsException extends RuntimeException{
    public FriendExistsException() {
        super("players already are friends");
    }
}
