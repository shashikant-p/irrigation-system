package com.example.irrigationsystem.plot.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "The Plot Object")
public class Plot implements Serializable {

	@Schema(description = "Unique identifier for a plot", accessMode = AccessMode.READ_ONLY)
	private String plotId;

	@Schema(description = "The plot's address", requiredMode = RequiredMode.REQUIRED)
	private String address;

	@Schema(description = "The plot's city", requiredMode = RequiredMode.REQUIRED)
	@NotEmpty
	private String city;

	@Schema(description = "The plot's province/state", requiredMode = RequiredMode.REQUIRED)
	@NotEmpty
	private String province;

	@Schema(description = "The area of covered by the plot in square feet", requiredMode = RequiredMode.REQUIRED)
	@NotEmpty
	private String area;

	@Schema(description = "The plot's configurations", accessMode = AccessMode.READ_ONLY)
	@JsonInclude(Include.NON_NULL)
	private List<PlotConfig> configs;
}
