package com.bksecure.bksecure.service;

import com.bksecure.bksecure.domain.model.CartItem;
import com.bksecure.bksecure.repository.CartItemRepository;
import com.bksecure.bksecure.repository.ServiceRepository;
import com.bksecure.bksecure.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CartService {

    @Autowired
    private CartItemRepository cartRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ServiceRepository serviceRepo;

    public List<CartItem> getCart(Long userId) {
        return cartRepo.findByUserId(userId);
    }

    public CartItem addToCart(Long userId, Long serviceId, int qty) {
        var user = userRepo.findById(userId).orElseThrow();
        var service = serviceRepo.findById(serviceId).orElseThrow();

        var existing = cartRepo.findByUser_IdAndService_Id(userId, serviceId);
        if (existing.isPresent()) {
            var item = existing.get();
            item.setQuantity(item.getQuantity() + qty);
            return cartRepo.save(item);
        } else {
            CartItem item = new CartItem();
            item.setUser(user);
            item.setService(service);
            item.setQuantity(qty);
            return cartRepo.save(item);
        }
    }

    public void clearCart(Long userId) {
        cartRepo.deleteByUserId(userId);
    }

    public void removeFromCart(Long userId, Long serviceId) {
        cartRepo.deleteByUser_IdAndService_Id(userId, serviceId);
    }

    public CartItem updateQuantity(Long userId, Long serviceId, int quantity) {
        CartItem item = cartRepo.findByUser_IdAndService_Id(userId, serviceId)
                .orElseThrow(() -> new RuntimeException("Item no encontrado"));
        item.setQuantity(quantity);
        return cartRepo.save(item);
    }
}
