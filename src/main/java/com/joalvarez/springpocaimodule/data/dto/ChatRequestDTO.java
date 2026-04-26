package com.joalvarez.springpocaimodule.data.dto;

import com.joalvarez.springpocaimodule.data.dto.general.BaseDTO;
import jakarta.validation.constraints.NotBlank;

public record ChatRequestDTO(
	@NotBlank(message = "message must not be blank") String message
) implements BaseDTO {}
