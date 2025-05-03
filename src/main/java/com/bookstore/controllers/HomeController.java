package com.bookstore.controllers;


import com.bookstore.entity.User;
import com.bookstore.enums.Role;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @GetMapping({"/","/home"})
    public User home(){
        return new User(
                19L,
                "ahmed@example.com",
                "ahmed&159753",
                "ahmednagi",
                "01025656596",
                "11 of october",
                Role.CUSTOMER
        );
    }



}
