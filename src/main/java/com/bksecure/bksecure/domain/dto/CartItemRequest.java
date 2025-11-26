package com.bksecure.bksecure.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemRequest {
    private Long userId;
    private Long serviceId;
    private Integer quantity; // Opcional, para actualizar
}