package com.bksecure.bksecure.controller;

import com.bksecure.bksecure.domain.dto.GoogleLoginRequest;
import com.bksecure.bksecure.domain.model.User;
import com.bksecure.bksecure.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login-or-register")
    public User loginOrRegister(@RequestBody GoogleLoginRequest request) {
        return authService.loginOrRegister(request);
    }
}