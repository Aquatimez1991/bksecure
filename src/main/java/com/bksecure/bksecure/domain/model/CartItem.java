package com.bksecure.bksecure.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart_items", uniqueConstraints = { @UniqueConstraint(columnNames = {"user_id", "service_id"}) })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceEntity service;

    private Integer quantity = 1;
}