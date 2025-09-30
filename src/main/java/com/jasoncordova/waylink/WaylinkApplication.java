package com.jasoncordova.waylink;

import com.jasoncordova.waylink.data.repository.UrlRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WaylinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaylinkApplication.class, args);
    }

    @Bean
    public CommandLineRunner run (UrlRepository urlRepository) {

        return args -> {

            urlRepository.findAll().forEach(System.out::println);

        };

    }

}
