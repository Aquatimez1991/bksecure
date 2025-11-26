package com.bksecure.bksecure.controller;

import com.bksecure.bksecure.domain.dto.MessageRequest;
import com.bksecure.bksecure.domain.model.Message;
import com.bksecure.bksecure.service.MessageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @PostMapping
    public Message receiveMessage(@RequestBody MessageRequest request) {
        return service.processMessage(request);
    }

    @GetMapping("/ping")
    public String ping() {
        return "Backend activo";
    }
}