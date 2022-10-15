package com.no7sag.alkemychallengejava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.no7sag.alkemychallengejava.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Alkemy Challenge Backend",
                "Java Spring Boot (Disney API)",
                "1.0",
                "https://www.alkemy.org",
                new Contact("Alejandro Corvalán", "https://github.com/no7sag", "email@deprueba.com"),
                "Alejandro Corvalán - LinkedIn",
                "https://www.linkedin.com/in/alejandrocorvalan/",
                Collections.emptyList());
    }

}