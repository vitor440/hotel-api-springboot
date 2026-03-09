package com.hotelapi.hotelapi.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Hotel API",
                description = "Api de gerenciamento de hoteis, quartos e reservas.",
                version = "v1",
                contact = @Contact(
                        name = "Vitor Oliveira",
                        email = "vitor16.souzaoliver@gmail.com"
                )
        )
)
public class SwaggerConfiguration {

}
