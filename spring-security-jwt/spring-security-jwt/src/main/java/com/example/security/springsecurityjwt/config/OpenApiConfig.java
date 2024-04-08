package com.example.security.springsecurityjwt.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Adkham",
                        email = "adhamsalaydinov5@gmail.com",
                        url = "default-url"
                ),
                description = "Demo OpenApi documentation for Spring security",
                title = "OpenApi specification - Adkham",
                version = "1.0",
                license = @License(
                        name = "Default License",
                        url = "https://example.com"
                ),
                termsOfService = "Default Terms of service"

        ),
        servers = {
                @Server(
                        description = "Local server",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Default description",
                        url = "https://example.com"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
