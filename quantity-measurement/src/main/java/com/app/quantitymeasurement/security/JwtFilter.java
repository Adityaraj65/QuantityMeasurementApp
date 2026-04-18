package com.app.quantitymeasurement.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        // ONLY try to authenticate if the header exists and starts with Bearer
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            try {
                String email = JwtUtil.extractEmail(token);
                if (email != null) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            email, null, Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception e) {
                // If token is invalid, we don't block the whole app, 
                // Spring Security will block private routes automatically later
                System.out.println("JWT Validation failed: " + e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }
}