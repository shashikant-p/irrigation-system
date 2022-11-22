package com.example.irrigationsystem.plot.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.irrigationsystem.common.ResourceNotFoundException;
import com.example.irrigationsystem.common.ResponseObject;
import com.example.irrigationsystem.common.ResponseStatus;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { ResourceNotFoundException.class })
	protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		return handleExceptionInternal(ex, ResponseObject.builder().status(ResponseStatus.FAILURE).message("Resource not found").build(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
}
