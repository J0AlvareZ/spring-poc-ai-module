package com.joalvarez.springpocaimodule.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@ConfigurationProperties(prefix = "app.ai")
public class AiConfigProperties {

	private Resource systemPromptResource;

	public Resource getSystemPromptResource() {
		return systemPromptResource;
	}

	public void setSystemPromptResource(Resource systemPromptResource) {
		this.systemPromptResource = systemPromptResource;
	}
}
