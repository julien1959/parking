package com.formation.parking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ParkingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkingApplication.class, args);
    }

    @Bean // Bean de configuration , pour créer RestTemplate qui sera injecter dans ParkingAPIDAOimpl uniquement si la classe ParkingAPIDAOImpl est un component(avec annotation @Repository)
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}
