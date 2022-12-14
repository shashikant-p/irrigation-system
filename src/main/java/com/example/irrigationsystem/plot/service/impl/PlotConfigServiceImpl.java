package com.example.irrigationsystem.plot.service.impl;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.irrigationsystem.common.ResourceNotFoundException;
import com.example.irrigationsystem.plot.dto.PlotConfig;
import com.example.irrigationsystem.plot.model.PlotConfigModel;
import com.example.irrigationsystem.plot.model.PlotModel;
import com.example.irrigationsystem.plot.repository.PlotConfigRepository;
import com.example.irrigationsystem.plot.repository.PlotRepository;
import com.example.irrigationsystem.plot.service.PlotConfigService;

@Service
public class PlotConfigServiceImpl implements PlotConfigService {

	@Autowired
	PlotConfigRepository plotConfigRepository;

	@Autowired
	PlotRepository plotRepository;

	@Autowired
	PlotObjectMapper plotObjectMapper;

	@Override
	public List<PlotConfig> getPlotConfigs(String plotId) {
		Optional<List<PlotConfigModel>> value = plotConfigRepository.findByPlotId(plotId);

		if (value.isPresent()) {
			return plotObjectMapper.plotConfigModelsToPlotConfigs(value.get());
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public PlotConfig getPlotConfig(String plotConfigId) {
		Optional<List<PlotConfigModel>> value = plotConfigRepository.findById(plotConfigId);

		if (value.isPresent() && !value.get().isEmpty()) {
			return plotObjectMapper.plotConfigModelToPlotConfig(value.get().get(0));
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	@Transactional
	public PlotConfig addPlotConfig(String plotId, PlotConfig plotConfig) {
		Optional<PlotModel> value = plotRepository.findByPlotId(plotId);

		if (value.isPresent()) {
			PlotModel plot = value.get();
			PlotConfigModel plotConfigModel = plotObjectMapper.plotConfigToPlotConfigModel(plotConfig);
			plotConfigModel.setPlotConfigId(UUID.randomUUID().toString());
			plot.getConfigs().add(plotConfigModel);

			plotConfigModel.setPlot(plot);
			plotConfigModel = plotConfigRepository.save(plotConfigModel);
			return plotObjectMapper.plotConfigModelToPlotConfig(plotConfigModel);
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	@Transactional
	public PlotConfig updatePlotConfig(String plotConfigId, PlotConfig plot) {
		Optional<List<PlotConfigModel>> plotModel = plotConfigRepository.findById(plotConfigId);

		if (plotModel.isPresent() && !plotModel.get().isEmpty()) {
			PlotConfigModel plotConfigModel = plotModel.get().get(0);
			plotObjectMapper.updatePlotConfigToPlotConfigModel(plot, plotConfigModel);
			plotConfigModel = plotConfigRepository.save(plotConfigModel);
			return plotObjectMapper.plotConfigModelToPlotConfig(plotConfigModel);
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public List<PlotConfig> getScheduledPlotConfigs(LocalTime startTime, LocalTime endTime) {
		Optional<List<PlotConfigModel>> plotConfigs = plotConfigRepository.findBetweenStartTimes(startTime, endTime);

		if (plotConfigs.isPresent()) {
			return plotObjectMapper.plotConfigModelsToPlotConfigs(plotConfigs.get());
		} else {
			return Collections.emptyList();
		}
	}
}
