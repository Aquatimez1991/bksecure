package com.bksecure.bksecure.repository;

import com.bksecure.bksecure.domain.model.CartItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;


import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserId(Long userId);
    Optional<CartItem> findByUserIdAndServiceId(Long userId, Long serviceId);

    Optional<CartItem> findByUser_IdAndService_Id(Long userId, Long serviceId);

    @Transactional
    @Modifying
    void deleteByUser_IdAndService_Id(Long userId, Long serviceId);

    void deleteByUserId(Long userId);
}