package com.bksecure.bksecure.repository;

import com.bksecure.bksecure.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Busca por UserId y ordena por fecha de creación (el más reciente primero)
    List<Order> findByUserIdOrderByCreatedAtDesc(Long userId);
}