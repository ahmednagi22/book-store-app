package com.bookstore.controller;

import com.bookstore.dto.LoginDTO;
import com.bookstore.dto.UserDTO;
import com.bookstore.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // show registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("userDto",new UserDTO());
        return "register";
    }

    //process registration
    @PostMapping("/register")
    public String registerUser(
            @Valid @ModelAttribute("userDto") UserDTO userDto,
            BindingResult bindingResult,
            Model model){
        // if validation error
        if(bindingResult.hasErrors()){
            //show register form again
            return "register";
        }
        try{
            userService.registerUser(userDto);
        }catch (RuntimeException exception){
            model.addAttribute("emailError",exception.getMessage());
            return "register";
        }
        // After successful sign‑up, redirect to login page
        return "redirect:/login?registered";
    }

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "logout", required = false) String logout,
                                @RequestParam(value = "registered", required = false) String registered,
                                Model model){
        model.addAttribute("loginDto", new LoginDTO());

        if (error != null)   model.addAttribute("loginError", "Invalid credentials");
        if (logout != null)  model.addAttribute("logoutMsg", "You have been logged out");
        if (registered != null) model.addAttribute("regMsg", "Registration successful. Please log in.");

        return "login";
    }

    @PostMapping("/login")
    public String loginUser(
            @Valid @ModelAttribute("loginDto") LoginDTO loginDto,
            BindingResult bindingResult,
            Model model){

        if(bindingResult.hasErrors()){
            return "login";
        }
        if (!userService.authenticateUser(loginDto.getEmail(), loginDto.getPassword())) {
            model.addAttribute("loginError", "Invalid email or password.");
            return "login";
        }
        return "redirect:/books";
    }
}
