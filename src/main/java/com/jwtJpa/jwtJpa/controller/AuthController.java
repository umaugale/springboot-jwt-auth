package com.jwtJpa.jwtJpa.controller;

import com.jwtJpa.jwtJpa.Entity.JwtRequest;
import com.jwtJpa.jwtJpa.Entity.JwtResponse;
import com.jwtJpa.jwtJpa.Entity.UserEntity;
import com.jwtJpa.jwtJpa.Repositoty.UserRepository;
import com.jwtJpa.jwtJpa.Security.JwtHelper;
import com.jwtJpa.jwtJpa.Service.UserRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper jwthelper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRegister userRegister;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest request) {
        try {
            doAuthenticate(request.getEmail(), request.getPassword());
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
            String token = jwthelper.generateToken(userDetails);

            JwtResponse response = JwtResponse.builder()
                    .jwtToken(token)
                    .username(userDetails.getUsername())
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid Credentials: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong: " + e.getMessage());
        }
    }

    private void doAuthenticate(String email, String password) {
        System.out.println("Trying to authenticate: " + email + " with password: " + password);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authToken);
            System.out.println("Authentication SUCCESS");
        } catch (BadCredentialsException e) {
            System.out.println("Authentication FAILED: " + e.getMessage());
            throw new BadCredentialsException("Invalid email or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserEntity userEntity){
        try {
           UserEntity userEntity1 = userRegister.registerUser(userEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(userEntity1);
        } catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
