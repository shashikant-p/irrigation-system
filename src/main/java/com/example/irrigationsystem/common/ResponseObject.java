package com.example.irrigationsystem.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Schema(description = "The object representing the API responses.")
public class ResponseObject {

	@Schema(description = "Indicates if the API response was success or a failure")
	private ResponseStatus status;

	@Schema(description = "A descriptive message for the response status")
	private String message;

	@Schema(description = "This will hold the response body. It will have plot or plot configuration details in case of success. In case of any errors, it will hold the error details")
	@JsonInclude(Include.NON_NULL)
	private Object data;

	@Builder
	public static class ErrorEntry {

		@Getter
		@Setter
		private String code;

		@Getter
		@Setter
		private String message;
	}
}
