package com.example.text.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.text.model.ApplicationUser;
import com.example.text.model.Movies;
import com.example.text.model.logout;

public interface logoutRepository extends JpaRepository<logout, Integer> {
	
	 @Query(value="select *  from logout where token=%:token% limit 1" , nativeQuery = true)

	 Optional<logout> getToken(@Param("token") String token);

}
   //table functions 