package com.rafaelMelo.rpg_cyberpunk_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica a configuração de CORS para todos os endpoints ("/**")
            .allowedOrigins("http://localhost:4200") // Permite requisições vindas desta origem (seu app Angular)
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS"); // Permite que a origem use estes métodos HTTP
    }
}