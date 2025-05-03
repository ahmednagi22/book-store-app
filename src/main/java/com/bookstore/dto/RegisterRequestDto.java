package com.bookstore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDto (

        @NotBlank(message = "Full name is required")
        String fullName,

        @Email(message = "Invalid email")
        @NotBlank(message = "Email is required")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters")
        String password,

        @NotBlank(message = "Phone Number is required")
        String phoneNumber,

        @NotBlank(message = "address Can't Be Empty")
        @Size(min = 6, message = "Too Short For Address")
        String address
){}
