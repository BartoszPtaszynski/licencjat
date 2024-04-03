package com.bartoszptaszynski.football_club_carrier.model;

import com.bartoszptaszynski.football_club_carrier.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long> {
}
