package com.bksecure.bksecure.repository;

import com.bksecure.bksecure.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> { }
