package com.bookstore.controller;

import com.bookstore.dto.UserDTO;
import com.bookstore.entity.User;
import com.bookstore.service.BookService;
import com.bookstore.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String loginForm(){
        return "login";
    }

    @PostMapping("/login")
    public String login(){

        return "redirect:/books";
    }
}
