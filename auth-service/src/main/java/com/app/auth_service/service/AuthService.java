package com.app.auth_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.auth_service.entity.User;
import com.app.auth_service.repository.UserRepository;
import com.app.auth_service.security.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private JwtUtil jwtUtil;

    // Regex for: 1 Upper, 1 Lower, 1 Digit, Min 7 Chars
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{7,}$";

    public String register(User user) {
        // 1. Validate Password Strength
        if (!user.getPassword().matches(PASSWORD_PATTERN)) {
            throw new RuntimeException("Password must have at least 1 uppercase, 1 lowercase, 1 digit, and be min 7 characters long.");
        }
        
        // 2. Save User (Note: In production, always use BCrypt to encode password)
        repository.save(user);
        return "User registered successfully";
    }

    public String login(String email, String password) {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getPassword().equals(password)) {
            return jwtUtil.generateToken(email);
        } else {
            throw new RuntimeException("Invalid Credentials");
        }
    }
}