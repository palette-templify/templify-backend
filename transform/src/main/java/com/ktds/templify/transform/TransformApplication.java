package com.ktds.templify.transform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients(basePackages = {"com.ktds.templify.transform.client"})
public class TransformApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransformApplication.class, args);
    }
}
