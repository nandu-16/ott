package com.example.text.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.text.model.Movies;
import com.example.text.model.PlanUser;

public interface PlanUserRepository extends JpaRepository<PlanUser, Integer> {
	
	@Query(value="SELECT * from plans limit 2 OFFSET :offset" , nativeQuery = true)
    List<PlanUser> findAllPlans(@Param("offset") Integer offset);
	
	 long count();
	 
	 @Query(value="SELECT \r\n"
	 		
	 		+ "    DATEDIFF(DATE_ADD(subscription.date, INTERVAL plans.duration DAY), CURDATE()) AS remaining_days\r\n"
	 		+ "FROM \r\n"
	 		+ "    plans\r\n"
	 		+ "INNER JOIN \r\n"
	 		+ "    subscription ON subscription.plan_id = plans.plan_id\r\n"
	 		+ "WHERE \r\n"
	 		+ "    subscription.user_id  = :user_id\r\n"
	 		+ "    AND DATEDIFF(DATE_ADD(subscription.date, INTERVAL plans.duration DAY), CURDATE()) >= 0;\r\n"
	 		+ "" , nativeQuery = true) 
	 String findRemainingDay(@Param("user_id") Integer user_id);
	 
	 @Query(value="SELECT \r\n"
		 		+ "    plans.plan_id,plans.description,plans.name,plans.duration,plans.isenabled,plans.price\r\n"
		
		 		+ "FROM \r\n"
		 		+ "    plans\r\n"
		 		+ "INNER JOIN \r\n"
		 		+ "    subscription ON subscription.plan_id = plans.plan_id\r\n"
		 		+ "WHERE \r\n"
		 		+ "    subscription.user_id  = :user_id\r\n"
		 		+ "    AND DATEDIFF(DATE_ADD(subscription.date, INTERVAL plans.duration DAY), CURDATE()) >= 0;\r\n"
		 		+ "" , nativeQuery = true) 
	 Optional<PlanUser> findPlanByUser(@Param("user_id") Integer user_id);

}
