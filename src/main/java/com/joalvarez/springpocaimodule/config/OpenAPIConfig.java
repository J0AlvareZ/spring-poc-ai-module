package com.joalvarez.springpocaimodule.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

	private final AppBaseProperties properties;

	public OpenAPIConfig(AppBaseProperties properties) {
		this.properties = properties;
	}

    @Bean
    public OpenAPI api() {
        License license = new License()
                .name("license")
                .url("licenseUrl");

        Contact contact = new Contact();

        Info info = new Info()
                .title(this.properties.name())
                .description(this.properties.description())
                .contact(contact)
                .license(license)
                .version(this.properties.version());

        return new OpenAPI()
                .info(info);
    }
}