package com.joalvarez.springpocaimodule;

import com.joalvarez.springpocaimodule.config.GlobalConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication(
	scanBasePackages = GlobalConfig.BASE_PACKAGE,
	exclude = {
		LiquibaseAutoConfiguration.class,
		DataSourceAutoConfiguration.class,
		JpaRepositoriesAutoConfiguration.class
	}
)
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
@ConfigurationPropertiesScan(basePackages = {"com.joalvarez.springpocaimodule.config" })
public class SpringPocAiModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPocAiModuleApplication.class, args);
    }
}