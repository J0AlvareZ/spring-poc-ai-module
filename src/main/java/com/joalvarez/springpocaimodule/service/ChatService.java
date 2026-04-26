package com.joalvarez.springpocaimodule.service;

import com.joalvarez.springpocaimodule.ai.prompt.ClasspathSystemPromptProvider;
import com.joalvarez.springpocaimodule.data.dto.ChatRequestDTO;
import com.joalvarez.springpocaimodule.data.dto.ChatResponseDTO;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

	private final ChatClient client;
	private final String systemPrompt;

	public ChatService(ChatClient.Builder builder, ClasspathSystemPromptProvider provider) {
		this.client = builder.build();
		this.systemPrompt = provider.getPrompt();
	}

	public ChatResponseDTO chat(ChatRequestDTO message) {
		return new ChatResponseDTO(this.client
			.prompt()
			.system(this.systemPrompt)
			.user(message.message())
			.call()
			.content());
	}
}
