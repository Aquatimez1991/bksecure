package com.bksecure.bksecure.service;

import com.bksecure.bksecure.domain.dto.MessageRequest;
import com.bksecure.bksecure.domain.model.Message;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class MessageService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Message processMessage(MessageRequest request) {
        String n8nUrl = "http://localhost:5678/webhook/mensaje";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> payload = Map.of(
                "sender", request.getSender(),
                "content", request.getContent()
        );

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(payload, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(n8nUrl, entity, String.class);

            // Deserializar el JSON devuelto por n8n
            Map<String, Object> responseMap = objectMapper.readValue(response.getBody(), new TypeReference<>() {});
            String reply = responseMap.getOrDefault("response", "Sin respuesta").toString();

            return new Message(request.getSender(), request.getContent(), reply);

        } catch (Exception e) {
            // Manejo básico de errores
            return new Message(request.getSender(), request.getContent(), "Error al procesar el mensaje: " + e.getMessage());
        }
    }
}