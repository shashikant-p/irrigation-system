package com.example.irrigationsystem.plot.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.irrigationsystem.common.ResourceNotFoundException;
import com.example.irrigationsystem.plot.dto.Plot;
import com.example.irrigationsystem.plot.model.PlotModel;
import com.example.irrigationsystem.plot.repository.PlotRepository;
import com.example.irrigationsystem.plot.service.PlotService;

@Service
public class PlotServiceImpl implements PlotService {

	@Autowired
	PlotRepository plotRepository;

	@Autowired
	PlotObjectMapper plotObjectMapper;

	@Override
	public Plot getPlot(String plotId) throws ResourceNotFoundException {
		Optional<PlotModel> value = plotRepository.findByPlotId(plotId);

		if (value.isPresent()) {
			Plot plot = plotObjectMapper.plotModelToPlot(value.get());
			plot.setConfigs(plotObjectMapper.plotConfigModelsToPlotConfigs(value.get().getConfigs()));
			return plot;
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public Plot addPlot(Plot plot) {
		PlotModel plotModel = plotObjectMapper.plotToPlotModel(plot);
		plotModel.setPlotId(UUID.randomUUID().toString());
		plotModel = plotRepository.save(plotModel);
		return plotObjectMapper.plotModelToPlot(plotModel);
	}

	@Override
	public Plot updatePlot(String plotId, Plot plot) throws ResourceNotFoundException {
		Optional<PlotModel> plotModel = plotRepository.findByPlotId(plotId);

		if (plotModel.isPresent()) {
			plotObjectMapper.updatePlotToPlotModel(plot, plotModel.get());
			PlotModel updatedPlotModel = plotRepository.save(plotObjectMapper.plotToPlotModel(plot));
			return plotObjectMapper.plotModelToPlot(updatedPlotModel);
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public List<Plot> getPlots() {
		List<PlotModel> value = plotRepository.findAll();
		return plotObjectMapper.plotModelsToPlots(value);
	}
}
