package com.bartoszptaszynski.football_club_carrier.player;


import com.bartoszptaszynski.football_club_carrier.player.exceptions.UserNotFoundException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;


    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player loadPlayerByEmail(String email) {
        return  playerRepository.findPlayerByEmail(email).orElseThrow(()->new UserNotFoundException("User not founded"));
    }

    public void addPlayer(Player player)
    {
        playerRepository.save(player);
    }

    public void login(PlayerLoginReq playerReq) {
        Player player = playerRepository.findPlayerByEmail(playerReq.getUsername()).orElseThrow(()->new UserNotFoundException("User not founded"));;
        if(player.getPassword().equals(playerReq.getPassword())) {
            log.info(player.getId() + "logged");

        }
        else {
            log.warn("Invalid data");
        }
        //  HttpSession session = null;
        //session.setAttribute("client",player.getId());
    }
}
