package com.bartoszptaszynski.football_club_carrier.club.repository;

import com.bartoszptaszynski.football_club_carrier.club.model.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club, Long> {


    @Query("SELECT c FROM clubs c " +
            "WHERE c.league = :league AND c.id <> :clubId AND " +
            "(SELECT COUNT( cf.position.shortcut) FROM club_footballers cf WHERE cf.club = c AND cf.position.shortcut <> 'R') = 11 " +
            "ORDER BY RANDOM() " +
            "LIMIT 1")
    public Club findClubInTheSameLeagueWithElevenFootballers(@Param("league") int league, @Param("clubId") Long clubId);



}
