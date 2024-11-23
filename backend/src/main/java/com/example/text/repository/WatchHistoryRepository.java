package com.example.text.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.text.model.WatchHistory;

import jakarta.transaction.Transactional;
@Transactional
public interface WatchHistoryRepository extends JpaRepository<WatchHistory, Integer> {
	
	 @Modifying
	 @Query(value="DELETE  from watch_history where user_id=:userid and movie_id=:movieid" , nativeQuery = true)
	 void removeWatchHistory(@Param("userid") Integer userid,@Param("movieid") Integer movieid);
	 
	 @Query(value="SELECT * from watch_history where user_id=:userid" , nativeQuery = true)
	 List<WatchHistory>findWatchListByUserId(@Param("userid") Integer userid);
	 
	
	

   
}