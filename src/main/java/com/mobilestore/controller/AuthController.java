package com.mobilestore.controller;

import com.mobilestore.entity.User;
import com.mobilestore.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        userService.authenticateUser(username, password);
        return ResponseEntity.ok("Login success");
    }
}
