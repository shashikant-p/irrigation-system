package com.example.irrigationsystem.plot.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
public class Plot implements Serializable {

	private String plotId;

	@NotEmpty
	private String address;

	@NotEmpty
	private String city;

	@NotEmpty
	private String province;

	@NotEmpty
	private String area;

	@JsonInclude(Include.NON_NULL)
	private List<PlotConfig> configs;
}
