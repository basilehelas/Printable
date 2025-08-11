package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDataAccess {
    User save(User user);
    Optional<User> getById(Integer id);
    List<User> getAll();
}
