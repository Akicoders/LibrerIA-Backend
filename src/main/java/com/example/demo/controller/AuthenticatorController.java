package com.example.demo.controller;

import com.example.demo.controller.dto.AuthCreateUserRequest;
import com.example.demo.controller.dto.AuthLoginRequest;
import com.example.demo.controller.dto.AuthResponse;
import com.example.demo.service.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticatorController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody @Valid AuthCreateUserRequest authCreateUser) {
        return new ResponseEntity<AuthResponse>(this.userDetailsService.createUser(authCreateUser),HttpStatus.CREATED );
    }
    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login (@RequestBody @Valid AuthLoginRequest userRequest){
        return new ResponseEntity<AuthResponse>(this.userDetailsService.loginUser(userRequest), HttpStatus.OK);
    }
}
