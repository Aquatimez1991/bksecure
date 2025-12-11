package com.bksecure.bksecure.controller;

import com.bksecure.bksecure.domain.model.ServiceEntity;
import com.bksecure.bksecure.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping
    public List<ServiceEntity> getAllServices() {
        return serviceRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceEntity> getServiceById(@PathVariable Long id) {
        return serviceRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ServiceEntity createService(@RequestBody ServiceEntity service) {
        // Ahora sí funcionará porque agregamos el campo a la entidad
        service.setCreatedAt(java.time.LocalDateTime.now());
        return serviceRepository.save(service);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceEntity> updateService(@PathVariable Long id, @RequestBody ServiceEntity serviceDetails) {
        return serviceRepository.findById(id)
                .map(service -> {
                    service.setTitle(serviceDetails.getTitle());
                    service.setDescription(serviceDetails.getDescription());
                    service.setPriceSoles(serviceDetails.getPriceSoles());
                    service.setCategory(serviceDetails.getCategory());

                    // CORREGIDO: Usamos getImageUrl y setImageUrl
                    service.setImageUrl(serviceDetails.getImageUrl());

                    service.setAddress(serviceDetails.getAddress());
                    service.setDistrict(serviceDetails.getDistrict());

                    // Opcional: Actualizar fecha de modificación si tuvieras ese campo
                    // service.setUpdatedAt(LocalDateTime.now());

                    return ResponseEntity.ok(serviceRepository.save(service));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteService(@PathVariable Long id) {
        return serviceRepository.findById(id)
                .map(service -> {
                    serviceRepository.delete(service);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}