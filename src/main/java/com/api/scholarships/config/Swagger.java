package com.api.scholarships.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger {
  @Bean
  public OpenAPI springShopOpenAPI() {
    return new OpenAPI()
        .info(new Info().title("Scholarships API")
            .description("Spring shop sample application")
            .version("v1.0.0")
            .description("This is a Spring Boot REST application which was created to manage scholarships in the best way to have all the information centralized, so that everyone can have this opportunity.")
        );
  }
}
