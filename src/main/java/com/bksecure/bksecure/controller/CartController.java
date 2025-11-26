package com.bksecure.bksecure.controller;

import com.bksecure.bksecure.domain.model.CartItem;
import com.bksecure.bksecure.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping("/{userId}")
    public List<CartItem> getCart(@PathVariable Long userId) {
        return cartService.getCart(userId);
    }

    @PostMapping("/add")
    public CartItem add(@RequestBody Map<String, Object> body) {
        Long userId = Long.valueOf(body.get("userId").toString());
        Long serviceId = Long.valueOf(body.get("serviceId").toString());
        int qty = body.get("quantity") != null ? Integer.parseInt(body.get("quantity").toString()) : 1;
        return cartService.addToCart(userId, serviceId, qty);
    }

    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<?> clear(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok().build();
    }
}
