package com.example.irrigationsystem.plot.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.irrigationsystem.common.IrrigationFrequency;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PlotConfig implements Serializable {
	
	private String crop;
	
	private String plotConfigId;
	
	@NotNull
	private Date startTime;
	
	@NotNull
	private Long duration;
	
	@NotNull
	private Integer waterQuantity;
	
	private IrrigationFrequency frequency;
}