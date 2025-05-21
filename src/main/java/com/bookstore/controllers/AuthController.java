package com.bookstore.controllers;


import com.bookstore.dto.LoginRequestDto;
import com.bookstore.dto.RegisterRequestDto;
import com.bookstore.dto.UserResponseDto;
import com.bookstore.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody RegisterRequestDto registerRequestDto) {

        return  ResponseEntity.status(HttpStatus.CREATED)
                        .body(authService.register(registerRequestDto));

    }


//    @PostMapping("/login")
//    public ResponseEntity<UserResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
//
//        return ResponseEntity.ok(
//                authService.login(loginRequestDto)
//        );
//
//    }
    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

}
