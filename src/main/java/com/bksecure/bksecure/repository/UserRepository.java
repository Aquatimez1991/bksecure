package com.bksecure.bksecure.repository;

import com.bksecure.bksecure.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // Añade este método
}