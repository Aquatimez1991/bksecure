package com.bksecure.bksecure.domain.dto;

import lombok.Data;

@Data
public class MessageRequest {
    private String sender;
    private String content;
}