package com.casey.bankingapi.controller;

import com.casey.bankingapi.utility.JwtIssuer;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final AuthenticationManager authenticationManager;
    private final JwtIssuer jwtIssuer;

    public AuthController(AuthenticationManager authenticationManager, JwtIssuer jwtIssuer) {
        this.authenticationManager = authenticationManager;
        this.jwtIssuer = jwtIssuer;
    }

}
