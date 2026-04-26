package com.joalvarez.springpocaimodule.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@ConfigurationProperties(prefix = "app.ai")
public class AiConfigProperties {

	private Resource systemPrompt;

	public Resource getSystemPrompt() {
		return systemPrompt;
	}

	public void setSystemPrompt(Resource systemPrompt) {
		this.systemPrompt = systemPrompt;
	}
}
