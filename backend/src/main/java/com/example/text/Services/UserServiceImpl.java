//package com.example.text.Services;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.example.text.Services.UserService;
//import com.example.text.dto.UserDto;
//import com.example.text.model.User;
//import com.example.text.repository.UserRepository;
//
//
//@Service
//public class UserServiceImpl implements UserService {
//   
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//   
//    @Autowired
//    private UserRepository userRepository;
//
//
//    @Override
//    public User save(UserDto userDto) {
//        User user = new User(userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()) , userDto.getRole(), userDto.getFullname());
//        return userRepository.save(user);
//    }
//
//
//}