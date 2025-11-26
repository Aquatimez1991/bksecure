package com.bksecure.bksecure.controller;

import com.bksecure.bksecure.domain.model.ServiceEntity;
import com.bksecure.bksecure.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {
    @Autowired
    ServiceRepository serviceRepo;

    @GetMapping
    public List<ServiceEntity> listAll() {
        return serviceRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceEntity> getById(@PathVariable Long id) {
        return serviceRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

