package com.bookstore.service;

import com.bookstore.entity.User;
import com.bookstore.exception.BookNotFoundException;
import com.bookstore.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(User user){
        if(!userRepository.existsById(user.getId())){
            throw new BookNotFoundException("Cannot update. User not found.");
        }
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        if(!userRepository.existsById(id)){
            throw new BookNotFoundException("cannot delete. User not found");
        }
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new BookNotFoundException("User not found"));
    }


}
