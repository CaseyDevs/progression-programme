package com.casey.bankingapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // APIs usually disable CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").authenticated() // block API routes
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults()) // simplest starter auth
                .build();
    }

    // Create a fake user for auth
    @Bean
    UserDetailsService users() {
        UserDetails user = User.withUsername("casey")
                .password("{noop}password")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

}

