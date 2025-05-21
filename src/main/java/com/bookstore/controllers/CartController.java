package com.bookstore.controllers;

import com.bookstore.dto.AddToCartRequest;
import com.bookstore.entity.User;
import com.bookstore.service.BookService;
import com.bookstore.service.CartService;
import com.bookstore.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/api/cart")
public class CartController {


    private final CartService cartService;
    private final UserService userService;

    public CartController(CartService cartService,
                          BookService bookService,
                          UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@Valid @RequestBody AddToCartRequest request,
                                            Principal principal){
        cartService.addToCart(request,principal.getName());
        return ResponseEntity.ok("book added to cart");
    }

}
