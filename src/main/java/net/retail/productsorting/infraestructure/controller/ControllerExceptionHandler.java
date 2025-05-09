package net.retail.productsorting.infraestructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler({InvalidRequestException.class})
	protected ResponseEntity<String> handleInvalidRequestException(
			InvalidRequestException ex) {

		return ResponseEntity.badRequest().body(ex.getMessage());
	}
}
