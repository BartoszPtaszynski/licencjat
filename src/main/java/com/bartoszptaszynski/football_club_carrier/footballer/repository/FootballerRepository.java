package com.bartoszptaszynski.football_club_carrier.footballer.repository;

import com.bartoszptaszynski.football_club_carrier.footballer.model.entity.Footballer;
import com.bartoszptaszynski.football_club_carrier.footballer.model.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FootballerRepository extends JpaRepository<Footballer,Long> {
    @Query( "select footballer from footballers footballer JOIN footballer.footballerPositions positions " +
            "where footballer.rating <= 10 " +
            "and positions.shortcut = :position " +
            "ORDER BY  RANDOM() " +
            "LIMIT 1"
    )
    Footballer findByPosition(@Param("position") String position);

    @Query("SELECT footballer FROM footballers footballer " +
            "JOIN footballer.footballerPositions positions " +
            "WHERE footballer.value >= :priceFrom AND footballer.value <= :priceTo AND footballer.rating >= :ratingFrom AND footballer.rating <= :ratingTo " +
            "AND ( :positionId = 0  OR positions.id = :positionId) "+
            "ORDER BY footballer.rating DESC")
    List<Footballer> findByFilters(@Param("priceFrom") int priceFrom, @Param("priceTo") int priceTo, @Param("ratingFrom") int ratingFrom, @Param("ratingTo") int ratingTo, @Param("positionId") Long positionId);

}
