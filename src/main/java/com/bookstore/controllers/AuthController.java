package com.bookstore.controllers;


import com.bookstore.dto.LoginRequestDto;
import com.bookstore.dto.LoginResponseDto;
import com.bookstore.dto.RegisterRequestDto;
import com.bookstore.dto.UserResponseDto;
import com.bookstore.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public UserResponseDto registerUser(@Valid @RequestBody RegisterRequestDto registerRequestDto) {

        UserResponseDto userResponseDto = authService.register(registerRequestDto);

        return userResponseDto;
    }

    @PostMapping("/login")
    public LoginResponseDto login(@Valid @RequestBody LoginRequestDto loginRequestDto) {

        LoginResponseDto loginResponseDto = authService.login(loginRequestDto);

        return loginResponseDto;

    }


}
