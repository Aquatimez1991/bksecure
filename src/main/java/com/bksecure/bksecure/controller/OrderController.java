package com.bksecure.bksecure.controller;

import com.bksecure.bksecure.domain.model.Order;
import com.bksecure.bksecure.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/checkout/{userId}")
    public Order checkout(@PathVariable Long userId) {
        return orderService.checkout(userId);
    }
}

