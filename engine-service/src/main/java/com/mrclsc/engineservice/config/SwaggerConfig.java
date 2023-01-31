package com.mrclsc.engineservice.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    GroupedOpenApi eventFraudApis() { // group all APIs with `event fraud` in the path
        return GroupedOpenApi.builder().group("engine").pathsToMatch("/**/engine/**").build();
    }

    @Bean
    GroupedOpenApi userApis() { // group all APIs with `user` in the path
        return GroupedOpenApi.builder().group("user").pathsToMatch("/**/user/**").build();
    }

    @Bean
    public OpenAPI apiInfo() {
        final var securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")))
                .info(
                        new Info()
                                .title("Engine Service REST API")
                                .description("REST API for engine microservice")
                                .version("1.0"));
    }
}
