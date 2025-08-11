package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.dataAccess.entity.UserEntity;
import com.spring.henallux.firstSpringProject.dataAccess.repository.UserRepository;
import com.spring.henallux.firstSpringProject.dataAccess.util.UserConverter;
import com.spring.henallux.firstSpringProject.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserDAO implements UserDataAccess {

    private final UserRepository repo;
    private final UserConverter converter;

    public UserDAO(UserRepository repo, UserConverter converter) {
        this.repo = repo;
        this.converter = converter;
    }

    @Override
    public User save(User user) {
        UserEntity entity;
        if (user.getId() != null) {
            entity = repo.findById(user.getId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found: " + user.getId()));
            entity.setUsername(user.getUsername());
            entity.setPassword(user.getPassword());
            entity.setEmail(user.getEmail());
            entity.setAddress(user.getAddress());
            entity.setAuthorities(user.getAuthorities());
            entity.setNonExpired(user.isNonExpired());
            entity.setNonLocked(user.isNonLocked());
            entity.setCredentialsNonExpired(user.isCredentialsNonExpired());
            entity.setEnabled(user.isEnabled());
        } else {
            entity = converter.toEntity(user);
        }
        return converter.toModel(repo.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getById(Integer id) {
        return repo.findById(id).map(converter::toModel);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return repo.findAll().stream().map(converter::toModel).toList();
    }
}
