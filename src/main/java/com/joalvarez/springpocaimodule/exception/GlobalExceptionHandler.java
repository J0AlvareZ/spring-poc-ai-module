package com.joalvarez.springpocaimodule.exception;

import com.joalvarez.springpocaimodule.data.dto.general.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Stream;

@RestControllerAdvice
public class GlobalExceptionHandler {

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

}