package com.bartoszptaszynski.football_club_carrier.player.repository;

import com.bartoszptaszynski.football_club_carrier.player.model.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player,Long> {
    Optional<Player> findPlayerByEmail(String username);
    Optional<Player> findPlayerById(Long id);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    Optional<Player> findPlayerByUsername(String username);
}
