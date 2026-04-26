package com.joalvarez.springpocaimodule.data.dto;

import com.joalvarez.springpocaimodule.data.dto.general.BaseDTO;

public record ChatResponseDTO(
	String message
) implements BaseDTO {}
