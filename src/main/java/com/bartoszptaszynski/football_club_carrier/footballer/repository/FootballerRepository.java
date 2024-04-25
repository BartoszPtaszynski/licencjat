package com.bartoszptaszynski.football_club_carrier.footballer.repository;

import com.bartoszptaszynski.football_club_carrier.footballer.model.entity.Footballer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FootballerRepository extends JpaRepository<Footballer,Long> {
//    @Query(value = "SELECT new Footballer(" +
//            "footballer.id, " +
//            "footballer.name, " +
//            "footballer.surname, " +
//            "footballer.rating, " +
//            "footballer.value) " +
//            "FROM Footballer footballer JOIN footballer.positionList position")
//    List<Footballer> getFootballers();

    @Query( "select footballer from footballers footballer JOIN footballer.footballerPositions positions " +
            "where footballer.rating <= 10 " +
            "and positions.shortcut = :position " +
            "ORDER BY  RANDOM() " +
            "LIMIT 1"
    )
    Footballer findByPosition(@Param("position") String position);

}
