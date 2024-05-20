package com.bartoszptaszynski.football_club_carrier.footballer.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Getter
@Setter
@Entity(name = "footballers")
public class Footballer {
    @Id
    Long id;
    private String name;
    private String surname;
    private int rating;
    private int value;
   @ManyToMany
   @JoinTable(
           name = "footballer_positions",
           joinColumns = @JoinColumn(name = "footballer_id"),
           inverseJoinColumns = @JoinColumn(name = "position_id"))
    private List<Position> footballerPositions;


    @Override
    public String toString() {
         StringBuilder s =new StringBuilder();
                 s.append("Footballer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", rating=" + rating +
                ", value=" + value +"positions: ");

        String s1 = footballerPositions.stream().map(Position::getNameOfPosition).collect(Collectors.joining(" "));

         return s.toString()+s1;
    }
}

