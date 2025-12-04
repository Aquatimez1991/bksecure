package com.bksecure.bksecure.controller;

import com.bksecure.bksecure.domain.dto.DashboardStats;
import com.bksecure.bksecure.domain.model.Order;
import com.bksecure.bksecure.repository.OrderRepository;
import com.bksecure.bksecure.repository.ServiceRepository;
import com.bksecure.bksecure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired private OrderRepository orderRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private ServiceRepository serviceRepository;

    @GetMapping("/stats")
    public ResponseEntity<DashboardStats> getStats() {
        List<Order> orders = orderRepository.findAll();
        long totalOrders = orders.size();

        // CORRECCIÓN AQUÍ: Convertimos el Double a BigDecimal antes de sumar
        BigDecimal totalIncome = orders.stream()
                .map(order -> BigDecimal.valueOf(order.getTotalSoles()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        long totalClients = userRepository.count();
        long activeServices = serviceRepository.count();

        return ResponseEntity.ok(new DashboardStats(totalOrders, totalIncome, totalClients, activeServices));
    }
}