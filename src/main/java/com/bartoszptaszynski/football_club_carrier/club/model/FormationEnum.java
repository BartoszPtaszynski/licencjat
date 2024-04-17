package com.bartoszptaszynski.football_club_carrier.club.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum FormationEnum {
    _1_4_4_2("1-4-4-2",List.of("BR","LO","LŚO","PŚO","PO","LP","LŚP","PŚP","PP","LN","PN")),
    _1_4_3_3("1-4-3-3",List.of("BR","LO","LŚO","PŚO","PO","LS","LŚP","PŚP","PS","N","ŚP")),
    _1_3_5_2("1-3-5-2",List.of("BR","LśO","ŚO","PŚO","PP","LP","LŚP","PŚP","ŚP","LN","PN"));


    private final String name;

    private final List<String> listOfPositions;
     FormationEnum( String name,List<String> listOfPositions) {
         this.listOfPositions = listOfPositions;
         this.name = name;
    }

    public static List<Map<String,Object>> getFormations() {
        return Stream.of(values())
                .map(formation-> Map.ofEntries(
                        Map.entry("code",formation.toString()),
                        Map.entry("name",formation.name),
                        Map.entry("listOfPositions", formation.listOfPositions
                ))).collect(Collectors.toList());
     }

}
