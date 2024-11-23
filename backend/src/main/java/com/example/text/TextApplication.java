package com.example.text;
import java.util.HashSet;  
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.example.text.admin"})
public class TextApplication {

    public static void main(String[] args) {
        SpringApplication.run(TextApplication.class, args);
    }
   
    }
