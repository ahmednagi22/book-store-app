package com.bookstore.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AddToCartRequest(
        @NotNull
        Long bookId,
        @Min(1)
        int quantity
){}
