package com.spring.henallux.firstSpringProject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class a {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = "password";
        String encodedPassword = passwordEncoder.encode(password);
        System.out.println("Encoded password: " + encodedPassword);
    }
}
