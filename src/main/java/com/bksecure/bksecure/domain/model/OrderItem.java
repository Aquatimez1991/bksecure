package com.bksecure.bksecure.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference // Evita bucle infinito JSON
    private Order order;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceEntity service; // Asegúrate que se llame ServiceEntity en tu proyecto

    private Double priceSoles;
    private Integer quantity;
}