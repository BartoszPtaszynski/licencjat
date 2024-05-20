package com.bartoszptaszynski.football_club_carrier.club.repository;

import com.bartoszptaszynski.football_club_carrier.club.model.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

public interface MatchInterface extends JpaRepository<Match,Long> {
    @Query("SELECT match from matches  match where match.hostClub.id = :id OR match.guestClub = :id ")
    public List<Match> getMatchesByClubId(@Param("id") Long id);

    @Query("SELECT match from matches  match where match.hostClub.id = :id OR match.guestClub = :id limit 5")
    public List<Match> getLast5Matches(@Param("id") Long id);

}
