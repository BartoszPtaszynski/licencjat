package com.bartoszptaszynski.football_club_carrier.club.repository;

import com.bartoszptaszynski.football_club_carrier.club.model.entity.ClubFootballers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubFootballersRepository extends JpaRepository<ClubFootballers,Long> {
}
