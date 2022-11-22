package com.example.irrigationsystem.irrigationscheduler.model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.irrigationsystem.common.AuditableModel;
import com.example.irrigationsystem.common.IrrigationStatus;
import com.example.irrigationsystem.plot.model.PlotConfigModel;
import com.example.irrigationsystem.plot.model.PlotModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "irrigation_schedule")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IrrigationScheduleModel extends AuditableModel {

	private static final long serialVersionUID = 1L;

	@Column(name = "start_time", columnDefinition = "TIME", nullable = false)
	private LocalTime startTime;

	@Column(name = "end_time", columnDefinition = "TIME", nullable = false)
	private LocalTime endTime;

	@Column(name = "water_quantity", nullable = false)
	private Integer waterQuantity;

	@Column(name = "irrigation_status", nullable = false)
	@Enumerated(EnumType.STRING)
	private IrrigationStatus irrigationStatus;

	@OneToOne
	PlotModel plot;

	@OneToOne
	PlotConfigModel plotConfig;
}
