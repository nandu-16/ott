package com.example.text.dto;

import com.example.text.model.ApplicationUser;

public class LoginResponseDTO {
    private ApplicationUser user; // Renamed from username to user
    private String jwt;

    // Default constructor
    public LoginResponseDTO() {  
        super();  //parent class axiss super
    }

    // Parameterized constructor  //class name is constructor
    public LoginResponseDTO(ApplicationUser user, String jwt) {
        this.user = user; // Assigning the parameter to the field correctly
        this.jwt = jwt;
    }

    // Getter for user
    public ApplicationUser getUser() {
        return this.user; // Returns the user object
    }

    // Setter for user
    public void setUser(ApplicationUser user) {
        this.user = user; // Sets the user object
    }

    // Getter for jwt
    public String getJwt() {
        return this.jwt; // Returns the JWT string
    }

    // Setter for jwt
    public void setJwt(String jwt) {
        this.jwt = jwt; // Sets the JWT string
    }
}
