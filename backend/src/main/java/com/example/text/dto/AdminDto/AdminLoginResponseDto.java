package com.example.text.dto.AdminDto;

import com.example.text.model.AdminModel.Admin;

public class AdminLoginResponseDto {
    private Admin admin;  // Change type to Admin

    // Default constructor
    public AdminLoginResponseDto() {
        super();
    }

    // Constructor accepting Admin object
    public AdminLoginResponseDto(Admin admin) {
        this.admin = admin;  // Correct assignment
    }

    // Getter for Admin object
    public Admin getAdmin() {
        return this.admin;  // Return the Admin object
    }

    // Setter for Admin object
    public void setAdmin(Admin admin) {
        this.admin = admin;  // Set the Admin object
    }
}
