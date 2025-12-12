package com.bksecure.bksecure.service;

import com.bksecure.bksecure.domain.dto.HelpTriggerRequest;
import com.bksecure.bksecure.domain.model.Message;
import com.bksecure.bksecure.domain.model.Order;
import com.bksecure.bksecure.domain.model.OrderItem; // Importante
import com.bksecure.bksecure.domain.model.User;
import com.bksecure.bksecure.repository.OrderRepository;
import com.bksecure.bksecure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    private final String N8N_URL = "http://n8n:5678/webhook/9e43291a-54e0-4b4c-89b8-d51754d8c69e";

    public Message triggerHelpBot(HelpTriggerRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<Order> orders = orderRepository.findByUserIdOrderByCreatedAtDesc(user.getId());
        Order lastOrder = orders.isEmpty() ? null : orders.get(0);

        Map<String, Object> payload = new HashMap<>();

        // Datos Personales
        payload.put("user_id", user.getId());
        payload.put("full_name", user.getName() + " " + (user.getLastname() != null ? user.getLastname() : ""));
        payload.put("phone", user.getPhone());

        // Contexto de la Orden (ENRIQUECIDO)
        if (lastOrder != null) {
            payload.put("has_recent_order", true);
            payload.put("order_id", lastOrder.getId());
            payload.put("order_total", lastOrder.getTotalSoles());
            payload.put("order_status", lastOrder.getStatus()); // Ej: PENDING, COMPLETED

            // --- AQUÍ ESTÁ LA MAGIA ---
            // Convertimos la lista de objetos en un String legible para la IA
            // Ej: "DJ Profesional (x1), Torta Personalizada (x2)"
            String itemsSummary = lastOrder.getItems().stream()
                    .map(item -> item.getService().getTitle() + " (x" + item.getQuantity() + ")")
                    .collect(Collectors.joining(", "));

            payload.put("order_items_text", itemsSummary);
            // ---------------------------

        } else {
            payload.put("has_recent_order", false);
            payload.put("order_items_text", "Ninguno");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);

        try {
            restTemplate.postForEntity(N8N_URL, entity, String.class);
            return new Message("System", "Bot activado", "Solicitud enviada.");
        } catch (Exception e) {
            return new Message("System", "Error", "Error al contactar n8n");
        }
    }
}