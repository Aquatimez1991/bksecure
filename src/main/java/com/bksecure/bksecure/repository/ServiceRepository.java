package com.bksecure.bksecure.repository;

import com.bksecure.bksecure.domain.model.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
    List<ServiceEntity> findByCategory(String category);
}