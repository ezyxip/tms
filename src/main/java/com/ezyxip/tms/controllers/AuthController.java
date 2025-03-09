package com.ezyxip.tms.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezyxip.tms.data.AuthRequest;
import com.ezyxip.tms.entities.UserEntity;
import com.ezyxip.tms.services.JwtProvider;
import com.ezyxip.tms.services.UserService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserEntity user) {
        userService.createUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request) {
        UserEntity user = userService.findByEmail(request.email());
        if (user != null && userService.passwordEncoder.matches(request.password(), user.getPassword())) {
            String token = jwtTokenProvider.createToken(user.getEmail(), user.getRole().name());
            return ResponseEntity.ok(token);
        } else {
            throw new RuntimeException("Invalid email or password");
        }
    }

    @GetMapping("/test")
    public String getMethodName() {
        return "Test123";
    }
    
}

