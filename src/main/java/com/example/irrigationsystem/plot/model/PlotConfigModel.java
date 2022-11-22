package com.example.irrigationsystem.plot.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.irrigationsystem.common.AuditableModel;
import com.example.irrigationsystem.common.IrrigationFrequency;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "plot_config", indexes = @Index(columnList = "product_config_id", unique = true))
@Getter
@Setter
public class PlotConfigModel extends AuditableModel {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "product_config_id", length = 36, nullable = false, unique = true)
	private String plotConfigId;
	
	@Column(name = "start_time", nullable = false)
	private Date startTime;
	
	@Column(name = "duration", nullable = false)
	private Long duration;
	
	@Column(name = "water_quantity", nullable = false)
	private Long waterQuantity;
	
	@Column(name = "frequency")
	@Enumerated(EnumType.STRING)
	private IrrigationFrequency frequency;
	
	@Column(name = "crop", length = 50)
	private String crop;
	
	@ManyToOne
	@JoinColumn(name = "plot_id", nullable = false)
	PlotModel plot;
}
