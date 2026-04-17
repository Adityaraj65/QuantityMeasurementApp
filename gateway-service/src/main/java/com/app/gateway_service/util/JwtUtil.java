package com.app.gateway_service.util;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    // Minimum 32 characters for HS256 algorithm
    private static final String SECRET = "mysecretkeymysecretkeymysecretkeymysecretkey"; 
    private final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    // Used by Auth-Service during Login
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 Hours
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    // Used by Gateway to verify access
    public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token);
    }

    // Used by Gateway to pass email to Quantity Service
    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}