package com.bartoszptaszynski.football_club_carrier.club.repository;

import com.bartoszptaszynski.football_club_carrier.club.model.entity.ClubFootballers;
import com.bartoszptaszynski.football_club_carrier.footballer.model.entity.Footballer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubFootballersRepository extends JpaRepository<ClubFootballers,Long> {
    @Query("select avg(clubFootballer.footballer.rating ) from " +
            "club_footballers clubFootballer where clubFootballer.club.id=:id and clubFootballer.position <> 'R' ")
    public int getRatingOfSquad(@Param("id") Long id);

    @Query("select clubFootballer.footballer from " +
            "club_footballers clubFootballer where clubFootballer.club.id=:id ")
    public List<Footballer> allClubFootballers(@Param("id") Long id);
}
