package com.example.irrigationsystem.plot.service;

import java.util.List;

import com.example.irrigationsystem.common.ResourceNotFoundException;
import com.example.irrigationsystem.plot.dto.Plot;

public interface PlotService {

	Plot getPlot(String plotId) throws ResourceNotFoundException;

	Plot addPlot(Plot plot);

	Plot updatePlot(String plotId, Plot plot) throws ResourceNotFoundException;

	List<Plot> getPlots();

}