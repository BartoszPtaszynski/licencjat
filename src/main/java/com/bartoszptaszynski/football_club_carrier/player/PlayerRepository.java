package com.bartoszptaszynski.football_club_carrier.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface PlayerRepository extends JpaRepository<Player,UUID> {
    Optional<Player> findPlayerByEmail(String username);

    boolean existsByUsername(String username);

    Optional<Player> findPlayerByUsername(String username);
}
