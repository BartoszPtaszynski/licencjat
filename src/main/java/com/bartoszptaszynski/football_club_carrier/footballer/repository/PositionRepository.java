package com.bartoszptaszynski.football_club_carrier.footballer.repository;

import com.bartoszptaszynski.football_club_carrier.footballer.model.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position,Long> {
    public Position findByShortcut(String shortcut);


    @Query("SELECT position from  positions position " +
            " where  position.shortcut IN :shortcuts")
    List<Position> findByListOfShortcut(@Param("shortcuts") List<String> shortcuts);
}
