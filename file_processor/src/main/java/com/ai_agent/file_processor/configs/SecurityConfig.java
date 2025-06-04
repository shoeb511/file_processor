package com.ai_agent.file_processor.configs;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.SecretKey;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityconfig(HttpSecurity http) throws Exception {

        http.cors().disable();
        http.csrf().disable();
        http.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests.anyRequest().permitAll());
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecretKey secretKey(@Value("${jwt.secret}") String secret) {

        byte[] keyBytes = secret.getBytes();

        if(keyBytes.length < 32){
            throw new IllegalArgumentException("Secret key must be at least 32 bytes");
        }

        return Keys.hmacShaKeyFor(keyBytes);
    }
}
