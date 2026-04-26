package com.joalvarez.springpocaimodule.controller;

import com.joalvarez.springpocaimodule.data.dto.ChatRequestDTO;
import com.joalvarez.springpocaimodule.data.dto.ChatResponseDTO;
import com.joalvarez.springpocaimodule.service.ChatService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ai")
public class ChatController {

	private final ChatService service;

	public ChatController(ChatService service) {
		this.service = service;
	}

	@PostMapping("chat")
	public ChatResponseDTO chat(@Valid @RequestBody ChatRequestDTO message) {
		return this.service.chat(message);
	}
}
