package com.bartoszptaszynski.football_club_carrier.footballer.model.entity;

import com.bartoszptaszynski.football_club_carrier.footballer.model.entity.Footballer;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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
    @ManyToMany
    Set<Footballer> footballers;
}
