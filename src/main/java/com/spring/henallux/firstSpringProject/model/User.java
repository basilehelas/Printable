package com.spring.henallux.firstSpringProject.model;

public class User {
    private Integer id;
    private String username;
    private String password;   // mets déjà un hash si possible
    private String email;
    private String address;
    private String authorities;
    private boolean nonExpired = true;
    private boolean nonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

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
