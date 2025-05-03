package com.bookstore.service;

import com.bookstore.dto.LoginRequestDto;
import com.bookstore.dto.LoginResponseDto;
import com.bookstore.dto.RegisterRequestDto;
import com.bookstore.dto.UserResponseDto;
import com.bookstore.entity.User;
import com.bookstore.repository.UserRepository;
import jakarta.validation.Valid;
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
                throw new RuntimeException("User Already Exist!!");
            }
            //create user
            User user =new User();
            user.setFullName(registerRequestDto.fullName());
            user.setEmail(registerRequestDto.email());
            user.setAddress(registerRequestDto.address());
            user.setPhoneNumber(registerRequestDto.phoneNumber());
            //hash the password
            user.setPassword(passwordEncoder.encode(registerRequestDto.password()));
            //save user in db
            userRepository.save(user);
            //return User Response Dto
            return new UserResponseDto(
                    user.getId(),
                    user.getFullName(),
                    user.getEmail(),
                    user.getPhoneNumber(),
                    user.getAddress()
            );

    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByEmail(loginRequestDto.email()).orElse(null);

        if(user != null && passwordEncoder
                .matches(loginRequestDto.password(), user.getPassword())) {
            return new LoginResponseDto(
                    user.getId(),
                    user.getFullName(),
                    user.getEmail(),
                    user.getPhoneNumber(),
                    user.getAddress(),
                    user.getRole()
            );
         }
        else return null;
    }
}
