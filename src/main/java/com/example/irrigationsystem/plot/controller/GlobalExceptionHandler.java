package com.example.irrigationsystem.plot.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.irrigationsystem.common.ResourceNotFoundException;
import com.example.irrigationsystem.common.ResponseObject;
import com.example.irrigationsystem.common.ResponseStatus;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		logger.error("Validation Error Occured", exception);

		List<ResponseObject.ErrorEntry> errors = new ArrayList<>();
		for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
			ResponseObject.ErrorEntry entry = ResponseObject.ErrorEntry.builder().code(fieldError.getCode())
					.message(fieldError.getField() + " : " + fieldError.getDefaultMessage()).build();
			errors.add(entry);
		}

		return super.handleExceptionInternal(exception, ResponseObject.builder().status(ResponseStatus.FAILURE)
				.message("Validation Error").data(errors).build(), headers, status, request);
	}

	@ExceptionHandler(value = { ResourceNotFoundException.class })
	protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exception,
			WebRequest request) {

		logger.error("Resource Not Found Error Occured", exception);

		return handleExceptionInternal(exception,
				ResponseObject.builder().status(ResponseStatus.FAILURE).message("Resource not found").build(),
				new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
}
