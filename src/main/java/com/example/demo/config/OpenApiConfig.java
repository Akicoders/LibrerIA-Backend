package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI myOpenAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Server url in dev environment ");

        Contact contact = new Contact();
        contact.setEmail("akicoders@gmail.com");
        contact.setName("Akicoder");
        contact.setUrl("https://github.com/Akicoders");

        License myLicense = new License().name("MIT License").url("https://opensource.org/licenses/MIT");

        Info info = new Info();
        info.title("Gestionador de API  ");
        info.version("1.0.0");
        info.contact(contact);
        info.license(myLicense);
        info.description("""
                        Esta es la documentación para la API del proyecto **LibrerIA API**.
                        Proporciona endpoints para gestionar recursos y operar con eficiencia.
                        """);

        return new OpenAPI().info(info).servers(List.of(server));
    }
}
