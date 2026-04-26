package com.joalvarez.springpocaimodule.service;

import com.joalvarez.springpocaimodule.config.AiConfigProperties;
import com.joalvarez.springpocaimodule.data.dto.ChatRequestDTO;
import com.joalvarez.springpocaimodule.data.dto.ChatResponseDTO;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
public class ChatService {

	private final ChatClient client;
	private final AiConfigProperties properties;

	public ChatService(ChatClient.Builder builder, AiConfigProperties properties) {
		this.client = builder.build();
		this.properties = properties;
	}

	public ChatResponseDTO chat(ChatRequestDTO message) {
		return new ChatResponseDTO(this.client
			.prompt()
			.system(this.loadSystemPrompt())
			.user(message.message())
			.call()
			.content());
	}

	private String loadSystemPrompt() {
		try (InputStream inputStream = this.properties.getSystemPromptResource().getInputStream()) {
			return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new IllegalStateException("Could not load system prompt file", e);
		}
	}
}
