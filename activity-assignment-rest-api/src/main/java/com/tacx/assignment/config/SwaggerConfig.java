package com.tacx.assignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket productApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tacx.assignment"))
                .paths(PathSelectors.regex("/api.*"))
                .build();
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("Tacx - Activity and Records")
                .description("Developed using Java RESTful Web Service")
                .version("1.0")
                .build();
    }
}

