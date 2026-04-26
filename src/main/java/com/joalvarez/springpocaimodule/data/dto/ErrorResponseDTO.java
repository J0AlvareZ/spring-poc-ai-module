package com.joalvarez.springpocaimodule.data.dto;

import com.joalvarez.springpocaimodule.data.dto.general.BaseDTO;

import java.time.Instant;

public record ErrorResponseDTO(
	Instant timestamp,
	int status,
	String error,
	String message,
	String path
) implements BaseDTO {}
