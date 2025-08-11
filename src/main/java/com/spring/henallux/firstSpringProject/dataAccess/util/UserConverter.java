package com.spring.henallux.firstSpringProject.dataAccess.util;

import com.spring.henallux.firstSpringProject.dataAccess.entity.UserEntity;
import com.spring.henallux.firstSpringProject.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User toModel(UserEntity e) {
        if (e == null) return null;
        User m = new User();
        m.setId(e.getId());
        m.setUsername(e.getUsername());
        m.setPassword(e.getPassword());
        m.setEmail(e.getEmail());
        m.setAddress(e.getAddress());
        m.setAuthorities(e.getAuthorities());
        m.setNonExpired(e.isNonExpired());
        m.setNonLocked(e.isNonLocked());
        m.setCredentialsNonExpired(e.isCredentialsNonExpired());
        m.setEnabled(e.isEnabled());
        return m;
    }

    public UserEntity toEntity(User m) {
        if (m == null) return null;
        UserEntity e = new UserEntity();
        e.setId(m.getId());
        e.setUsername(m.getUsername());
        e.setPassword(m.getPassword());
        e.setEmail(m.getEmail());
        e.setAddress(m.getAddress());
        e.setAuthorities(m.getAuthorities());
        e.setNonExpired(m.isNonExpired());
        e.setNonLocked(m.isNonLocked());
        e.setCredentialsNonExpired(m.isCredentialsNonExpired());
        e.setEnabled(m.isEnabled());
        return e;
    }
}
