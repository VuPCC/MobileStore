package com.mobilestore.service;

import com.mobilestore.entity.User;
import com.mobilestore.exception.ResourceNotFoundException;
import com.mobilestore.exception.DuplicateResourceException;
import com.mobilestore.exception.InvalidPasswordException;
import com.mobilestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with: " + username));
    }

    public User registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new DuplicateResourceException("Username '" + user.getUsername() + "' already exists.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean authenticateUser(String username, String rawPassword) {
        User user = getUserByUsername(username);
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }
        return true;
    }
}
