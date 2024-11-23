package com.example.text.model;

import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="watchHistory")
public class WatchHistory {
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long wl_id;
	    private LocalDateTime date;
	    
	    @ManyToOne
	    @JoinColumn(name = "user_id", nullable = true) // Foreign key to the User entity
	    private ApplicationUser user;

	    @ManyToOne
	    @JoinColumn(name = "movie_id", nullable = true) // Foreign key to the Movies entity
	    private Movies movies;

		public Long getWl_id() {
			return wl_id;
		}

		public void setWl_id(Long wl_id) {
			this.wl_id = wl_id;
		}

		public LocalDateTime getDate() {
			return date;
		}

		public void setDate(LocalDateTime localDateTime) {
			this.date = localDateTime;
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
		
		 public void setUserId(Integer userId) {
		        this.user = new ApplicationUser();
		        this.user.setUserId(userId); // Assuming ApplicationUser has a method setId()
		    }

		    public void setMovieId(Long movieId) {
		        this.movies = new Movies();
		        this.movies.setId(movieId); // Assuming Movies has a method setId()
		    }
}
