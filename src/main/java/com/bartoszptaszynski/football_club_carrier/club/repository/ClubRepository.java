package com.bartoszptaszynski.football_club_carrier.club.repository;

import com.bartoszptaszynski.football_club_carrier.club.model.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long> {
}
