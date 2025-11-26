package com.bksecure.bksecure.repository;

import com.bksecure.bksecure.domain.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserId(Long userId);
    Optional<CartItem> findByUserIdAndServiceId(Long userId, Long serviceId);
    void deleteByUserId(Long userId);
}