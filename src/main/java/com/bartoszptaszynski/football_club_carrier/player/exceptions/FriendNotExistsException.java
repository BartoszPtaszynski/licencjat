package com.bartoszptaszynski.football_club_carrier.player.exceptions;

public class FriendNotExistsException extends RuntimeException{
    public FriendNotExistsException() {
        super("players are not friends");
    }
}
