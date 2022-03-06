package com.kitchentracker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class MyConfiguration {

	 
	 @Bean
	 public LayoutDialect layoutDialect() {
		 return new LayoutDialect();
	 }
	
}
