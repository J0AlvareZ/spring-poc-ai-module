package com.joalvarez.springpocaimodule;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
@ConfigurationPropertiesScan(basePackages = {"com.joalvarez.springpocaimodule.config" })
public class SpringPocAiModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPocAiModuleApplication.class, args);
    }
}