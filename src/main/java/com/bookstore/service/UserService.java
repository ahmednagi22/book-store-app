package com.bookstore.service;

import com.bookstore.dto.UserDTO;
import com.bookstore.entity.Book;
import com.bookstore.entity.User;
import com.bookstore.enums.Role;
import com.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(UserDTO userDTO) throws RuntimeException{
        // Check if user already exists
        if(userRepository.findByEmail(userDTO.getEmail()).isPresent()){
            throw new RuntimeException("Email already in use");
        }

        //create New User
        User user = new User();
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(Role.CUSTOMER);// default user is customer

        //save the user
        userRepository.save(user);
    }
}
