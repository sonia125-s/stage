package com.example.gestionlogistique;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Date;
@EnableFeignClients
@SpringBootApplication
public class GestionLogistiqueApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionLogistiqueApplication.class, args);
    }
   
}
