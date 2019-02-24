package com.shafique.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author mohammedshafique
 */
@SpringBootApplication
@ComponentScan("com.shafique.*")
public class AngularjsSpringbootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AngularjsSpringbootDemoApplication.class, args);
	}

}

