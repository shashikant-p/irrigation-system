package com.example.irrigationsystem.plot.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.irrigationsystem.common.AuditableModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "plot", indexes = @Index(columnList = "plotId", unique = true))
@ToString
@Getter
@Setter
public class PlotModel extends AuditableModel {

	private static final long serialVersionUID = 1L;

	@Column(name = "plotId", length = 36, nullable = false, unique = true)
	private String plotId;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "province", nullable = false)
	private String province;

	@Column(name = "area", precision = 10, scale = 2, nullable = false)
	private BigDecimal area;

	@OneToMany(mappedBy = "plot", cascade = { CascadeType.REMOVE })
	List<PlotConfigModel> configs;
}
