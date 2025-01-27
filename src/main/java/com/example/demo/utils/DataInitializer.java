package com.example.demo.utils;

import com.example.demo.User.model.User;
import com.example.demo.User.model.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!userRepository.findByUsername("testUser").isPresent()) {
                User user = new User(
                        "testUser",
                        passwordEncoder.encode("12345"),
                        "ROLE_USER"
                );
                userRepository.saveAndFlush(user);
            }
            if (!userRepository.findByUsername("testUserAdmin").isPresent()) {
                User user = new User(
                        "testUserAdmin",
                        passwordEncoder.encode("12345"),
                        "ROLE_ADMIN"
                );
                userRepository.saveAndFlush(user);
            }
        };
    }
}