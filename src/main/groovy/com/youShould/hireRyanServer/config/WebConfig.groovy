package com.youShould.hireRyanServer.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("\${cors.allowed-origins}")
    String allowedOrigins

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        def origins = allowedOrigins.split(",")

        registry.addMapping("/**") // Allow CORS for all endpoints
                .allowedOrigins(origins) // Allow requests from Angular frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow these HTTP methods
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true); // Allow credentials if needed (cookies, sessions)
    }
}