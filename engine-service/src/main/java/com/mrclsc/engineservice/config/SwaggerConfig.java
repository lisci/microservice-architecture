package com.mrclsc.engineservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.mrclsc.engineservice.controller"))
                .paths(PathSelectors.ant("/engine/*"))
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, newArrayList(
                        new ResponseBuilder().code("500")
                                .description("500 message").build(),
                        new ResponseBuilder().code("403")
                                .description("Forbidden").build()));
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("My REST API", "Some custom description of API.", "API TOS", "Terms of service",
                new Contact("Marco Lisci", "www.test.com", "test@test.com"), "License of API",
                "API license URL", Collections.emptyList());
        return apiInfo;
    }
}