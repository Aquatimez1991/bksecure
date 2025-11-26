package com.bksecure.bksecure.service;

import com.bksecure.bksecure.domain.model.CartItem;
import com.bksecure.bksecure.domain.model.Order;
import com.bksecure.bksecure.domain.model.OrderItem;
import com.bksecure.bksecure.repository.CartItemRepository;
import com.bksecure.bksecure.repository.OrderItemRepository;
import com.bksecure.bksecure.repository.OrderRepository;
import com.bksecure.bksecure.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepo;
    @Autowired
    OrderItemRepository orderItemRepo;
    @Autowired
    CartItemRepository cartRepo;
    @Autowired
    UserRepository userRepo;

    @Transactional
    public Order checkout(Long userId) {
        var user = userRepo.findById(userId).orElseThrow();
        var items = cartRepo.findByUserId(userId);
        double total = items.stream()
                .mapToDouble(i -> i.getService().getPriceSoles() * i.getQuantity())
                .sum();

        Order order = new Order();
        order.setUser(user);
        order.setTotalSoles(total);
        order = orderRepo.save(order);

        for (CartItem ci : items) {
            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setService(ci.getService());
            oi.setPriceSoles(ci.getService().getPriceSoles());
            oi.setQuantity(ci.getQuantity());
            orderItemRepo.save(oi);
        }

        // Clear cart
        cartRepo.deleteByUserId(userId);
        return order;
    }
}

