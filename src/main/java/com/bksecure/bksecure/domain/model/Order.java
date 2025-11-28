package com.bksecure.bksecure.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double totalSoles;
    private String status = "PENDING";
    private LocalDateTime createdAt = LocalDateTime.now();

    // NUEVO: Relación inversa para obtener los items de la orden
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference // Evita bucle infinito JSON
    private List<OrderItem> items;
}