package com.bartoszptaszynski.football_club_carrier.footballer;

public class FootballerNotFoundException extends RuntimeException{
    public FootballerNotFoundException(Long id) {
        super("footballer with id="+id+" not found");
    }
}
