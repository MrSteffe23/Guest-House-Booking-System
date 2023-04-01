package com.booking.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the main Class and the Spring Boot Application.
 */
@SpringBootApplication
public class ProjectApplication {

	/**
	 * Method used to run the entire project.
	 * @param args arguments coming from the console
	 */
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

}
