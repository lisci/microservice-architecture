package com.mrclsc.springcloudapigatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringCloudApiGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudApiGatewayServiceApplication.class, args);
	}

}
