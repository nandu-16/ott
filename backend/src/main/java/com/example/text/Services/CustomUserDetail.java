package com.example.text.Services;


import java.util.Collection;
import java.util.List;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.example.text.model.User;


public class CustomUserDetail implements UserDetails {


    private User user;


    public CustomUserDetail(User user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       
//        return List.of(() -> user.getRole());
    	return List.of();  // Return an empty list if authorities are not needed
    }


//    public String getFullname() {
//        return user.getFullname();
//    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }


    @Override
    public String getUsername() {
       
        return user.getEmail();
    }


    @Override
    public boolean isAccountNonExpired() {
   
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
   
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
   
        return true;
    }


    @Override
    public boolean isEnabled() {
       
        return true;
    }


}