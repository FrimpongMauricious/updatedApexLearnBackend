package com.group.appdev.Service;

import com.group.appdev.config.UserAlreadyExistsException;
import com.group.appdev.model.Users;
import com.group.appdev.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(Users user){
        if (repo.findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Username already exists: " + user.getUsername());
        }

        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public String verify(Users user) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );

            // If authentication is successful (auth.isAuthenticated() will be true)
            if(auth.isAuthenticated()){
                return jwtService.generateToken(user.getUsername());
            }

            // This line is technically unreachable if authenticate() succeeds,
            // but included for completeness.
            return "Failed: Authentication check after successful auth object creation";

        } catch (AuthenticationException e) {

            // 1. Username (email) not found
            // 2. Password is wrong

            // Spring Security throws exceptions like BadCredentialsException or
            // UsernameNotFoundException (depending on the internal process).

            // When caught here, we know authentication failed.
            // The service returns "Failed" to indicate this.
            return "Failed: Authentication failed due to incorrect credentials or user not found.";
        }
    }
    }

