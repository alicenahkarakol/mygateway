package com.mygateway.mygateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

@SpringBootApplication
public class MygatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MygatewayApplication.class, args);
	}
	

	
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
	    return builder.routes()
	        .route(p -> p
	            .path("/get")
	            .filters(f -> f.rewritePath("/get", "/appconfigs"))
	            .uri("http://localhost:3000"))
	        .route(p -> p
		            .path("/getDetails/{id}")
		            .filters(f -> f.rewritePath("/getDetails/(?<id>.*)", "/appconfigs/${id}"))
		            .uri("http://localhost:3000"))
	        .route(p -> p
	        		.method(HttpMethod.POST)
	        		.and()
		            .path("/post")
		            .filters(f -> f.rewritePath("/post", "/appconfigs"))
		            .uri("http://localhost:3000"))
	        .route(p -> p
		            .path("/update/{id}")
		            .filters(f -> f.rewritePath("/update/(?<id>.*)", "/appconfigs/${id}"))
		            .uri("http://localhost:3000"))
	        .route(p -> p
		            .path("/delete/{id}")
		            .filters(f -> f.rewritePath("/delete(?<id>.*)", "/appconfigs/${id}"))
		            .uri("http://localhost:3000"))
	        .build();
	}
	
	

}
