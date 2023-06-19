package com.osifojohncode.financetracker.controller;

import com.osifojohncode.financetracker.dto.AuthenticationRequest;
import com.osifojohncode.financetracker.dto.AuthenticationResponse;
import com.osifojohncode.financetracker.dto.RegisterRequest;
import com.osifojohncode.financetracker.error.BadCredentialsException;
import com.osifojohncode.financetracker.error.UserAlreadyExistsException;
import com.osifojohncode.financetracker.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication and Login")
public class AuthenticationController {

    private final AuthenticationService service;
    @Operation(
            description = "Post endpoint for Register",
            summary = "This is a summary for Register post endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Email already taken",
                            responseCode = "409"
                    )
            }

    )
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) throws UserAlreadyExistsException {
        return ResponseEntity.ok(service.register(request));
    }
    @Operation(
            description = "Post endpoint for Authenticate",
            summary = "This is a summary for Authentication post endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid token",
                            responseCode = "403"
                    )
            }

    )
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) throws BadCredentialsException {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
