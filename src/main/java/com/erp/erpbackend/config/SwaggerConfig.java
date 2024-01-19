package com.erp.erpbackend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myOpenAPI() {

        Contact contact = new Contact();
        contact.setEmail("sarvar55mszde@gmail.com");
        contact.setName("Sarvar55");
        contact.setUrl("https://sarvarmusa.vercel.app/");

        Info info = new Info()
                .title("Course ERP API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage tutorials.")
                .termsOfService("https://sarvarmusa.vercel.app/");

        return new OpenAPI().info(info);
    }
}
