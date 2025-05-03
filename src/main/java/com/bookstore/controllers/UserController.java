package com.bookstore.controllers;


import com.bookstore.entity.User;
import com.bookstore.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/users")
        public List<User> getAllUsers() {
            return userService.getAllUsers();
    }


    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }
//
//    // show registration form
//    @GetMapping("/register")
//    public String showRegistrationForm(Model model){
//        model.addAttribute("UserResponseDto",new UserResponseDto());
//        return "register";
//    }
//
//    //process registration
//    @PostMapping("/register")
//    public String registerUser(
//            @Valid @ModelAttribute("UserResponseDto") UserResponseDto UserResponseDto,
//            BindingResult bindingResult,
//            Model model){
//        // if validation error
//        if(bindingResult.hasErrors()){
//            //show register form again
//            return "register";
//        }
//        try{
//            userService.registerUser(UserResponseDto);
//        }catch (RuntimeException exception){
//            model.addAttribute("emailError",exception.getMessage());
//            return "register";
//        }
//        // After successful sign‑up, redirect to login page
//        return "redirect:/login?registered";
//    }
//
//    @GetMapping("/login")
//    public String showLoginForm(@RequestParam(value = "error", required = false) String error,
//                                @RequestParam(value = "logout", required = false) String logout,
//                                @RequestParam(value = "registered", required = false) String registered,
//                                Model model){
//        model.addAttribute("loginDto", new LoginDTO());
//
//        if (error != null)   model.addAttribute("loginError", "Invalid credentials");
//        if (logout != null)  model.addAttribute("logoutMsg", "You have been logged out");
//        if (registered != null) model.addAttribute("regMsg", "Registration successful. Please log in.");
//
//        return "login";
//    }
//
//    @PostMapping("/login")
//    public String loginUser(
//            @Valid @ModelAttribute("loginDto") LoginDTO loginDto,
//            BindingResult bindingResult,
//            Model model){
//
//        if(bindingResult.hasErrors()){
//            return "login";
//        }
//        if (!userService.authenticateUser(loginDto)) {
//            model.addAttribute("loginError", "Invalid email or password.");
//            return "login";
//        }
//        return "redirect:/books";
//    }
//
//    @GetMapping("/error/access-denied")
//    public String accessDenied(){
//        return "accessDenied";
//    }
}
