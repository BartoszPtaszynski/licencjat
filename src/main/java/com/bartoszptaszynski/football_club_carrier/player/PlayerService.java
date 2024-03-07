package com.bartoszptaszynski.football_club_carrier.player;


import com.bartoszptaszynski.football_club_carrier.player.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class PlayerService implements UserDetailsService {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public PlayerService(PlayerRepository playerRepository, PasswordEncoder passwordEncoder) {
        this.playerRepository = playerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Player loadPlayerByEmail(String email) {
        return  playerRepository.findPlayerByEmail(email).orElseThrow(()->new UserNotFoundException("User not founded"));
    }

    public ResponseEntity<?> register(PlayerRegistrationCommand command)
    {
        if(playerRepository.existsByUsername(command.getUsername())) {
            return new ResponseEntity<>("user not found", HttpStatus.BAD_REQUEST);
        }
        Player player = new Player(
                command.getEmail(),command.getUsername(),passwordEncoder.encode(command.getPassword())
        );
        playerRepository.save(player);

        return new ResponseEntity<>("registered",HttpStatus.CREATED);
    }

    public ResponseEntity<?> login(PlayerLoginCommand command) {
        try {
            var authToken = new UsernamePasswordAuthenticationToken(command.getUsername(), command.getPassword());
            SecurityContextHolder.getContext().setAuthentication(this.authenticationManager.authenticate(authToken));
            log.info("Login success for user {}", command.getUsername());
            return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
        }catch (Exception exception) {
            log.warn("login FAILED for user {}",command.getUsername());
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Player loadUserByUsername(String username) throws UsernameNotFoundException {
        return playerRepository.findPlayerByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
    }
}
