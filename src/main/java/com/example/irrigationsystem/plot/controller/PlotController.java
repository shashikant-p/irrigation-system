package com.example.irrigationsystem.plot.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.irrigationsystem.common.ResponseObject;
import com.example.irrigationsystem.common.ResponseStatus;
import com.example.irrigationsystem.plot.dto.Plot;
import com.example.irrigationsystem.plot.dto.PlotConfig;
import com.example.irrigationsystem.plot.service.PlotConfigService;
import com.example.irrigationsystem.plot.service.PlotService;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/plot")
public class PlotController {

	private static final Logger logger = LoggerFactory.getLogger(PlotController.class);

	@Autowired
	PlotService plotService;

	@Autowired
	PlotConfigService plotConfigService;

	@Operation(summary = "Get all plots", description = "Returns a a list of all the plots in the system")
	@GetMapping
	public ResponseEntity<ResponseObject> getPlots() {

		logger.info("getPlots called");

		List<Plot> plots = plotService.getPlots();
		return ResponseEntity
				.ok(ResponseObject.builder().status(ResponseStatus.SUCCESS).message("Success").data(plots).build());
	}

	@Operation(summary = "Get plot details", description = "Fetches the properties for the given plotId")
	@GetMapping("/{plotId}")
	public ResponseEntity<ResponseObject> getPlot(@PathVariable(name = "plotId", required = true) String plotId) {

		logger.info("getPlot called with id {} ", plotId);

		Plot plot = plotService.getPlot(plotId);
		return ResponseEntity
				.ok(ResponseObject.builder().status(ResponseStatus.SUCCESS).message("Success").data(plot).build());
	}

	@Operation(summary = "Create a plot", description = "Creates a plot with the provided properties")
	@PostMapping
	public ResponseEntity<ResponseObject> addPlot(@Valid @RequestBody Plot plot) {

		logger.info("addPlot called");

		Plot createdPlot = plotService.addPlot(plot);
		return ResponseEntity.ok(
				ResponseObject.builder().status(ResponseStatus.SUCCESS).message("Success").data(createdPlot).build());
	}

	@Operation(summary = "Update a plot", description = "Updates plot properties for the given plotId")
	@PutMapping("/{plotId}")
	public ResponseEntity<ResponseObject> updatePlot(@PathVariable(name = "plotId", required = true) String plotId,
			@Valid @RequestBody Plot plot) {

		logger.info("updatePlot called with id {} ", plotId);

		Plot updatedPlot = plotService.updatePlot(plotId, plot);
		return ResponseEntity.ok(
				ResponseObject.builder().status(ResponseStatus.SUCCESS).message("Success").data(updatedPlot).build());
	}

	@Operation(summary = "Fetches the plot configurations", description = "Gets all the plot configurations associated with a give plot. A plot may have more than one configuration")
	@GetMapping("/{plotId}/config")
	public ResponseEntity<ResponseObject> getPlotConfig(@PathVariable(name = "plotId", required = true) String plotId) {

		logger.info("getPlotConfig called for plot id {} ", plotId);

		List<PlotConfig> plotConfigs = plotConfigService.getPlotConfigs(plotId);
		return ResponseEntity.ok(
				ResponseObject.builder().status(ResponseStatus.SUCCESS).message("Success").data(plotConfigs).build());
	}

	@Operation(summary = "Get a plot configuration", description = "Retrieves plot configuration details for the given plotId and plotConfigurationId")
	@GetMapping("/{plotId}/config/{plotConfigId}")
	public ResponseEntity<ResponseObject> getPlotConfig(@PathVariable(name = "plotId", required = true) String plotId,
			@PathVariable(name = "plotConfigId", required = true) String plotConfigId) {

		logger.info("getPlotConfig called for plot id {} and config id {} ", plotId, plotConfigId);

		PlotConfig plotConfig = plotConfigService.getPlotConfig(plotConfigId);
		return ResponseEntity.ok(
				ResponseObject.builder().status(ResponseStatus.SUCCESS).message("Success").data(plotConfig).build());
	}

	@Operation(summary = "Create a plot configuration", description = "Creates a plot configuration for a given plotId with the provided properties. A plot may have more than one configuration")
	@PostMapping("/{plotId}/config")
	public ResponseEntity<ResponseObject> addPlotConfig(@PathVariable(name = "plotId", required = true) String plotId,
			@Valid @RequestBody PlotConfig plotConfig) {

		logger.info("addPlotConfig called for plot id {} ", plotId);

		PlotConfig createdPlot = plotConfigService.addPlotConfig(plotId, plotConfig);
		return ResponseEntity.ok(
				ResponseObject.builder().status(ResponseStatus.SUCCESS).message("Success").data(createdPlot).build());
	}

	@Operation(summary = "Update a plot configuration", description = "Updates a plot with the provided properties for the given plotId and plotConfigurationId")
	@PutMapping("/{plotId}/config/{plotConfigId}")
	public ResponseEntity<ResponseObject> updatePlotConfig(
			@PathVariable(name = "plotId", required = true) String plotId,
			@PathVariable(name = "plotConfigId", required = true) String plotConfigId,
			@Valid @RequestBody PlotConfig plot) {

		logger.info("updatePlotConfig called for plot id {} and config id {} ", plotId, plotConfigId);

		PlotConfig updatedPlot = plotConfigService.updatePlotConfig(plotConfigId, plot);

		return ResponseEntity.ok(
				ResponseObject.builder().status(ResponseStatus.SUCCESS).message("Success").data(updatedPlot).build());
	}
}
