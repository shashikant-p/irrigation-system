package com.example.irrigationsystem.irrigationscheduler.service.impl;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.irrigationsystem.common.IrrigationStatus;
import com.example.irrigationsystem.irrigationscheduler.model.IrrigationScheduleModel;
import com.example.irrigationsystem.irrigationscheduler.repository.IrrigationScheduleRepository;
import com.example.irrigationsystem.irrigationscheduler.service.SlotService;
import com.example.irrigationsystem.plot.controller.PlotController;
import com.example.irrigationsystem.plot.dto.PlotConfig;
import com.example.irrigationsystem.plot.model.PlotConfigModel;
import com.example.irrigationsystem.plot.repository.PlotConfigRepository;
import com.example.irrigationsystem.plot.service.PlotConfigService;
import com.example.irrigationsystem.sensor.SensorException;

@Service
public class SlotServiceImpl implements SlotService {

	private static final Logger logger = LoggerFactory.getLogger(PlotController.class);

	@Autowired
	PlotConfigService plotConfigService;

	@Autowired
	PlotConfigRepository plotConfigRepository;

	@Autowired
	IrrigationScheduleRepository irrigationScheduleRepository;

	@Autowired
	SensorRetriableService sensorRetriableService;

	@Override
	public List<PlotConfig> getScheduledPlotConfigs(LocalTime startTime, LocalTime endTime) {
		return plotConfigService.getScheduledPlotConfigs(startTime, endTime);
	}

	@Override
	public void addIrrigationSchedule(PlotConfig plotConfig) {

		Optional<List<PlotConfigModel>> value = plotConfigRepository.findById(plotConfig.getPlotConfigId());

		if (value.isPresent() && !value.get().isEmpty()) {
			IrrigationScheduleModel irrigationScheduleModel = IrrigationScheduleModel.builder()
					.startTime(plotConfig.getStartTime())
					.endTime(plotConfig.getStartTime().plusMinutes(plotConfig.getDuration()))
					.waterQuantity(plotConfig.getWaterQuantity()).irrigationStatus(IrrigationStatus.SCHEDULED).build();

			irrigationScheduleModel.setPlotConfig(value.get().get(0));
			irrigationScheduleModel.setPlot(value.get().get(0).getPlot());
			irrigationScheduleRepository.save(irrigationScheduleModel);

		}
	}

	@Override
	public void initiateIrrigation(LocalTime endTime) {

		Optional<List<IrrigationScheduleModel>> irrigationSchedules = irrigationScheduleRepository
				.findBetweenStartTimes(endTime, IrrigationStatus.SCHEDULED);

		if (irrigationSchedules.isPresent() && !irrigationSchedules.get().isEmpty()) {

			for (IrrigationScheduleModel irrigationSchedule : irrigationSchedules.get()) {
				if (irrigationSchedule.getIrrigationStatus() == IrrigationStatus.SCHEDULED) {
					irrigationSchedule.setIrrigationStatus(IrrigationStatus.PROCESSING);
					irrigationScheduleRepository.save(irrigationSchedule);

					try {
						sensorRetriableService.startIrrigation(irrigationSchedule);
					} catch (SensorException e) {
						logger.error("Sensor error occured", e);
						;
					}
				}
			}
		}
	}
}
