package com.bksecure.bksecure.domain.dto;

import lombok.Data;

@Data
public class HelpTriggerRequest {
    private Long userId; // Solo necesitamos el ID, el backend busca el resto
}