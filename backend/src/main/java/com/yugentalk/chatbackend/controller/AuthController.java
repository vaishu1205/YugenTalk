package com.yugentalk.chatbackend.controller;

import com.yugentalk.chatbackend.model.User;
import com.yugentalk.chatbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserRepository userRepo;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // ✅ Signup API
    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            return "❌ Username already exists!";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return "✅ User registered!";
    }

    // ✅ Login API
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User found = userRepo.findByUsername(user.getUsername());
        if (found != null && passwordEncoder.matches(user.getPassword(), found.getPassword())) {
            return "✅ Login successful!";
        }
        return "❌ Invalid username or password!";
    }
}
