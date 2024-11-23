package com.example.text.controller;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.example.text.model.ApplicationUser;
import com.example.text.model.logout;
import com.example.text.repository.UserRepository;
import com.example.text.repository.logoutRepository;
import com.example.text.dto.LoginResponseDTO;
import com.example.text.dto.RegistrationDTO;
//import com.example.text.dto.otpDTO;
//import com.example.text.exception.ProductNotFoundException;
import com.example.text.exception.UsernameException;
import com.example.text.Services.AuthenticationService;
import com.example.text.Services.EmailService;
import com.example.text.Services.otpService;
//import com.example.text.repository.OtpRepository;

@RestController //rest api controllor
@CrossOrigin("*")
@RequestMapping("/auth")




public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    
    @Autowired
    private UserRepository userRepository;  //privte varible
    
    @Autowired
    private logoutRepository logoutrepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private EmailService emailService;

//    @PostMapping("/register")
//    public ApplicationUser registerUser(@RequestBody RegistrationDTO body){
//      
//        return authenticationService.registerUser(body.getUsername(), body.getPassword(),body.getEmail());
//    }
    
    
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody RegistrationDTO body) {
        Map<String, Object> response = new HashMap<>();
        
        // Check if the user already exists
        Optional<ApplicationUser> userOptional = userRepository.findByEmail(body.getEmail());
        if (!userOptional.isEmpty()) {
          
            response.put("message", "User already exists.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        
        // Proceed with user registration
        ApplicationUser newUser = authenticationService.registerUser(body.getUsername(), body.getPassword(), body.getEmail());
        
        response.put("message", "success");
        
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

   
    @PostMapping("/login")

    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
    	
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }
    @PostMapping("/otp-generation")
    public ResponseEntity<String> otpGeneration(@RequestBody  Map<String, String> body )
    {
    	Long userId;

    	try {
            // Extract phone number from the request body
            String email = body.get("email");

            if (email == null || email.isEmpty()) {
//            	return "Please provide a username";
            	return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Please provide a username");
            }
            

            Optional<ApplicationUser> userOptional = userRepository.findByEmail(email);
            
            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("User not found");
            }
            else
            {
            	 ApplicationUser user = userOptional.get(); // Get the user object
            	 userId=(user.getUserId().longValue());
            	
            }
            
            String otp = otpService.generateOtp();
            
            
            
//            otpRepository.save(newProduct);
        
//                // Create a new Otp instance
////                Otp otp1 = new Otp();
//                otp1.setOtp(otp);
//                otp1.setUser_id(userId);
//                otp1.setExpiry(LocalDateTime.now().plusMinutes(5)); // Set expiry to 5 minutes from now

                // Save the OTP to the repository
//                otpRepository.save(otp1);
                this.updateOtpByEmail(otp, email);
            
                
            String s=emailService.sendMail(otp,email);
            System.out.println(s);
            return ResponseEntity.status(200)
                    .body("Otp successfully generated");

             
//            otpService.sendOtp(phoneNumber, otp);

//            return ResponseEntity.ok("OTP sent successfully.");
        } catch (Exception e) {
//            // Handle exceptions (e.g., log the error)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to generate OTP.");
        }
	
    	
    }
    
    public void updateOtpByEmail(String otp, String email) {
        ApplicationUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setToken(otp);  // Assuming you have a setter for the token
        userRepository.save(user);
    }
    
    
    @PostMapping("/otp-verification")
    public ResponseEntity<String> otpVerification(@RequestBody  Map<String, String> body )
    {
    	
    	String otp = body.get("otp");
    	Long userId;
    	

    	try {
            // Extract phone number from the request body
            String email = body.get("email");

            if (email == null || email.isEmpty()) {
            	return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Please provide a email");
            }
            

            Optional<ApplicationUser> userOptional = userRepository.findByEmail(email);
            
            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("User not found");
            }
            else
            {
            	 ApplicationUser user = userOptional.get(); // Get the user object
            	 userId=(user.getUserId().longValue());
            	 String otp2=user.getToken();
            	 
            	 if(otp.equals(otp2))
            	 {
            		 return ResponseEntity.status(200)
                             .body("success");
            		 
            	 }
            	 else
            	 {
            		 return ResponseEntity.status(200)
                             .body("otp_miss_match");
            		 
            	 }
            	
            }
            
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to generate OTP.");
        }
	
    }
    
    @PostMapping("/upadtePassword")
    public ResponseEntity<String> upadtePassword(@RequestBody  Map<String, String> body )
    {
    	
    	String password = body.get("password");
    	Long userId;
    	

    	try {
            // Extract phone number from the request body
            String email = body.get("email");

            if (email == null || email.isEmpty()) {
            	return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Please provide a email");
            }
            

            Optional<ApplicationUser> userOptional = userRepository.findByEmail(email);
            
            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("User not found");
            }
            else
            {
            	 ApplicationUser user = userOptional.get(); // Get the user object
            	 String encodedPassword = passwordEncoder.encode(password);
            	 this.updatePasswordByEmail(encodedPassword, email);
            	 return ResponseEntity.status(200)
                         .body("success");
            	 
            	 
            	
            }
            
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to generate OTP.");
        }
	
    }
    
    public void updatePasswordByEmail(String password, String email) {
        ApplicationUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(password);  // Assuming you have a setter for the token
        userRepository.save(user);
    }
    
    @PostMapping("/changePasword")
    public ResponseEntity<String> changePassword(@RequestBody  Map<String, String> body )
    {
    	String user_id=body.get("user_id");
    	String old_password=body.get("old_password"); 
    	String new_password=body.get("new_password");
    	
        Optional<ApplicationUser> userOptional = userRepository.findById(Integer.parseInt(user_id));
        
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }
        else
        {
//        	$2a$10$ZS02gKYiFyCid8QyuoodxOoa3QJhDIKOUdViYagFah9ILidDFNr6m
        	 ApplicationUser user = userOptional.get(); // Get the user object
//        	 String encoded_old_Password = passwordEncoder.encode(old_password);
        	 String old_password_from_db=user.getPassword();
        	 System.out.println(old_password_from_db);
//        	 System.out.println(encoded_old_Password);
        	 if (passwordEncoder.matches(old_password,old_password_from_db)) {
        		 String enc_pass=passwordEncoder.encode(new_password);
        		 String email=user.getEmail();
        		 this.updatePasswordByEmail(enc_pass, email);
            	 return ResponseEntity.status(200)
                         .body("success");
        		 
        	 }
        	 else {
        		 return ResponseEntity.status(200)
                         .body("password missmatch");
        		 
        	 }
        }
        
    
        	
       
   }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = true) String authorizationHeader,
            @RequestBody Map<String, String> body) {
    	System.out.println("hi");   
        // Extract token by removing "Bearer " prefix
//        String token = authorizationHeader.replace("Bearer ", "");
//
//        // Extract userId from the request body
//        String user_id = body.get("user_id");
//        
//        logout obj=new logout(); 
    	
//        obj.setToken(token);
//        obj.setUserId(Integer.parseInt(user_id));    
//          
//        logoutrepository.save(obj);
//        // Example logic: add token to blocklist, invalidate session, or modify user credentials
// 
//        // Respond with success or any status relevant to your application
        return new ResponseEntity<>("User logged out successfully.", HttpStatus.OK);
    }
    
    

    	
    

}
