package com.bartoszptaszynski.football_club_carrier.club.repository;

import com.bartoszptaszynski.football_club_carrier.club.model.entity.Club;
import com.bartoszptaszynski.football_club_carrier.club.model.entity.ClubFootballers;
import com.bartoszptaszynski.football_club_carrier.footballer.model.FootballerClubDto;
import com.bartoszptaszynski.football_club_carrier.footballer.model.FootballerDto;
import com.bartoszptaszynski.football_club_carrier.footballer.model.entity.Footballer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClubFootballersRepository extends JpaRepository<ClubFootballers,Long> {
    @Query("select sum(clubFootballer.footballer.rating )/11 from " +
            "club_footballers clubFootballer where clubFootballer.club.id=:id and clubFootballer.position.shortcut <> 'R' ")
    public int getRatingOfSquad(@Param("id") Long id);

    @Query("select new com.bartoszptaszynski.football_club_carrier.footballer.model.FootballerClubDto(" +
            " clubFootballer.footballer, " +
            "clubFootballer.position)from " +
            "club_footballers clubFootballer where clubFootballer.club.id=:id ")
    public List<FootballerClubDto> allClubFootballers(@Param("id") Long id);

    @Query("select clubFootballer " +
         "from " +
            "club_footballers clubFootballer where clubFootballer.club.id=:id ")
    public List<ClubFootballers> clubFootballers(@Param("id") Long id);

    Optional<ClubFootballers> findByFootballerAndClub(Footballer footballer,Club club);

    @Query("select new com.bartoszptaszynski.football_club_carrier.footballer.model.FootballerClubDto(" +
            "clubFootballer.footballer, " +
            "clubFootballer.position )" +
            "from club_footballers  clubFootballer " +
            "where clubFootballer.club=:club and clubFootballer.footballer.id = :footballerId")
    Optional<FootballerClubDto> findByFootballerId(@Param("footballerId") Long footballerId, @Param("club") Club club);

@Query("select clubfootballer from club_footballers  clubfootballer " +
        "where clubfootballer.club.id = :clubId And clubfootballer.footballer.id = :footballerId")
    public ClubFootballers clubFootballer(@Param("clubId") Long clubId, @Param("footballerId") Long footballerId);


    boolean existsByFootballerAndClub(Footballer footballer, Club club);
//    @Query("select clubFootballer from club_footballers clubFootballer  where clubFootballer.footballer.id=:footballerId and clubFootballer.club.id=:clubId")
//    boolean existsByFootballerIdAndClubId(@Param("footballerId") Long footballerId, @Param("clubId") Long clubId);

}
