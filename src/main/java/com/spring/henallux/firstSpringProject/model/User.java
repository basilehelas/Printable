package com.spring.henallux.firstSpringProject.model;

import javax.validation.constraints.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails {

    private Integer id;

    @NotBlank(message = "Le nom d'utilisateur est obligatoire.")
    @Size(min = 3, max = 100, message = "Le nom d'utilisateur doit faire entre 3 et 100 caractères.")
    @Pattern(regexp = "^[A-Za-z0-9._-]+$", message = "Seuls lettres, chiffres, '.', '_' et '-' sont autorisés.")
    private String username;

    @NotBlank(message = "Le mot de passe est obligatoire.")
    @Size(min = 8, max = 255, message = "Le mot de passe doit faire entre 8 et 255 caractères.")
    private String password;

    @NotBlank(message = "L'e-mail est obligatoire.")
    @Email(message = "Format d'e-mail invalide.")
    @Size(max = 255, message = "L'e-mail ne peut pas dépasser 255 caractères.")
    private String email;

    private String address;

    @Size(max = 255, message = "Les autorités ne peuvent pas dépasser 255 caractères.")
    private String authorities;

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    public User() {}

    public User(Integer id,
                String username,
                String password,
                String email,
                String address,
                String authorities,
                boolean accountNonExpired,
                boolean accountNonLocked,
                boolean credentialsNonExpired,
                boolean enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.authorities = authorities;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (authorities != null && !authorities.isBlank()) {
            String[] authoritiesAsArray = authorities.split(",");
            for (String authority : authoritiesAsArray) {
                String trimmed = authority.trim();
                if (!trimmed.isEmpty()) {
                    grantedAuthorities.add(new SimpleGrantedAuthority(trimmed));
                }
            }
        }
        return grantedAuthorities;
    }

    @Override
    public String getPassword() { return password; }

    @Override
    public String getUsername() { return username; }

    @Override
    public boolean isAccountNonExpired() { return accountNonExpired; }

    @Override
    public boolean isAccountNonLocked() { return accountNonLocked; }

    @Override
    public boolean isCredentialsNonExpired() { return credentialsNonExpired; }

    @Override
    public boolean isEnabled() { return enabled; }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getAuthoritiesAsString() { return authorities; }
    public void setAuthorities(String authorities) { this.authorities = authorities; }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
