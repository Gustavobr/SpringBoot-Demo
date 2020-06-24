package com.exemplo.Big;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.Big.Model.Produto;

@SpringBootApplication
@EnableAutoConfiguration
@RestController
@EnableJpaRepositories(basePackages = "com.exemplo.Big.repository")
public class ApiBigApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiBigApplication.class, args);
	}
	
	
	
	

}
