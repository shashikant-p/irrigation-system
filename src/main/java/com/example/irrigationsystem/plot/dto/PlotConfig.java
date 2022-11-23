package com.example.irrigationsystem.plot.dto;

import java.io.Serializable;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import com.example.irrigationsystem.common.IrrigationFrequency;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "The Plot Configuration Object. This object is used to configure a plot for irrigation schedules. More than one configuration can be associated with a plot.")
public class PlotConfig implements Serializable {

	@Schema(description = "The crop that has been planted on the plot for which irrigation is required", requiredMode = RequiredMode.REQUIRED)
	private String crop;

	@Schema(description = "Unique identifier for a plot configuration", accessMode = AccessMode.READ_ONLY)
	private String plotConfigId;

	@Schema(description = "The time when the irrigation has to be started", requiredMode = RequiredMode.REQUIRED)
	@NotNull
	private LocalTime startTime;

	@Schema(description = "The time duration from the start time for which the plot needs to be irrigated", requiredMode = RequiredMode.REQUIRED)
	@NotNull
	private Long duration;

	@Schema(description = "The quantity of water in liters that needs to be used during the time duration", requiredMode = RequiredMode.REQUIRED)
	@NotNull
	private Integer waterQuantity;

	@Schema(description = "This is to indicate if this configuration is a one time configuration or if this configuration is to be applied to the plot everyday at the give time. i.e If the plot needs to be irrigated daily or one time.", requiredMode = RequiredMode.REQUIRED)
	@NotNull
	private IrrigationFrequency frequency;

	@Schema(description = "Indicated if this configuration is active or nor. Only active configurations are processed", requiredMode = RequiredMode.NOT_REQUIRED)
	private Boolean active;

}
