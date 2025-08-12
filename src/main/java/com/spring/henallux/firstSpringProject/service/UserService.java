package com.spring.henallux.firstSpringProject.service;

import com.spring.henallux.firstSpringProject.dataAccess.dao.UserDataAccess;
import com.spring.henallux.firstSpringProject.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    private final UserDataAccess userDataAccess;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserDataAccess userDataAccess) {
        this.userDataAccess = userDataAccess;
    }

    @Transactional
    public void registerNewUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthorities("ROLE_USER");
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        userDataAccess.save(user);
    }

    public boolean emailExists(String email) {
        return userDataAccess.existsByEmail(email.trim().toLowerCase());
    }

}
