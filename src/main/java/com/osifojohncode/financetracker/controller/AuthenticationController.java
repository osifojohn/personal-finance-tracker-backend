package com.osifojohncode.financetracker.controller;

import com.osifojohncode.financetracker.dto.AuthenticationRequest;
import com.osifojohncode.financetracker.dto.AuthenticationResponse;
import com.osifojohncode.financetracker.dto.RegisterRequest;
import com.osifojohncode.financetracker.error.BadCredentialsException;
import com.osifojohncode.financetracker.error.UserAlreadyExistsException;
import com.osifojohncode.financetracker.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) throws UserAlreadyExistsException {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) throws BadCredentialsException {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
