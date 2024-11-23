package com.example.text.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.example.text.model.ApplicationUser;
import com.example.text.model.User;


@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Integer> {

	
    Optional<ApplicationUser> findByEmail(String username);
   
   
	
//    Optional<ApplicationUser> findb(String email);
    
}