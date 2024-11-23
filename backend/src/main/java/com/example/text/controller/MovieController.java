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
import com.example.text.model.Watchlater;
import com.example.text.model.WatchHistory;
import com.example.text.repository.MovieRepository;
import com.example.text.repository.RatingRepository;
import com.example.text.repository.WatchHistoryRepository;
import com.example.text.repository.WatchLaterRepository;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController 
@RequestMapping("/Movies")
@CrossOrigin("*") 
//@CrossOrigin(origins = "*")
public class MovieController {
	
	@Value("${base.url}")
	    private String baseUrl;
	
	@Autowired
	private  MovieRepository movieRepository;
	
	@Autowired
	private  RatingRepository ratingRepository;
	
	@Autowired
	private  WatchLaterRepository watchLaterRepository;
	
	@Autowired
	private  WatchHistoryRepository watchHistoryRepository;
	
	
//	 @GetMapping("/GetMovies")
//	
//			
//			public ResponseEntity<List<Movies>> GetMovies(){
//				
//				List<Movies> movies = movieRepository.findByTitleContainingIgnoreCase(null);
//				
//				return new ResponseEntity<List<Movies>>(movies,HttpStatus.OK);
//			}
//     return " access level";
	@GetMapping("/GetMovies")
	public ResponseEntity<List<Movies>> GetMovies(@RequestParam(required = false) String searchKey)
			
//		,@RequestParam(required = true) Integer pagenumber)
			{
		
		System.out.println("jkd");
		
		
		Long total=movieRepository.count(); 
	    
	    List<Movies> movies;
	    
	    
	    
	    
//	    Integer offset= (pagenumber-1)* total.intValue();
	    
	    // Check if searchKey is null or empty, and fetch all movies if so
	    System.out.println(searchKey);  
	    if (searchKey == null || searchKey.isEmpty()) {
	        movies = movieRepository.findAllMovies(); 
//	        movies = movieRepository.findAllMovies(offset);// fetch all movies
	    } else {
	        movies = movieRepository.findMovies(searchKey);  // search by title
//	        movies = movieRepository.findMovies(searchKey,offset);  // search by title
	    }
	    
	    
//	    System.out.println(rating);
	    for (Movies movie : movies) {
	    	Long movie_id=movie.getId();
	    	String rating=movieRepository.findRating(movie_id);
	    	movie.setTemporaryField(rating);
	    	
	        String imagePath = movie.getThumbnail(); // Assuming you have a method to get the image path
	        if (imagePath != null && !imagePath.isEmpty()) {
	            movie.setThumbnail(baseUrl + imagePath);// Set the full image path
	            
	        }
	        
	        
	    }
	    return new ResponseEntity<List<Movies>>(movies, HttpStatus.OK);
	}
	
	@GetMapping("/MovieMainPage")
	public Optional<Movies> MovieMainPage(@RequestParam(required = true) Integer id) {
	    
		 Optional<Movies> movieOptional= movieRepository.findById(id);
       
       if (movieOptional.isPresent()) {
           Movies movie = movieOptional.get();
           // Assuming there's a method in Movies class to get the movie path or similar
           String thumb = movie.getThumbnail(); // Modify according to your actual method
           String video = movie.getVideo();
           // Concatenate the base URL with the movie path
           String completethumb = baseUrl + thumb;
           String completevideo = baseUrl + thumb;
           movie.setThumbnail(completethumb); // Assuming you have a method to set the URL
           movie.setVideo(completevideo);  
           
           return Optional.of(movie);
       }
       return Optional.empty();
	    
}
	
	@PostMapping("/addRating")
	public ResponseEntity<String> addRating(@RequestBody Map<String, String> body) {
	    // Handle the rating value here
		String rating = body.get("rating");
		String user_id = body.get("user_id");
		String movie_id=body.get("movie_id");
		System.out.println(movie_id);
		

		MovieRating obj = new MovieRating(); 
		obj.setRating(Long.parseLong(rating));
		obj.setUserId(Integer.parseInt(user_id));
		obj.setMovieId(Long.parseLong(movie_id));

		ratingRepository.save(obj);
	    return ResponseEntity.ok("Rating added successfully");
	}
	
	@PostMapping("/WatchLater")
	public ResponseEntity<String> WatchLater(@RequestBody Map<String, String> body) {
	    // Handle the rating value here
		
		String user_id = body.get("user_id");
		String movie_id=body.get("movie_id");
		System.out.println(movie_id);
		
		Watchlater obj = new Watchlater(); 

		obj.setUserId(Integer.parseInt(user_id));
		obj.setMovieId(Long.parseLong(movie_id));
		obj.setDate(LocalDateTime.now());

		watchLaterRepository.save(obj);


	    return ResponseEntity.ok("Watch Later added successfully");
	}

	@PostMapping("/removeRating")
	public String removeRating(@RequestBody Map<String, String> body) {
		
	    // Handle the rating value here

		String user_id = body.get("user_id");
		System.out.println("iiiii"); 
		String movie_id=body.get("movie_id");
		System.out.println("vvvv");
		ratingRepository.deleteMovieRating(Integer.parseInt(user_id),Integer.parseInt(movie_id));
		return "remove successfully";
	}
	@PostMapping("/WatchList")
	public ResponseEntity<String> WatchList(@RequestBody Map<String, String> body) {
	    // Handle the rating value here
		
		String user_id = body.get("user_id");
		String movie_id=body.get("movie_id");
		System.out.println(movie_id);
		
		Watchlater obj = new Watchlater(); 

		obj.setUserId(Integer.parseInt(user_id));
		obj.setMovieId(Long.parseLong(movie_id));
		obj.setDate(LocalDateTime.now());

		watchLaterRepository.save(obj);


	    return ResponseEntity.ok("Watch List added successfully");
	}
	
	@PostMapping("/remove_watchLater")
	public String remove_watchLater(@RequestBody Map<String, String> body) {
		
	    // Handle the rating value here

		String user_id = body.get("user_id");
		System.out.println("iiiii"); 
		String movie_id=body.get("movie_id");
		System.out.println("vvvv");
//		watchLaterRepository.d(Integer.parseInt(user_id),Integer.parseInt(movie_id));
		
            Integer userid = Integer.parseInt(user_id);
            Integer movieid = Integer.parseInt(movie_id);
            watchLaterRepository.removeWatchLater(userid, movieid);
            return "success";
 
	}
	@PostMapping("/getWatchLater")
	public ResponseEntity<List<Watchlater>> getWatchList(@RequestBody Map<String, String> body)
	{
		String user_id = body.get("user_id");
	
		
		
	    
	    List<Watchlater> watchlater=watchLaterRepository.findWatchListByUserId(Integer.parseInt(user_id));
	    
	    for (Watchlater item : watchlater) {
	        if (item.getMovies() != null) {
	            Movies movie = item.getMovies();
	            
	            // Append base URL if the fields are not null
	            if (movie.getThumbnail() != null) {
	                movie.setThumbnail(baseUrl + movie.getThumbnail());
	            }
	            
	            if (movie.getVideo() != null) {
	                movie.setVideo(baseUrl + movie.getVideo());
	            }
	        }
	    }
	    

	    
	    
	    
	    

	    return new ResponseEntity<List<Watchlater>>(watchlater, HttpStatus.OK);
//		return null;
	}
	
	@PostMapping("/getWatchHistory")
	public ResponseEntity<List<WatchHistory>> getWatchHistory(@RequestBody Map<String, String> body)
	{
		String user_id = body.get("user_id");
	
		
		
	    
	    List<WatchHistory> watchhistory=watchHistoryRepository.findWatchListByUserId(Integer.parseInt(user_id));
	    
	    
	    
	    

	    return new ResponseEntity<List<WatchHistory>>(watchhistory, HttpStatus.OK);
//		return null;
	}
	@PostMapping("/WatchHistory")
	public ResponseEntity<String> WatchHistory(@RequestBody Map<String, String> body) {
	    // Handle the rating value here
		
		String user_id = body.get("user_id");
		String movie_id=body.get("movie_id");
		System.out.println(movie_id);
		
		WatchHistory obj = new WatchHistory(); 

		obj.setUserId(Integer.parseInt(user_id));
		obj.setMovieId(Long.parseLong(movie_id));
		obj.setDate(LocalDateTime.now());

		watchHistoryRepository.save(obj);


	    return ResponseEntity.ok("Watch History added successfully");
	}
	  @GetMapping("/print-token")
	    public String printToken(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {

	        // Extract and print the token
	        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	            String token = authorizationHeader.substring(7);
	            System.out.println("Bearer Token: " + token);
	            return "Bearer Token received and printed to console.";
	        }
	        return "No Bearer Token found!";
	    }
	
}




