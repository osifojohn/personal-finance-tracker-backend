package com.osifojohncode.financetracker.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo-controller")
@Hidden
public class HelloController {
    @GetMapping
    public ResponseEntity<String> helloController(){
        return ResponseEntity.ok("Welcome to personal-finance-application");
    }
}
