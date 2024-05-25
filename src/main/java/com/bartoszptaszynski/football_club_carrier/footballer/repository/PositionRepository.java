package com.bartoszptaszynski.football_club_carrier.footballer.repository;

import com.bartoszptaszynski.football_club_carrier.footballer.model.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position,Long> {
    public Position findByShortcut(String shortcut);
}
