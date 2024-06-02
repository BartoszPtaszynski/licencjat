package com.bartoszptaszynski.football_club_carrier.club.repository;

import com.bartoszptaszynski.football_club_carrier.club.model.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClubRepository extends JpaRepository<Club, Long> {


    @Query("select club from clubs club " +
            "where club.league = :league and club.id <> :clubId " +
            "order by RANDOM() " +
            "LIMIT 1")

    public Club findClubInTheSameLeague(@Param("league") int league, @Param("clubId") Long clubId);

}
