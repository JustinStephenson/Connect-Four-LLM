package com.justinsoftware.connect_four_llm.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    /**
     * Configures the Cross-Origin Resource Sharing (CORS) settings for the application.
     * This method creates and returns a {@link WebMvcConfigurer} bean that defines
     * CORS mappings applied globally to all request mappings. The configuration allows
     * all origins, methods, and headers.
     *
     * @return a {@link WebMvcConfigurer} that defines the global CORS mapping settings.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .allowedHeaders("*");
            }
        };
    }
}