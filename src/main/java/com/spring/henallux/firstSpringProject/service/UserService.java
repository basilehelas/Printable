package com.spring.henallux.firstSpringProject.service;

import com.spring.henallux.firstSpringProject.dataAccess.dao.UserDataAccess;
import com.spring.henallux.firstSpringProject.dataAccess.entity.UserEntity;
import com.spring.henallux.firstSpringProject.dataAccess.repository.UserRepository;
import com.spring.henallux.firstSpringProject.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class  UserService {

    private final UserDataAccess userDataAccess;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserDataAccess userDataAccess) {
        this.userDataAccess = userDataAccess;
    }

    @Transactional
    public void registerNewUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthorities("ROLE_USER");
        userDataAccess.save(user);
    }

    public boolean emailExists(String email) {
        return userDataAccess.existsByEmail(email.trim().toLowerCase());
    }


    @Transactional
    public void updateUser(String email, String username, String address, String phoneNumber, String rawPassword) {
        User user = userDataAccess.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User not found with email: " + email);
        }
        user.setUsername(username);
        user.setAddress(address);
        user.setPhoneNumber(phoneNumber);
        if (rawPassword != null && !rawPassword.isBlank()) {
            user.setPassword(encoder.encode(rawPassword));
        }
        userDataAccess.save(user);
    }
}
