package com.joalvarez.springpocaimodule.exception;

import com.joalvarez.springpocaimodule.data.dto.ErrorResponseDTO;
import com.joalvarez.springpocaimodule.data.dto.general.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joalvarez.springpocaimodule.shared.LoggerHelper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ai.retry.NonTransientAiException;
import org.springframework.ai.retry.TransientAiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.stream.Stream;

@RestControllerAdvice
public class GlobalExceptionHandler implements LoggerHelper {

    private final ObjectMapper mapper;

    public GlobalExceptionHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleValidationExceptions(MethodArgumentNotValidException e) throws JsonProcessingException {
        final Stream<ObjectError> errors = e.getBindingResult().getAllErrors().stream().filter(FieldError.class::isInstance);

		final JsonNode details = this.mapper.readTree(
			this.mapper.writeValueAsString(errors.map(FieldError.class::cast)
				.map(
					(error) -> String.format(
						"The field %s is invalid for the value %s with the following cause: %s",
						error.getField(),
						error.getRejectedValue(),
						error.getDefaultMessage())
				)
				.toList()));

        return ResponseEntity
                .badRequest()
                .body(
                        new ResponseDTO(
                                String.valueOf(HttpStatus.BAD_REQUEST.value()),
                                "Some fields are invalid, check details",
                                details
                        )
                );
    }

	@ExceptionHandler(NonTransientAiException.class)
	public ResponseEntity<ErrorResponseDTO> handle(NonTransientAiException ex, HttpServletRequest request) throws JsonProcessingException {
		ErrorResponseDTO response = new ErrorResponseDTO(
			Instant.now(),
			HttpStatus.BAD_GATEWAY.value(),
			HttpStatus.BAD_GATEWAY.getReasonPhrase(),
			"AI provider rejected the request",
			request.getRequestURI()
		);

		this.warn("Error AI provider: {}, {}", ex.getMessage(), this.mapper.writeValueAsString(response));

		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
	}

	@ExceptionHandler(TransientAiException.class)
	public ResponseEntity<ErrorResponseDTO> handle(TransientAiException ex, HttpServletRequest request) throws JsonProcessingException {
		ErrorResponseDTO response = new ErrorResponseDTO(
			Instant.now(),
			HttpStatus.SERVICE_UNAVAILABLE.value(),
			HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase(),
			"AI provider is temporarily unavailable. Please try again later.",
			request.getRequestURI()
		);

		this.warn("Error AI provider: {}, {}", ex.getMessage(), this.mapper.writeValueAsString(response));

		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
	}

}