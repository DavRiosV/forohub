package com.foro.forohub.controller;

import com.foro.forohub.dto.LoginRequest;
import com.foro.forohub.dto.LoginResponse;
import com.foro.forohub.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping

public class AuthController {

    private final AuthenticationManager authManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authManager, TokenService tokenService) {
        this.authManager = authManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        String token = tokenService.generarToken(auth.getName());
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
