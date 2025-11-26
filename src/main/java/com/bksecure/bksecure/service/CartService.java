package com.bksecure.bksecure.service;

import com.bksecure.bksecure.domain.model.CartItem;
import com.bksecure.bksecure.repository.CartItemRepository;
import com.bksecure.bksecure.repository.ServiceRepository;
import com.bksecure.bksecure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    CartItemRepository cartRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    ServiceRepository serviceRepo;

    public List<CartItem> getCart(Long userId) {
        return cartRepo.findByUserId(userId);
    }

    public CartItem addToCart(Long userId, Long serviceId, int qty) {
        var user = userRepo.findById(userId).orElseThrow();
        var service = serviceRepo.findById(serviceId).orElseThrow();

        var existing = cartRepo.findByUserIdAndServiceId(userId, serviceId);
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
}

