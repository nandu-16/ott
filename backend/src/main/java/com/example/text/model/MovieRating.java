package com.example.text.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rating")
public class MovieRating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rating_id;
    
    private Long rating;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true) // Foreign key to the User entity
    private ApplicationUser user;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = true) // Foreign key to the Movies entity
    private Movies movies;

	public Long getRating_id() {
		return rating_id;
	}

	public void setRating_id(Long rating_id) {
		this.rating_id = rating_id;
	}

	public Long getRating() {
		return rating;
	}

	public void setRating(Long rating) {
		this.rating = rating;
	}

	public ApplicationUser getUser() {
		return user;
	}

	public void setUser(ApplicationUser user) {
		this.user = user;
	}

	public Movies getMovies() {
		return movies;
	}

	public void setMovies(Movies movies) {
		this.movies = movies;
	}

    // Getters and Setters for all fields (if needed)
	// Optionally, add helper methods to set using ID directly
    public void setUserId(Integer userId) {
        this.user = new ApplicationUser();
        this.user.setUserId(userId); // Assuming ApplicationUser has a method setId()
    }

    public void setMovieId(Long movieId) {
        this.movies = new Movies();
        this.movies.setId(movieId); // Assuming Movies has a method setId()
    }
    
    
}
 