package com.example.text.model.AdminModel;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table(name = "admin", uniqueConstraints = @UniqueConstraint(columnNames = "email"))

public class Admin {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Admin_id")
    private Long id;
   
    private String email;
    private String password;
    private String otp;
   
    public Admin() {
        super();
    }


    public Admin(String email, String password) {
       
        this.email = email;
        this.password = password;
//        this.otp = ;
      
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

}