package com.app.auth_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.auth_service.entity.User; // Updated Import

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}