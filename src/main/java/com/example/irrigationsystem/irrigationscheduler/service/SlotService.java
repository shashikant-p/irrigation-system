package com.example.irrigationsystem.irrigationscheduler.service;

import java.time.LocalTime;
import java.util.List;

import com.example.irrigationsystem.plot.dto.PlotConfig;

public interface SlotService {

	List<PlotConfig> getScheduledPlotConfigs(LocalTime startTime, LocalTime endTime);

	void addIrrigationSchedule(PlotConfig plotConfig);

	void initiateIrrigation(LocalTime endTime);

}