package com.casey.bankingapi.controller;

import com.casey.bankingapi.dto.LoginRequestDTO;
import com.casey.bankingapi.utility.JwtIssuer;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final AuthenticationManager authenticationManager;
    private final JwtIssuer jwtIssuer;

    public AuthController(AuthenticationManager authenticationManager, JwtIssuer jwtIssuer) {
        this.authenticationManager = authenticationManager;
        this.jwtIssuer = jwtIssuer;
    }

    @PostMapping("/login")
    // Returns header name and token value
    public Map<String, String> login(@RequestBody LoginRequestDTO request) {
        // Authenticate user
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        // Create token
        String token = jwtIssuer.issue(auth.getName(), auth.getAuthorities());

        return Map.of("token", token);
    }
    

}
