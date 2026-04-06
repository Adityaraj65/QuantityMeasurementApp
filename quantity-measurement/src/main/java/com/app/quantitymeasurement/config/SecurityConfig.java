package com.app.quantitymeasurement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.quantitymeasurement.security.JwtFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())   // ✅ VERY IMPORTANT
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/auth/**").permitAll()   // login/register allowed
                    .anyRequest().authenticated()              // rest secured
            )
            .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}