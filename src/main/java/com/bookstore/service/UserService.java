package com.bookstore.service;

import com.bookstore.dto.UserDTO;
import com.bookstore.exception.UserNotFoundException;
import org.mindrot.jbcrypt.BCrypt;
import com.bookstore.entity.User;
import com.bookstore.enums.Role;
import com.bookstore.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

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
        String hashedPassword = BCrypt.hashpw(userDTO.getPassword(),BCrypt.gensalt());
        user.setPassword(hashedPassword);

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
        //user doesn't exist, return false
        if(user == null){
            return false;
        }
        // check user password with stored password
        return BCrypt.checkpw(password,user.getPassword());
    }
}
