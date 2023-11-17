package com.tfl.roadchecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication

public class RoadcheckerMainApplication {


	public static void main(String[] args) {
		SpringApplication.run(RoadcheckerMainApplication.class, args);
	}



}
