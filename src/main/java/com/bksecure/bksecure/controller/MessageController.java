package com.bksecure.bksecure.controller;

import com.bksecure.bksecure.domain.dto.HelpTriggerRequest;
import com.bksecure.bksecure.domain.model.Message;
import com.bksecure.bksecure.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/help") // Cambié la ruta para que sea más semántica
public class MessageController {

    @Autowired
    private MessageService service;

    @PostMapping("/trigger")
    public Message triggerHelp(@RequestBody HelpTriggerRequest request) {
        return service.triggerHelpBot(request);
    }
}