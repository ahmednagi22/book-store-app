package com.bookstore.dto;

import com.bookstore.enums.Role;

public record UserResponseDto(
        Long id,
        String fullName,
        String email,
        String phoneNumber,
        String address,
        Role role){
}
