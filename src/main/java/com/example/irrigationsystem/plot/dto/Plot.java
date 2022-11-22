package com.example.irrigationsystem.plot.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
}
