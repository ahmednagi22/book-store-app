//package com.bookstore.controllers;
//
//import com.bookstore.entity.User;
//import com.bookstore.service.BookService;
//import com.bookstore.service.CartService;
//import com.bookstore.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/cart")
//public class CartController {
//
//
//    private final CartService cartService;
//    private final UserService userService;
//
//    public CartController(CartService cartService,
//                          BookService bookService,
//                          UserService userService) {
//        this.cartService = cartService;
//        this.userService = userService;
//    }
//    // 🛒 Show cart items
//    @GetMapping
//    public String viewCart(@AuthenticationPrincipal UserDetails userDetails, Model model) {
//        User user = userService.findByEmail(userDetails.getUsername());
//        model.addAttribute("cartItems", cartService.getCartItems(user));
////        model.addAttribute("totalPrice", cartService.getTotalPrice(user));
////        model.addAttribute("totalItems", cartService.getTotalItems(user));
//        return "cart";
//    }
//
//    // ➕ Add item to cart
//    @PostMapping("/add")
//    public String addToCart(@RequestParam Long bookId,
//                            @RequestParam int quantity,
//                            @AuthenticationPrincipal UserDetails userDetails) {
//
//        User user = userService.findByEmail(userDetails.getUsername());
////        cartService.addItemToCart(bookId, quantity, user);
//        return "redirect:/cart";
//    }
//
//    // Update item quantity
//    @PostMapping("/update/{cartItemId}")
//    public String updateQuantity(@PathVariable Long cartItemId,
//                                 @RequestParam int quantity,
//                                 @AuthenticationPrincipal UserDetails userDetails) {
//
//        User user = userService.findByEmail(userDetails.getUsername());
////        cartService.updateQuantity(cartItemId, quantity, user);
//        return "redirect:/cart";
//    }
//
//    // ❌ Remove item from cart
//    @PostMapping("/remove/{cartItemId}")
//    public String removeItem(@PathVariable Long cartItemId,
//                             @AuthenticationPrincipal UserDetails userDetails) {
//
//        User user = userService.findByEmail(userDetails.getUsername());
////        cartService.removeItemFromCart(cartItemId, user);
//        return "redirect:/cart";
//    }
//}
