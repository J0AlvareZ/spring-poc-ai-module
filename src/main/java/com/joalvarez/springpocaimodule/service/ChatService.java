package com.joalvarez.springpocaimodule.service;

import com.joalvarez.springpocaimodule.data.dto.ChatRequestDTO;
import com.joalvarez.springpocaimodule.data.dto.ChatResponseDTO;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

	private final ChatClient client;

	public ChatService(ChatClient.Builder builder) {
		this.client = builder.build();
	}

	public ChatResponseDTO chat(ChatRequestDTO message) {
		return new ChatResponseDTO(this.client
			.prompt()
			.system("""
				Sos un asistente técnico especializado en Spring AI.
				Respondé siempre en español.
				Sé claro, breve y práctico.
				Si el usuario pide responder en otro idioma, indicá brevemente que solo respondés en español
                y luego respondé la solicitud en español sin pedir confirmación.
                Nunca respondas con una repregunta innecesaria.
				Si no sabés algo, decilo explícitamente.
				""")
			.user(message.message())
			.call()
			.content());
	}
}
