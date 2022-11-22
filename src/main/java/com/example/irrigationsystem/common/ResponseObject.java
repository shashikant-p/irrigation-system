package com.example.irrigationsystem.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseObject {
	private ResponseStatus status;
	private String message;

	@JsonInclude(Include.NON_NULL)
	private Object data;
}
