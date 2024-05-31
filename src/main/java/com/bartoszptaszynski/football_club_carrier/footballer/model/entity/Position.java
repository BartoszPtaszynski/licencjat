package com.bartoszptaszynski.football_club_carrier.footballer.model.entity;

import com.bartoszptaszynski.football_club_carrier.footballer.model.entity.Footballer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
@Getter
@Setter
@Entity(name = "positions")

public class Position {
    @Id
    private Long id;
    private String nameOfPosition;
    private String shortcut;



    @Override
    public boolean equals(Object obj) {
        Position position =  (Position) obj;
        return this.getShortcut().equals(position.shortcut) && this.getNameOfPosition().equals(position.getNameOfPosition());
    }
}
