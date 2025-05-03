package com.bookstore.dto;


public record UserResponseDto(
         Long id,
         String fullName,
         String email,
         String phoneNumber,
         String address) {
}
