package com.bartoszptaszynski.football_club_carrier.service;

import com.bartoszptaszynski.football_club_carrier.model.Club;
import com.bartoszptaszynski.football_club_carrier.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClubService {
    @Autowired
    private ClubRepository clubRepository;

    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public void createClub(Club club) {

        clubRepository.save(club);
    }

}
