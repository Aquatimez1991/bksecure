package com.bksecure.bksecure.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "services")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(columnDefinition = "text")
    private String description;
    private String category;
    private Double priceSoles;
    private String address;
    private String district;
    @Column(name = "image_url")
    private String imageUrl;
}