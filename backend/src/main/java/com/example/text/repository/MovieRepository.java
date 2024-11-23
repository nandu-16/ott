package com.example.text.repository;


import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.example.text.model.ApplicationUser;
import com.example.text.model.Movies;



@Repository
public interface MovieRepository extends JpaRepository<Movies, Integer> {


    List<Movies> findByTitleContainingIgnoreCase(String title);
    
    @Query(value="SELECT * from movies where title like %:title%" , nativeQuery = true)
//    @Query(value="SELECT * from movies where title like %:title% limit 10 OFFSET :offset" , nativeQuery = true)


    List<Movies> findMovies(@Param("title") String title);
//    List<Movies> findMovies(@Param("title") String title, @Param("offset") Integer offset);
  
    
    @Query(value="SELECT * from movies" , nativeQuery = true)
    List<Movies> findAllMovies();
//    @Query(value="SELECT * from movies limit 10 OFFSET :offset" , nativeQuery = true)
//    List<Movies> findAllMovies(@Param("offset") Integer offset);
    
  @Query(value="SELECT sum(rating.rating)*5/(count(rating.rating_id)*5) as star_rating\r\n"
	+ "FROM `movies`  left join rating on rating.movie_id=movies.movie_id where movies.movie_id=:movie_id" , nativeQuery = true)
    String findRating(@Param("movie_id") Long movie_id);
  
  
  


  @Query(value="SELECT * from movies where 1 limit 10 OFFSET :offset" , nativeQuery = true)

List<Movies> findMoviesByPage(
//		@Param("title") String title,
		@Param("offset") Integer offset);
    
    
    
    long count();
    

    
}