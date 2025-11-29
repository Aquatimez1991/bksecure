package com.bksecure.bksecure.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // --- NUEVOS CAMPOS ---
    private String lastname;
    private String phone;
    private String address;
    // ---------------------

    @Column(unique = true, nullable = false)
    private String email;

    // Si usas login con Google, el password podría ser opcional o un placeholder
    private String passwordHash;

    // Rol (opcional, si lo usas en el frontend)
    private String role = "customer";

    private LocalDateTime createdAt = LocalDateTime.now();
}