package com.example.octobusbackend.controller;

import com.example.octobusbackend.dto.addUser.UserResponseDTO;
import com.example.octobusbackend.entity.User;
import com.example.octobusbackend.services.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    //Add User
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addUser(@RequestBody UserResponseDTO userResponseDto) {
        User user = userService.addUser(userResponseDto);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "User added successfully.");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //Edit User Details
    @PutMapping("/edit/{userId}")
    public ResponseEntity<Map<String, Object>> editUser(@PathVariable Long userId, @RequestBody UserResponseDTO userResponseDto) {
        User user = userService.editUser(userId, userResponseDto);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "User edited successfully.");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //delete by id
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "User deleted successfully.");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //perform login
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody UserResponseDTO userResponseDto) {
        User user = userService.loginUser(userResponseDto);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "User logged in successfully.");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
