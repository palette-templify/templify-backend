package com.ktds.templify.write;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients(basePackages = {"com.ktds.templify.write.client"})
public class WriteApplication {
    public static void main(String[] args) {
        SpringApplication.run(WriteApplication.class, args);
    }
}