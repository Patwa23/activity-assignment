package com.tacx.assignment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${cross-origin.allowed-origins.frontend}")
    private String angularFrontendUrl;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(angularFrontendUrl)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
                .allowCredentials(false).maxAge(3600);
    }

//    @Override
//    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
//
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }



}
