package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.dataAccess.entity.UserEntity;
import com.spring.henallux.firstSpringProject.dataAccess.repository.UserRepository;
import com.spring.henallux.firstSpringProject.dataAccess.util.ProviderConverter;
import com.spring.henallux.firstSpringProject.model.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDAO implements UserDataAccess {

    private final UserRepository userRepository;
    private final ProviderConverter providerConverter;

    public UserDAO(UserRepository userRepository, ProviderConverter providerConverter) {
        this.userRepository = userRepository;
        this.providerConverter = providerConverter;
    }

    public User findByUsername(String username) {
        UserEntity entity = userRepository.findByUsername(username);
        return providerConverter.userEntityToUserModel(entity);
    }

    public User findByEmail(String email) {
        UserEntity entity = userRepository.findByEmail(email);
        return providerConverter.userEntityToUserModel(entity);
    }

    public User save(User user) {
        UserEntity userEntity = providerConverter.userModelToUserEntity(user);
        userRepository.save(userEntity);
        return user;
    }
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}
