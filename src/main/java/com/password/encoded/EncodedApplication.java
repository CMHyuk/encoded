package com.password.encoded;

import com.password.encoded.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(AppConfig.class)
@SpringBootApplication
public class EncodedApplication {

    public static void main(String[] args) {
        SpringApplication.run(EncodedApplication.class, args);
    }

}
