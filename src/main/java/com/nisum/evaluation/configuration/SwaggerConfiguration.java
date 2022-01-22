package com.nisum.evaluation.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI(@Value("${nisum.app.version}") String appVersion) {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Users API").version(appVersion));
    }
}
