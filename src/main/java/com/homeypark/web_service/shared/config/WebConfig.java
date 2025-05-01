package com.homeypark.web_service.shared.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // permite todas las rutas
                        .allowedOrigins("*") // permite todas las fuentes
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // permite todos los métodos HTTP
                        .allowedHeaders("*"); // permite todos los headers
            }
        };
    }
}
