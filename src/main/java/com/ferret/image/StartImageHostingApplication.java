package com.ferret.image;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class StartImageHostingApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartImageHostingApplication.class, args);
    }

    @Profile("standalone")
    @Bean
    CommandLineRunner init(IFileService iFileService) {
        return args -> {
            System.out.println("init springboot");
        };
    }
}