package com.bksecure.bksecure.service;

import com.bksecure.bksecure.domain.dto.GoogleLoginRequest;
import com.bksecure.bksecure.domain.model.User;
import com.bksecure.bksecure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public User loginOrRegister(GoogleLoginRequest request) {
        // Busca si el usuario ya existe por su email
        return userRepository.findByEmail(request.getEmail())
                .orElseGet(() -> {
                    // Si no existe, créalo
                    User newUser = new User();
                    newUser.setName(request.getName());
                    newUser.setEmail(request.getEmail());
                    // Le asignamos un password por defecto, ya que la columna no puede ser nula.
                    // En un sistema real, aquí manejarías la seguridad de otra forma.
                    newUser.setPasswordHash("google_user");
                    return userRepository.save(newUser);
                });
    }
}