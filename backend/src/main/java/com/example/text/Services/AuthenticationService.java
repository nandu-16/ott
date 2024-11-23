package com.example.text.Services;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.text.model.ApplicationUser;
import com.example.text.dto.LoginResponseDTO;
import com.example.text.dto.AdminDto.AdminLoginResponseDto;
//import com.example.text.model.Role;
import com.example.text.model.AdminModel.Admin;
//import com.example.text.repository.RoleRepository;
import com.example.text.repository.UserRepository;
import com.example.text.repository.AdminRepository.AdminRepository;


@Service
@Transactional    //all services
public class AuthenticationService {


    @Autowired   
    private UserRepository userRepository;
    
    @Autowired
    private AdminRepository adminRepository;

//    @Autowired
//    private RoleRepository roleRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private TokenService tokenService;


    public ApplicationUser registerUser(String username, String password,String email){


        String encodedPassword = passwordEncoder.encode(password);




        return userRepository.save(new ApplicationUser(0, encodedPassword,email));  //0 is id
    }


    public LoginResponseDTO loginUser(String username, String password){


        try{
//        	password=passwordEncoder.encode(password);
//        	 System.out.println(password);
        	
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );
            
            System.out.println(auth);


            String token = tokenService.generateJwt(auth);
            Optional<ApplicationUser> user = userRepository.findByEmail(username);
            if (user.isPresent()) {   
                System.out.println("User found: " + user.get());
            } else {
                System.out.println("No user found with email: " + username);
            }            

           
            return new LoginResponseDTO(user.get(), token);


        } catch(AuthenticationException e){
        	System.out.print(e);
            return new LoginResponseDTO(null, "");
        }
    }

    public AdminLoginResponseDto loginAdmin(String username, String password){
    	
//    	String test = passwordEncoder.encode(password);
//    	System.out.println(test);
    	
    	 try{
    		 Optional<Admin> adminOpt = adminRepository.findByEmail(username);
    
    	        if (adminOpt.isPresent()) {
    	            Admin admin = adminOpt.get();
    	            String storedPassword = admin.getPassword(); 
    	            String encodedPassword = passwordEncoder.encode(password);
//    	            System.out.println(encodedPassword);
    	            if (passwordEncoder.matches(password,storedPassword)) {
    	            	System.out.println("correct");
    	            	return new AdminLoginResponseDto(admin);
    	            }
   
    	          
    	        } else {
    	        	System.out.println("incorrect");
    	        	 return new AdminLoginResponseDto(null);
    	            
    	        }

         } catch(AuthenticationException e){
        	 System.out.println("incorrect");
        	  return new AdminLoginResponseDto(null);
         }
    	 System.out.println("incorrect"); 
       return new AdminLoginResponseDto(null);

    }



}
