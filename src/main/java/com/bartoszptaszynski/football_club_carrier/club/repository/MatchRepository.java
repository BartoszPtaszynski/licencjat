package com.bartoszptaszynski.football_club_carrier.club.repository;

import com.bartoszptaszynski.football_club_carrier.club.model.MatchDto;
import com.bartoszptaszynski.football_club_carrier.club.model.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match,Long> {
//    @Query("SELECT match from matches  match where match.hostClub.id = :id OR match.guestClub = :id ")
//    public List<Match> getMatchesByClubId(@Param("id") Long id);

    @Query("SELECT match from matches  match where match.hostClub.id = :id OR match.guestClub.id = :id  order by match.id desc limit 5 ")
    public List<Match> getLast5Matches(@Param("id") Long id);
    @Query("SELECT " +
            "CASE " +
            "when ((match.guestClub.id = :id and match.guestTeamScore > match.hostTeamScore) or (match.hostClub.id = :id and match.hostTeamScore > match.guestTeamScore)) then 'W'" +
            "when ((match.guestClub.id = :id and match.guestTeamScore < match.hostTeamScore) or (match.hostClub.id = :id and match.hostTeamScore < match.guestTeamScore)) then 'L' " +
            "ELSE 'D'" +
            "end " +
            "from matches  match " +
            "where match.hostClub.id = :id OR match.guestClub.id = :id  order by match.id desc limit 5 ")
    public List<String> getLastMatches(@Param("id") Long id);

    @Query("select match " +
            "from matches  match where match.hostClub.id= :clubId or match.guestClub.id=:clubId " +
            "order by match.id desc")
    List<Match> getResults(@Param("clubId")Long clubId);
}
