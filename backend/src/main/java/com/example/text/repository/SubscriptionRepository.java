package com.example.text.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.text.model.UserSubscription;

public interface SubscriptionRepository extends JpaRepository<UserSubscription,Integer>{
	
	UserSubscription getReferenceById(Integer user_id);
	
	

	
}
