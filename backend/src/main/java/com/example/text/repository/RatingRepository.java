package com.example.text.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.text.model.ApplicationUser;
import com.example.text.model.MovieRating;
import com.example.text.model.Movies;

public interface RatingRepository extends JpaRepository<MovieRating, Integer> {
	 @Query(value="DELETE  from rating where user_id=%:userid% and movie_id=%:movieid%" , nativeQuery = true)

	 void deleteMovieRating(@Param("userid") Integer userid,@Param("movieid") Integer movieid);
	 
}