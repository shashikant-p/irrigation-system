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

@CrossOrigin
@RestController
@RequestMapping("/api/v1/plot")
public class PlotController {

	private static final Logger logger = LoggerFactory.getLogger(PlotController.class);

	@Autowired
	PlotService plotService;

	@Autowired
	PlotConfigService plotConfigService;
	
	@GetMapping
	public ResponseEntity<ResponseObject> getPlots() {
		List<Plot> plots = plotService.getPlots();
		return ResponseEntity.ok(ResponseObject.builder().status(ResponseStatus.SUCCESS).message("Success").data(plots).build());
	}

	@GetMapping("/{plotId}")
	public ResponseEntity<ResponseObject> getPlot(@PathVariable(name = "plotId", required = true) String plotId) {
		Plot plot = plotService.getPlot(plotId);
		return ResponseEntity.ok(ResponseObject.builder().status(ResponseStatus.SUCCESS).message("Success").data(plot).build());
	}

	@PostMapping
	public ResponseEntity<ResponseObject> addPlot(@Valid @RequestBody Plot plot) {
		Plot createdPlot = plotService.addPlot(plot);
		return ResponseEntity.ok(ResponseObject.builder().status(ResponseStatus.SUCCESS).message("Success").data(createdPlot).build());
	}

	@PutMapping("/{plotId}")
	public ResponseEntity<ResponseObject> updatePlot(@PathVariable(name = "plotId", required = true) String plotId,
			@Valid @RequestBody Plot plot) {
		Plot updatedPlot = plotService.updatePlot(plotId, plot);
		return ResponseEntity.ok(ResponseObject.builder().status(ResponseStatus.SUCCESS).message("Success").data(updatedPlot).build());
	}

	
	@GetMapping("/{plotId}/config")
	public ResponseEntity<ResponseObject> getPlotConfig(@PathVariable(name = "plotId", required = true) String plotId) {
		List<PlotConfig> plotConfigs = plotConfigService.getPlotConfigs(plotId);
		return ResponseEntity.ok(ResponseObject.builder().status(ResponseStatus.SUCCESS).message("Success").data(plotConfigs).build());
	}
	
	
	@GetMapping("/{plotId}/config/{plotConfigId}")
	public ResponseEntity<ResponseObject> getPlotConfig(@PathVariable(name = "plotId", required = true) String plotId,
			@PathVariable(name = "plotConfigId", required = true) String plotConfigId) {
		PlotConfig plotConfig = plotConfigService.getPlotConfig(plotConfigId);
		return ResponseEntity.ok(ResponseObject.builder().status(ResponseStatus.SUCCESS).message("Success").data(plotConfig).build());
	}

	@PostMapping("/{plotId}/config")
	public ResponseEntity<ResponseObject> addPlotConfig(@PathVariable(name = "plotId", required = true) String plotId, 
			@Valid @RequestBody PlotConfig plotConfig) {
		PlotConfig createdPlot = plotConfigService.addPlotConfig(plotId, plotConfig);
		return ResponseEntity.ok(ResponseObject.builder().status(ResponseStatus.SUCCESS).message("Success").data(createdPlot).build());
	}

	@PutMapping("/{plotId}/config/{plotConfigId}")
	public ResponseEntity<ResponseObject> updatePlotConfig(@PathVariable(name = "plotId", required = true) String plotId,
			@PathVariable(name = "plotConfigId", required = true) String plotConfigId,
			@Valid @RequestBody PlotConfig plot) {
		PlotConfig updatedPlot = plotConfigService.updatePlotConfig(plotConfigId, plot);
		return ResponseEntity.ok(ResponseObject.builder().status(ResponseStatus.SUCCESS).message("Success").data(updatedPlot).build());
	}
}
