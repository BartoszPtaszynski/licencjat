package com.bartoszptaszynski.football_club_carrier.player;

import com.bartoszptaszynski.football_club_carrier.model.Club;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerInfo {
    private Long id;
    private String username;
    private String email;
    private Long clubId;
}
