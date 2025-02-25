package com.ktds.templify.write;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WriteApplication {
    public static void main(String[] args) {
        SpringApplication.run(WriteApplication.class, args);
    }
}