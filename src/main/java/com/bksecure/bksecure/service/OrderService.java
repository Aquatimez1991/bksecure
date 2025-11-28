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

import java.util.ArrayList;
import java.util.List;

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
        var user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        var items = cartRepo.findByUserId(userId);

        if (items.isEmpty()) {
            throw new RuntimeException("El carrito está vacío");
        }

        double total = items.stream()
                .mapToDouble(i -> i.getService().getPriceSoles() * i.getQuantity())
                .sum();

        Order order = new Order();
        order.setUser(user);
        order.setTotalSoles(total);
        // Guardamos la orden para generar el ID
        order = orderRepo.save(order);

        List<OrderItem> orderItemsList = new ArrayList<>();

        for (CartItem ci : items) {
            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setService(ci.getService());
            oi.setPriceSoles(ci.getService().getPriceSoles());
            oi.setQuantity(ci.getQuantity());

            // Guardamos el item
            orderItemRepo.save(oi);
            orderItemsList.add(oi);
        }

        // Asignamos la lista a la orden para retornarla completa al frontend
        order.setItems(orderItemsList);

        // Clear cart
        cartRepo.deleteByUserId(userId);
        return order;
    }

    // NUEVO MÉTODO
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepo.findByUserIdOrderByCreatedAtDesc(userId);
    }
}