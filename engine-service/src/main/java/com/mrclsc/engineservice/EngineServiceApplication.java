package com.mrclsc.engineservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EngineServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EngineServiceApplication.class, args);
    }

}
