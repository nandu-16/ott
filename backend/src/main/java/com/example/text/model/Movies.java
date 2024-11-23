package com.example.text.model;


import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="movies")
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long movie_id;
    private String title;
    private String description;
    private String thumbnail;
    private String video;
    
    @Transient
    private String rating;
    
	public Long getId() {
		return movie_id;
	}
	public void setId(Long movie_id) {
		this.movie_id = movie_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public String getTemporaryField() {
        return rating;
    }

    public void setTemporaryField(String temporaryField) {
        this.rating = temporaryField;
    }
  }