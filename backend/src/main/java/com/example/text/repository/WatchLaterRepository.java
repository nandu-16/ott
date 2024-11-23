package com.example.text.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.text.model.Watchlater;

import jakarta.transaction.Transactional;
@Transactional
public interface WatchLaterRepository extends JpaRepository<Watchlater, Integer> {
	
	 @Modifying
	 @Query(value="DELETE  from watchlater where user_id=:userid and movie_id=:movieid" , nativeQuery = true)
	 void removeWatchLater(@Param("userid") Integer userid,@Param("movieid") Integer movieid);
	 
	 @Query(value="SELECT * from watchlater where user_id=:userid" , nativeQuery = true)
	 List<Watchlater>findWatchListByUserId(@Param("userid") Integer userid);
	 
	
	

   
}