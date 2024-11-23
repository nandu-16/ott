package com.example.text.dto;

public class MovieDTO {
	  private Long id;
	    private String title;
	    private String description;
	    private String thumbnail;
	    private String video;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
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
}
