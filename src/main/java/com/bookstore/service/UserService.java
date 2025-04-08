package com.bookstore.service;

import com.bookstore.dto.UserDTO;
import com.bookstore.exception.UserNotFoundException;
import org.mindrot.jbcrypt.BCrypt;
import com.bookstore.entity.User;
import com.bookstore.enums.Role;
import com.bookstore.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public void registerUser(UserDTO userDTO) throws RuntimeException{
        // Check if user already exists
        if(userRepository.findByEmail(userDTO.getEmail()).isPresent()){
            throw new RuntimeException("Email already in use!!!");
        }

        //create New User
        User user = new User();
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        // encode password using BcCrypt
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        user.setRole(Role.CUSTOMER);// default user is customer

        //save the user
        userRepository.save(user);
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
    public boolean authenticateUser(String email, String password){
        // get user by email
        User user = getUserByEmail(email);

        // check user password with stored password
        return passwordEncoder.matches(password,user.getPassword());
    }
}
