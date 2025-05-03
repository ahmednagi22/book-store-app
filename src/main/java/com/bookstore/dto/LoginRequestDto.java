package com.bookstore.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record LoginRequestDto (
        @Email(message = "Invalid Email Format")
        @NotBlank(message = "Email is Required")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters")
        String password){}
