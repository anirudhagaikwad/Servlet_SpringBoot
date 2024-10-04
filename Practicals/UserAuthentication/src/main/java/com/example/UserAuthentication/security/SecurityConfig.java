package com.example.UserAuthentication.security;

import com.example.UserAuthentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.stereotype.Component;

@Component
public class SecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login", "/register").permitAll() // Public URLs
                .anyRequest().authenticated() // Protect all other URLs
            )
            .formLogin(form -> form
                .loginPage("/login") // Custom login page
                .defaultSuccessUrl("/home", true) // Redirect to home after successful login
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout") // URL for logout
                .logoutSuccessUrl("/login?logout") // Redirect after logout
                .permitAll()
            )
            .sessionManagement(session -> session
                .maximumSessions(1) // Allow only one session per user
                .maxSessionsPreventsLogin(false) // New login invalidates old session
            )
            .rememberMe(rememberMe -> rememberMe
                .tokenRepository(persistentTokenRepository()) // Set token repository
                .key("mySecretKey") // Secret key for the remember-me token
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userService; // Ensure UserService implements UserDetailsService
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        return new InMemoryTokenRepositoryImpl(); // Use in-memory token repository for remember-me
    }
}
