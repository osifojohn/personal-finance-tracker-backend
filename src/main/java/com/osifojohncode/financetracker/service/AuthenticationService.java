package com.osifojohncode.financetracker.service;

import com.osifojohncode.financetracker.config.JwtService;
import com.osifojohncode.financetracker.dto.AuthenticationRequest;
import com.osifojohncode.financetracker.dto.AuthenticationResponse;
import com.osifojohncode.financetracker.dto.RegisterRequest;
import com.osifojohncode.financetracker.entity.user.Role;
import com.osifojohncode.financetracker.entity.user.User;
import com.osifojohncode.financetracker.entity.user.UserRepository;
import com.osifojohncode.financetracker.error.BadCredentialsException;
import com.osifojohncode.financetracker.error.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
 private  final PasswordEncoder passwordEncoder;
 private final UserRepository repository;

 private final JwtService jwtService;
 private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest request) throws UserAlreadyExistsException {
        boolean isEmailTaken=repository.existsByEmail(request.getEmail());
        if (isEmailTaken) {
            throw new UserAlreadyExistsException("Email already taken");
        }

        var user = User
                .builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws BadCredentialsException{
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Wrong email or password"));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
