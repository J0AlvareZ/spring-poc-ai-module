package com.joalvarez.springpocaimodule.config;

import com.joalvarez.springpocaimodule.data.dto.general.BaseDTO;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public record AppBaseProperties(
	String version,
	String description,
	String name
) implements BaseDTO {}