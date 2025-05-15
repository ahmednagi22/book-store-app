package com.bookstore.service;

import com.bookstore.dto.UserResponseDto;
import com.bookstore.entity.User;
import com.bookstore.enums.Role;
import com.bookstore.exception.UserNotFoundException;
import com.bookstore.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        User User = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        // mapped user to
        return User;

    }


//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;
//
//    public UserService(
//            UserRepository userRepository,
//            PasswordEncoder passwordEncoder,
//            AuthenticationManager authenticationManager,
//            JWTService jwtService) {
//
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.authenticationManager = authenticationManager;
//
//    }
//    public void registerUser(UserResponseDto userDTO) throws RuntimeException{
//        // Check if user already exists
//        if(userRepository.findByEmail(userDTO.getEmail()).isPresent()){
//            throw new RuntimeException("Email already in use!!!");
//        }
//
//        //create New User
//        User user = new User();
//        user.setFullName(userDTO.getFullName());
//        user.setEmail(userDTO.getEmail());
//        // encode password using BcCrypt
//        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
//        user.setAddress(userDTO.getAddress());
//        user.setRole(Role.CUSTOMER);// default user is customer
//
//        //save the user
//        userRepository.save(user);
//    }
//
//    public boolean authenticateUser(LoginDTO loginDTO){
//        Authentication authentication =authenticationManager.authenticate(
//          new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
//        );
//        return authentication.isAuthenticated();
//
//    }
//
//    public User findByEmail(String email) {
//        return userRepository.findByEmail(email)
//                .orElseThrow(() -> new UserNotFoundException("User not found"));
//
//    }
}
