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
import com.example.text.model.Movies;
import com.example.text.model.PlanUser;
import com.example.text.model.UserSubscription;
import com.example.text.repository.MovieRepository;
import com.example.text.repository.PlanUserRepository;
import com.example.text.repository.RatingRepository;
import com.example.text.repository.SubscriptionRepository;
import com.example.text.repository.WatchLaterRepository;


@RestController 
@RequestMapping("/Plans")
@CrossOrigin("*")

public class PlansController {
	
	@Autowired
	PlanUserRepository planUserRepository;
	
	@Autowired
	SubscriptionRepository subscriptionRepository;
	
	@GetMapping("/getAllPlans")
	public ResponseEntity<List<PlanUser>> getAllPlans(@RequestParam(required = true) Integer pagenumber) {
		
       Long total=planUserRepository.count(); 
	    
	    List<PlanUser> plans;
	    
	    Integer offset= (pagenumber-1)* total.intValue();
	    plans=planUserRepository.findAllPlans(offset);
		 
	    return new ResponseEntity<List<PlanUser>>(plans, HttpStatus.OK);
		
		
	  }
	@GetMapping("/getPlanDetails")
	public ResponseEntity<PlanUser> getPlanDetails(@RequestParam(required = true) Integer plan_id) {
		
      
	    
	    Optional<PlanUser> plans = planUserRepository.findById(plan_id);
	    
	   
	    if (plans.isPresent()) {
	        return new ResponseEntity<>(plans.get(), HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
		
		
	  }
	@PostMapping("/subscribe")
	public String subscribe(@RequestBody Map<String, String> body) {
		
		String plan_id = body.get("plan_id");
		String user_id=body.get("user_id");
//		 UserSubscription plan= subscriptionRepository.getReferenceById(Integer.parseInt(user_id));
		Optional<PlanUser> plan = planUserRepository.findPlanByUser(Integer.parseInt(user_id));
		if (plan.isPresent()) {
			return "plan already subscribed";
		}
		else
		{
			UserSubscription obj= new  UserSubscription();
			obj.setPlanId(Long.parseLong(plan_id));
			obj.setUserId(Integer.parseInt(user_id));
			obj.setDate(LocalDateTime.now());
			subscriptionRepository.save(obj);		
			return "success";
			
		}
		 
		
		
		
		

		
	}
	
	@PostMapping("/current_plan")
	public Optional<PlanUser> current_plan(@RequestBody Map<String, String> body) {
		
      
		String user_id=body.get("user_id");
	    
//	    
		Optional<PlanUser> plan = planUserRepository.findPlanByUser(Integer.parseInt(user_id));
//		if (plan.isPresent()) {
//			   String day=planUserRepository.findRemainingDay(Integer.parseInt(user_id));
//			   plan.setRemaining_day(day);
//			   System.out.println(day);
//			   return plan;
//		}
//		else
//		{
//			return null;
//		}
		if (plan.isPresent()) {
		    PlanUser userPlan = plan.get();  // Get the actual PlanUser object
		    String remainingDay = planUserRepository.findRemainingDay(Integer.parseInt(user_id));  // Fetch remaining day
		    userPlan.setRemaining_day(remainingDay);  // Set remaining day on the PlanUser object
		    System.out.println(remainingDay);
		    return Optional.of(userPlan);  // Return the updated PlanUser wrapped in Optional
		} else {
		    return Optional.empty();  // Return Optional.empty() if no plan is found
		}
		
		
		

	    
	    
	   
//	    if (plans.isPresent()) {
//	        return new ResponseEntity<>(plans.get(), HttpStatus.OK);
//	    } else {
//	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	    }
//		
		
	  }
	
	
	
	
	

	
}
