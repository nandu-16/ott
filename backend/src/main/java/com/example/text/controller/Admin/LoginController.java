package com.example.text.controller.Admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.text.Services.AuthenticationService;
import com.example.text.dto.LoginResponseDTO;
import com.example.text.dto.RegistrationDTO;
import com.example.text.dto.AdminDto.AdminDto;
import com.example.text.dto.AdminDto.AdminLoginResponseDto;
import com.example.text.model.Movies;
import com.example.text.repository.MovieRepository;
@Controller
@RequestMapping("/admin")
public class LoginController
{
	
	  @Autowired
	    private AuthenticationService authenticationService;
	  
	  @Autowired
	   private MovieRepository movieRepository;
  @GetMapping("/")
  public String helloAdmineController(){
  	
      return "Admin level access";
  }
	@GetMapping("/login")
    public String loginView() {
		
//		System.out.println("hi");
		return "login";
    }

	@PostMapping("/login-action")
    public String login (Model model,@ModelAttribute AdminDto body ) {
//	     System.out.println("jk");
		AdminLoginResponseDto status= authenticationService.loginAdmin(body.getEmail(), body.getPassword());
		
		 if ( status.getAdmin() != null) {
		        // Successful login, redirect to the admin dashboard
			 System.out.println("true");
			 
		
			    // Redirect to the "adminDashboard" method in the same controller or another controller
			    return "redirect:/adminmovies/getAllMovies?pagenumber=1"; 
			 
//				Long total=movieRepository.count(); 
//			    
//			    List<Movies> movies;
//			    
//			    Integer offset= (pagenumber-1)* total.intValue();
//
//			     movies = movieRepository.findMoviesByPage(offset); 


			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			
			 
			 
//			 model.addAttribute("movie", movieRepository);
//		        return "AdminDashbord";
		    } else {
		        // Handle failed login
		    	System.out.println("false");
		        return "login";  // Return to the login page
		    }
	     

		 
    }

}

   