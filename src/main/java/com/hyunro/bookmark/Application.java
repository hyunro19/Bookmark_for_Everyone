package com.hyunro.bookmark;

import com.hyunro.bookmark.config.auth.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

// @EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
