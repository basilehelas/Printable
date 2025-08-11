package com.spring.henallux.firstSpringProject.dataAccess.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "User", uniqueConstraints = {
        @UniqueConstraint(name = "uk_user_email", columnNames = "email")
})
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 3) @NotBlank
    @Column(nullable = false, length = 100)
    private String username;

    @NotBlank
    @Column(nullable = false, length = 255)
    private String password; // ⚠️ stocke un HASH (BCrypt) en pratique

    @Email @NotBlank
    @Column(nullable = false, length = 255)
    private String email;

    @Lob
    private String address;

    @Column(length = 255)
    private String authorities; // ex: "ROLE_USER,ROLE_ADMIN"

    @Column(name = "non_expired", nullable = false)
    private boolean nonExpired = true;

    @Column(name = "non_locked", nullable = false)
    private boolean nonLocked = true;

    @Column(name = "credentials_non_expired", nullable = false)
    private boolean credentialsNonExpired = true;

    @Column(nullable = false)
    private boolean enabled = true;

    public UserEntity() {}

    // Getters/Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getAuthorities() { return authorities; }
    public void setAuthorities(String authorities) { this.authorities = authorities; }
    public boolean isNonExpired() { return nonExpired; }
    public void setNonExpired(boolean nonExpired) { this.nonExpired = nonExpired; }
    public boolean isNonLocked() { return nonLocked; }
    public void setNonLocked(boolean nonLocked) { this.nonLocked = nonLocked; }
    public boolean isCredentialsNonExpired() { return credentialsNonExpired; }
    public void setCredentialsNonExpired(boolean credentialsNonExpired) { this.credentialsNonExpired = credentialsNonExpired; }
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
}
