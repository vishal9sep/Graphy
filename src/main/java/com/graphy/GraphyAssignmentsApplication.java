package com.graphy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GraphyAssignmentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphyAssignmentsApplication.class, args);
	}

}
