package com.klef.medical_system.service;

import com.klef.medical_system.model.User;
import com.klef.medical_system.repository.UserRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    @Autowired
    private UserRepository userRepository;

    public String registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return "Email already registered.";
        }
        userRepository.save(user);
        return "Registration successful!";
    }
    
    @Transactional
    public String changePassword(Long userId, String oldPassword, String newPassword) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (user.getPassword().equals(oldPassword)) {
                user.setPassword(newPassword);
                userRepository.save(user);
                return "Password updated successfully";
            } else {
                return "Old password is incorrect";
            }
        } else {
            return "User not found";
        }
    }

    public Optional<User> loginUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.filter(user -> user.getPassword().equals(password));
    }

    public Optional<String> getUserNameByEmail(String email) {
        logger.info("Searching for user by email: " + email);
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.map(User::getName);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public int getUserCount() {
        return (int) userRepository.count();
    }
}
