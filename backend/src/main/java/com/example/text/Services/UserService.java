package com.example.text.Services;


import java.util.HashSet;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.example.text.model.ApplicationUser;    
//import com.example.text.model.Role;
import com.example.text.repository.UserRepository;


@Service
public class UserService implements UserDetailsService {


    @Autowired
    private PasswordEncoder encoder;

  
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        System.out.println("In the user details service");


        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("user is not valid"));
    }


}