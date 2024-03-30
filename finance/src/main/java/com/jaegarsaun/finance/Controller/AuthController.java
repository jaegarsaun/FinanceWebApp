package com.jaegarsaun.finance.Controller;

import com.jaegarsaun.finance.DTO.LoginResponse;
import com.jaegarsaun.finance.Service.UserService;
import com.jaegarsaun.finance.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User registeredUser = userService.register(user);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        boolean isAuthenticated = userService.authenticate(user.getUsername(), user.getPassword());
        if (isAuthenticated) {
            User loggedInUser = userService.findByUsername(user.getUsername());
            LoginResponse response = new LoginResponse("User authenticated successfully", loggedInUser.getUserId());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body("Invalid username or password");
    }
}

