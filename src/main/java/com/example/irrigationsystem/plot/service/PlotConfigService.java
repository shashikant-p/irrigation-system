package com.example.irrigationsystem.plot.service;

import java.time.LocalTime;
import java.util.List;

import com.example.irrigationsystem.plot.dto.PlotConfig;

public interface PlotConfigService {

	List<PlotConfig> getPlotConfigs(String plotId);

	PlotConfig getPlotConfig(String plotConfigId);

	PlotConfig addPlotConfig(String plotId, PlotConfig plotConfig);

	PlotConfig updatePlotConfig(String plotConfigId, PlotConfig plot);

	List<PlotConfig> getScheduledPlotConfigs(LocalTime startTime, LocalTime endTime);

}