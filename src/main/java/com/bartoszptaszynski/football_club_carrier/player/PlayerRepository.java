package com.bartoszptaszynski.football_club_carrier.player;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PlayerRepository extends JpaRepository<Player,UUID> {
    Optional<Player> findPlayerByEmail(String username);
    Optional<Player> findPlayerById(UUID id);

    boolean existsByUsername(String username);

    Optional<Player> findPlayerByUsername(String username);
}
