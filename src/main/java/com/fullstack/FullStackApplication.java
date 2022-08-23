package com.fullstack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class FullStackApplication {

	public static void main(String[] args) {

		SpringApplication.run(FullStackApplication.class, args);


		//final String uri = "https://api.dictionaryapi.dev/api/v2/entries/en/bird";
		//RestTemplate restTemplate = new RestTemplate();
		//String result = restTemplate.getForObject(uri, String.class);
		//System.out.println(result);


	}



}
