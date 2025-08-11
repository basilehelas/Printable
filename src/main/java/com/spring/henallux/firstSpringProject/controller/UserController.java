package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.dataAccess.dao.UserDAO;
import com.spring.henallux.firstSpringProject.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/users")
    public class UserController {

        private final UserDAO userDAO;

        public UserController(UserDAO userDAO) {
            this.userDAO = userDAO;
        }

        @PostMapping
        public User createUser(@RequestBody User user) {
            return userDAO.save(user);
        }

        @GetMapping
        public List<User> getAllUsers() {
            return userDAO.getAll();
        }

        @GetMapping("/{id}")
        public User getUserById(@PathVariable Integer id) {
            return userDAO.getById(id).orElseThrow(() -> new RuntimeException("Not found"));
        }
    }
    
