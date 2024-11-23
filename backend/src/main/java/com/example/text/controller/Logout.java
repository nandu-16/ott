package com.example.text.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.text.dto.MovieDTO;
import com.example.text.model.MovieRating;
import com.example.text.model.logout;
import com.example.text.model.Watchlater;
import com.example.text.model.WatchHistory;
import com.example.text.repository.MovieRepository;
import com.example.text.repository.RatingRepository;
import com.example.text.repository.WatchHistoryRepository;
import com.example.text.repository.WatchLaterRepository;
import com.example.text.repository.logoutRepository;

import org.springframework.web.bind.annotation.RequestHeader;


@RestController 
@RequestMapping("/Logout")
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class Logout {
	
	@Value("${base.url}")
	    private String baseUrl;
	
	@Autowired
	private  logoutRepository logoutrepository; 
	
	@PostMapping("/logout")
	public ResponseEntity<Map<String, String>> logout(
	        @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
	        @RequestBody Map<String, String> body) {
		System.out.println("jwrt");

	    // Check for Authorization header and extract the token
	    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	        String token = authorizationHeader.substring(7);

	        // Extract userId from the request body
	        String userIdString = body.get("user_id");
	        if (userIdString == null) {
	            return ResponseEntity.badRequest().body(Map.of("message", "User ID is missing in the request body."));
	        }

	        try {
	            int userId = Integer.parseInt(userIdString);

	            // Create and save logout entity
	            logout logoutEntry = new logout();
	            logoutEntry.setToken(token);
	            logoutEntry.setUserId(userId);
	            logoutrepository.save(logoutEntry);  

	            System.out.println("Bearer Token: " + token);
	            return ResponseEntity.ok(Map.of("message", "User logged out successfully."));
	        } catch (NumberFormatException e) {
	            return ResponseEntity.badRequest().body(Map.of("message", "Invalid user ID format."));
	        }

	    } else {
	    	return ResponseEntity.ok(Map.of("message", "User logged out successfully."));
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "No Bearer Token found!"));
	    }
	}

	
}



  
