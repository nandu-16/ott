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
@Table(name="logout")
public class logout  {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "token_id")
    private Integer token_id; 
    private Integer userId;
    @Column(name = "token", length = 500)
    private String token;
    private Integer isblock=1;

    // Default constructor
    public logout() {
    }

    // Parameterized constructor
    public logout(Integer userId) {
        this.userId = userId;
       
    }

    // Getter and Setter methods
    public Integer getUserId() {
        return this.userId;
    }

    public String getToken() {
		return token;
	}

	public Integer getIsblock() {
		return isblock;
	}

	public void setIsblock(Integer isblock) {
		this.isblock = isblock;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setUserId(Integer userId) {
        this.userId = userId;
    }}

   