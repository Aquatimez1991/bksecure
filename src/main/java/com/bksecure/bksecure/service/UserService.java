package com.bksecure.bksecure.service;

import com.bksecure.bksecure.domain.model.User;
import com.bksecure.bksecure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Actualizamos solo si vienen datos
        if (userDetails.getName() != null) user.setName(userDetails.getName());
        if (userDetails.getLastname() != null) user.setLastname(userDetails.getLastname());
        if (userDetails.getPhone() != null) user.setPhone(userDetails.getPhone());
        if (userDetails.getAddress() != null) user.setAddress(userDetails.getAddress());

        return userRepository.save(user);
    }

    // Método auxiliar para obtener usuario (si lo necesitas)
    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}