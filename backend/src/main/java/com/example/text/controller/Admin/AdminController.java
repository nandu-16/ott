package com.example.text.controller.Admin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.text.model.Movies;
import com.example.text.repository.UserRepository;
import com.example.text.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/adminmovies")

public class AdminController {
	
	public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/uploads";
	public static String uploadDirectory1 = System.getProperty("user.dir")+"/src/main/resources/static/uploads/movieVideo";
	
	@Autowired
    private MovieRepository movieRepository;
   
    @GetMapping("/create")
    public String movies(){
    	
        return "create";
    }
    @PostMapping ("/createmovies")
    public String createmovies(
    		@RequestParam("thumbnail") MultipartFile[] files,
    		@RequestParam("movieTitle") String title,
            @RequestParam("movieDescription") String description,
            @RequestParam("movieVideo") MultipartFile[] videoFiles
    		
    		) {
    	
    	System.out.println(title);
    	
      String[] allowedExtensions = {"jpg", "jpeg", "png", "gif", "bmp","webp"}; // Add more if needed
      String fileName="";
    	if (files.length > 0) {
            MultipartFile file = files[0];
            fileName = file.getOriginalFilename();
            
            if (fileName != null && isValidImageType(fileName, allowedExtensions)) {
                Path fileNameAndPath = Paths.get(uploadDirectory, fileName);
                try {
                    Files.write(fileNameAndPath, file.getBytes());
                    System.out.println("Saved image path: " + fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                    return "create"; // Handle the error case
                }
            } else {
                System.out.println("Invalid file type for: " + fileName);
                return "create"; // Handle the invalid file type case
            }
        } else {
            System.out.println("No files uploaded.");
            return "create"; // Handle the case where no files were uploaded
        }
    	
    	String[] allowedVideoExtensions = {"mp4", "avi", "mov", "mkv"};
    	 String videoFileName = "";
    	  if (videoFiles.length > 0) {
    	        MultipartFile videoFile = videoFiles[0];
    	        videoFileName = videoFile.getOriginalFilename();
    	        videoFileName=videoFileName.replace(" ", "-");

    	        if (videoFileName != null && isValidVideoType(videoFileName, allowedVideoExtensions)) {
    	            Path videoPath = Paths.get(uploadDirectory1, videoFileName);
    	            try {
    	                Files.write(videoPath, videoFile.getBytes());
    	                System.out.println("Saved video path: " + videoFileName);
    	            } catch (IOException e) {
    	                e.printStackTrace();
    	                return "create"; // Handle the error case
    	            }
    	        } else {
    	            System.out.println("Invalid file type for: " + videoFileName);
    	            return "create"; // Handle the invalid file type case
    	        }
    	    } else {
    	        System.out.println("No video files uploaded.");
    	        return "create"; // Handle the case where no files were uploaded
    	    }
    	
    	  fileName="/uploads/"+fileName;
    	  videoFileName="/uploads/movieVideo/"+videoFileName;
    	  
    	  
    	  fileName=fileName.replace(" ", "_");
    	  Movies newMovie = new Movies();
          newMovie.setTitle(title);
          newMovie.setDescription(description);
          newMovie.setThumbnail(fileName);
          newMovie.setVideo(videoFileName);
    	  
    	  
    	  
    	  movieRepository.save(newMovie);
    	
    	
    	 
    	
    	
    	

         
         
         return "create";
    	
    	
    	
    	
    	
    	
	}
    
    	


    private boolean isValidImageType(String fileName, String[] allowedExtensions) {
        String fileExtension = getFileExtension(fileName);
        for (String ext : allowedExtensions) {
            if (fileExtension.equalsIgnoreCase(ext)) {
                return true;
            }
        }
        return false;
    }

    private String getFileExtension(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf('.');
        if (lastIndexOfDot > 0 && lastIndexOfDot < fileName.length() - 1) {
            return fileName.substring(lastIndexOfDot + 1);
        }
        return "";
    }
    private boolean isValidVideoType(String fileName, String[] allowedExtensions) {
        String extension = getExtension(fileName);
        for (String allowed : allowedExtensions) {
            if (allowed.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }
    private String getExtension(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf('.');
        return (lastIndexOfDot == -1) ? "" : fileName.substring(lastIndexOfDot + 1);
    }
    
    
    
    
    @GetMapping("/getAllMovies")
    public String getAllMovies(Model model,@RequestParam(required = true) Integer pagenumber){
    	
    	
    	
	    Long total=movieRepository.count(); 
	    
	    List<Movies> movies;
	    
	    Integer offset= (pagenumber-1)* total.intValue();
	    movies = movieRepository.findMoviesByPage(offset);// fetch all movies
//	    model.addAttribute("movie", movieRepository);
	    
//	    List<Map<String, String>> movieList = new ArrayList<>();
//        for (Movies movie : movies) {
//            Map<String, String> movieData = new HashMap<>();
//            movieData.put("title", movie.getTitle());
//            movieData.put("viewUrl", "view.html?id=" + movie.getId()); // Assuming you have a way to fetch the movie by ID
//            movieData.put("editUrl", "edit.html?id=" + movie.getId());
//            movieList.add(movieData); 
//        }
	    System.out.println(movies.toString());

        model.addAttribute("movies", movies);
	    
        return "AdminDashbord";
    }
    
    
    
    
}
