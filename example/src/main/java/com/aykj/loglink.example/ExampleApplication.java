package com.aykj.loglink.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Create On 2019-08-29 20:21
 *
 * @author mo
 */
@SpringBootApplication(scanBasePackages = "com.aykj")
public class ExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }
}
