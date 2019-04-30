package com.ibm.k8api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringK8sApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringK8sApiApplication.class, args);
	}
	
    @Bean
    public RestTemplate getRestTemplate() {
       return new RestTemplate();
    }

}
