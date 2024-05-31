package com.bartoszptaszynski.football_club_carrier.footballer.expetion;

public class FootballerHasNotThatPosition extends RuntimeException{
    public FootballerHasNotThatPosition(String position) {
        super("footballer hasn't got this position:"+position);
    }
}
