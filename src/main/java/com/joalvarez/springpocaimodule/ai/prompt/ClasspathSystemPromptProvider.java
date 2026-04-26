package com.joalvarez.springpocaimodule.ai.prompt;

import com.joalvarez.springpocaimodule.config.AiConfigProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class ClasspathSystemPromptProvider {

	private final String prompt;

	public ClasspathSystemPromptProvider(AiConfigProperties properties) {
		this.prompt = this.loadPrompt(properties.getSystemPrompt());
	}

	public String getPrompt() {
		return this.prompt;
	}

	private String loadPrompt(Resource resource) {
		try (InputStream inputStream = resource.getInputStream()) {
			return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new IllegalStateException("Could not load system prompt resource", e);
		}
	}
}
