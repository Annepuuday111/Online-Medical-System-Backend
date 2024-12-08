package com.klef.medical_system.controller;

import com.klef.medical_system.model.User;
import com.klef.medical_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        String result = userService.registerUser(user);
        return "Registration successful!".equals(result) ? 
                new ResponseEntity<>(result, HttpStatus.CREATED) :
                new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Map<String, String> loginDetails) {
        String email = loginDetails.get("email");
        String password = loginDetails.get("password");
        Optional<User> userOptional = userService.loginUser(email, password);
        return userOptional.isPresent() ? 
                new ResponseEntity<>("Login successful!", HttpStatus.OK) : 
                new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
    }
    
    @PostMapping("/{userId}/change-password")
    public ResponseEntity<String> changePassword(
            @PathVariable Long userId,
            @RequestBody Map<String, String> passwordData) {

        String oldPassword = passwordData.get("oldPassword");
        String newPassword = passwordData.get("newPassword");

        String result = userService.changePassword(userId, oldPassword, newPassword);
        return "Password updated successfully".equals(result) ? 
                new ResponseEntity<>(result, HttpStatus.OK) : 
                new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<Map<String, String>> getUserName(@RequestParam String email) {
        logger.info("Received request to fetch user name for email: " + email);
        Optional<String> userNameOptional = userService.getUserNameByEmail(email);
        if (userNameOptional.isPresent()) {
            Map<String, String> response = new HashMap<>();
            response.put("name", userNameOptional.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Map.of("error", "User not found"), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUserById(id);
        return isDeleted ? 
                new ResponseEntity<>("User deleted successfully", HttpStatus.OK) : 
                new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/counts")
    public ResponseEntity<Map<String, Integer>> getUserCount() {
        Map<String, Integer> countMap = new HashMap<>();
        countMap.put("totalUsers", userService.getUserCount());
        return ResponseEntity.ok(countMap);
    }
}
