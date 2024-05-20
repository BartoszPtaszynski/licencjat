package com.bartoszptaszynski.football_club_carrier.player.repository;

import com.bartoszptaszynski.football_club_carrier.player.model.FriendInfo;
import com.bartoszptaszynski.football_club_carrier.player.model.PlayerInfo;
import com.bartoszptaszynski.football_club_carrier.player.model.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player,Long> {
    Optional<Player> findPlayerByEmail(String username);
    Optional<Player> findPlayerById(Long id);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    Optional<Player> findPlayerByUsername(String username);
    Optional<Player> findById(Long id);



//    @Query("select new com.bartoszptaszynski.football_club_carrier.player.model.FriendInfo(" +
//            "friend.id," +
//            "friend.username," +
//            "friend.club) " +
//            "from players  friend " +
//            "where friend.id in " +
//                "(select playerFriend.id from players player " +
//                "join player.playerFriends playerFriend where player.id = :id)")
//    List<FriendInfo> playerFriendsInfo(@Param("id") Long id);

    @Query("select player.playerFriends from players  player")
    List<Player> getPlayerFriends(@Param("id") Long id);


    @Query("select player from players  player where " +
            "case " +
            "when :searchType = 'username' then lower(player.username) " +
            "when :searchType = 'email' then lower(player.email) " +
            "when :searchType = 'clubName' then lower(player.club.name) " +
            "end " +
            "like CONCAT('%', lower(:pattern), '%') and " +
            "player.id<>:id")
    List<Player> getPLayerByPattern(@Param("id")  Long id,@Param("searchType") String searchType, @Param("pattern") String pattern);


}


