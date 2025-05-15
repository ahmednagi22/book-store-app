package com.bookstore.service;

import com.bookstore.dto.LoginRequestDto;
import com.bookstore.dto.RegisterRequestDto;
import com.bookstore.dto.UserResponseDto;
import com.bookstore.entity.User;
import com.bookstore.exception.AuthenticationFailedException;
import com.bookstore.exception.UserAlreadyExistsException;
import com.bookstore.exception.UserNotFoundException;
import com.bookstore.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto register(RegisterRequestDto registerRequestDto) {
            // check if email exist in db
            if(userRepository.findByEmail(registerRequestDto.email()).isPresent()){
                throw new UserAlreadyExistsException("Email already registered");
            }

            //create user
            User user =new User();
            user.setFullName(registerRequestDto.fullName());
            user.setEmail(registerRequestDto.email());
            user.setAddress(registerRequestDto.address());
            user.setPhoneNumber(registerRequestDto.phoneNumber());
            user.setPassword(passwordEncoder.encode(registerRequestDto.password()));
            //save user in db
            User savedUser = userRepository.save(user);

            //return User Response Dto

            return mapToDto(savedUser);
    }

    public UserResponseDto login(LoginRequestDto loginRequestDto) {
        // check if user exist in db
        User user = userRepository.findByEmail(loginRequestDto.email())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if(!passwordEncoder.matches(loginRequestDto.password(), user.getPassword())) {
            throw new AuthenticationFailedException("Password is incorrect");
         }

        return mapToDto(user);
    }

    // to not expose password
    public UserResponseDto mapToDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.getRole()
        );
    }

}
