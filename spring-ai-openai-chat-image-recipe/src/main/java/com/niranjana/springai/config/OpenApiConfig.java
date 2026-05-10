package com.niranjana.springai.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Spring AI API concept",
                version = "1.0",
                description = "AI-powered APIs for Chat, Image, and Recipe generation"
        )
)
@Configuration
public class OpenApiConfig {
}