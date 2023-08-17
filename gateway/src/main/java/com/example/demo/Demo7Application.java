package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Demo7Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo7Application.class, args);
    }



    //@Bean
    public RouteLocator router(RouteLocatorBuilder builder){
       return builder.routes()
               .route(r->r.path("/logistique/**").uri("lb://SERVICE-LOGISTIQUE"))
               .route(r->r.path("/users/**").uri("lb://USERS-SERVICE"))
               .build();
    }
    @Bean
    public DiscoveryClientRouteDefinitionLocator dynamiqueRoutes(ReactiveDiscoveryClient rdc , DiscoveryLocatorProperties dlp){
        return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
    }
}
