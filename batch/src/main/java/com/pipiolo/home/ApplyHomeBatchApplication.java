package com.pipiolo.home;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication(scanBasePackages = "com.pipiolo.home")
public class ApplyHomeBatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApplyHomeBatchApplication.class, args);
    }
}
