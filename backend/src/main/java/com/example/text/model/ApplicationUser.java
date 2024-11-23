package com.example.text.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name="users")
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;

    private String password;
    
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    
    private String token;
    private Integer isblock=0;

    // Default constructor
    public ApplicationUser() {
    }

    // Parameterized constructor
    public ApplicationUser(Integer userId, String password, String email) {
        this.userId = userId;
        this.password = password;
        this.email = email;
    }

    // Getter and Setter methods
    public Integer getUserId() {
        return this.userId;
    }

    public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Use email as username in this case
    @Override
    public String getUsername() {
        return this.email; // Use email as the username
    }

    // Implement the required method from UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Since you don't need roles/authorities, return an empty list
        return Collections.emptyList();
    }

    // UserDetails methods (these are mandatory for Spring Security)
    @Override
    public boolean isAccountNonExpired() {
        return true; // Account is never expired
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Account is never locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Credentials are never expired
    }

    @Override
    public boolean isEnabled() {
        return true; // Account is enabled
    }
}
