package com.example.irrigationsystem.plot.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.irrigationsystem.common.ResourceNotFoundException;
import com.example.irrigationsystem.plot.dto.PlotConfig;
import com.example.irrigationsystem.plot.model.PlotConfigModel;
import com.example.irrigationsystem.plot.model.PlotModel;
import com.example.irrigationsystem.plot.repository.PlotConfigRepository;
import com.example.irrigationsystem.plot.repository.PlotRepository;

@Service
public class PlotConfigService {
	
	@Autowired
	PlotConfigRepository plotConfigRepository;
	
	@Autowired
	PlotRepository plotRepository;
	
	@Autowired
	PlotObjectMapper plotObjectMapper;
	
	public List<PlotConfig> getPlotConfigs(String plotId) {
		Optional<List<PlotConfigModel>> value = plotConfigRepository.findByPlotId(plotId);

		if (value.isPresent()) {
			return plotObjectMapper.plotConfigModelsToPlotConfigs(value.get());
		} else {
			throw new ResourceNotFoundException();
		}
	}
	
	public PlotConfig getPlotConfig(String plotConfigId) {
		Optional<List<PlotConfigModel>> value = plotConfigRepository.findById(plotConfigId);

		if (value.isPresent()) {
			return plotObjectMapper.plotConfigModelToPlotConfig(value.get().get(0));
		} else {
			throw new ResourceNotFoundException();
		}
	}
	
	public PlotConfig addPlotConfig(String plotId, PlotConfig plotConfig) {
		Optional<PlotModel> value = plotRepository.findByPlotId(plotId);

		if (value.isPresent()) {
			PlotModel plot = value.get();
			PlotConfigModel plotConfigModel = plotObjectMapper.plotConfigToPlotConfigModel(plotConfig);
			plotConfigModel.setPlotConfigId(UUID.randomUUID().toString());
			plot.getConfigs().add(plotConfigModel);
			
			System.out.println(plot.getConfigs());
			System.out.println(plotConfigModel);
			
			plotConfigModel.setPlot(plot);
			plotConfigModel = plotConfigRepository.save(plotConfigModel);
			return plotObjectMapper.plotConfigModelToPlotConfig(plotConfigModel);
		} else {
			throw new ResourceNotFoundException();
		}
	}
	
	public PlotConfig updatePlotConfig(String plotConfigId, PlotConfig plot) {
		Optional<List<PlotConfigModel>> plotModel = plotConfigRepository.findById(plotConfigId);

		if (plotModel.isPresent()) {
			PlotConfigModel plotConfigModel = plotModel.get().get(0);
			plotObjectMapper.updatePlotConfigToPlotConfigModel(plot, plotConfigModel);
			plotConfigModel = plotConfigRepository.save(plotConfigModel);
			return plotObjectMapper.plotConfigModelToPlotConfig(plotConfigModel);
		} else {
			throw new ResourceNotFoundException();
		}
	}
}
