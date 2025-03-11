package com.ktds.templify.history;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = {"com.ktds.templify.history", "com.ktds.templify.common"})
public class HistoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(HistoryApplication.class, args);
    }
}