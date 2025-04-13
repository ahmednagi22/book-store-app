package com.bookstore.controller;


import com.bookstore.service.JWTService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/","/home"})
    public String home(){
//        JWTService jwtService = new JWTService();
//
//        return jwtService.generateToken("ahmed@example.com");
        return "home";
    }

}
