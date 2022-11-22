package com.example.irrigationsystem.irrigationscheduler.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.irrigationsystem.common.IrrigationStatus;
import com.example.irrigationsystem.irrigationscheduler.model.IrrigationScheduleModel;
import com.example.irrigationsystem.irrigationscheduler.repository.IrrigationScheduleRepository;
import com.example.irrigationsystem.plot.dto.PlotConfig;
import com.example.irrigationsystem.plot.model.PlotConfigModel;
import com.example.irrigationsystem.plot.repository.PlotConfigRepository;
import com.example.irrigationsystem.plot.service.PlotConfigService;
import com.example.irrigationsystem.plot.service.PlotObjectMapper;
import com.example.irrigationsystem.sensor.Sensor;
import com.example.irrigationsystem.sensor.SensorException;

@Service
public class SlotService {

	@Autowired
	PlotObjectMapper plotObjectMapper;

	@Autowired
	PlotConfigService plotConfigService;

	@Autowired
	PlotConfigRepository plotConfigRepository;

	@Autowired
	IrrigationScheduleRepository irrigationScheduleRepository;

	@Autowired
	Sensor sensor;

	public List<PlotConfig> getScheduledPlotConfigs(LocalTime startTime, LocalTime endTime) {
		return plotConfigService.getScheduledPlotConfigs(startTime, endTime);
	}

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

	public void initiateIrrigation(LocalTime startTime, LocalTime endTime) {

		Optional<List<IrrigationScheduleModel>> irrigationSchedules = irrigationScheduleRepository
				.findBetweenStartTimes(startTime, endTime);

		if (irrigationSchedules.isPresent() && !irrigationSchedules.get().isEmpty()) {
			for (IrrigationScheduleModel irrigationSchedule : irrigationSchedules.get()) {
				if (irrigationSchedule.getIrrigationStatus() == IrrigationStatus.SCHEDULED) {
					try {
						sensor.startIrrigation(plotObjectMapper.plotModelToPlot(irrigationSchedule.getPlot()),
								irrigationSchedule.getEndTime(), irrigationSchedule.getWaterQuantity());
						irrigationSchedule.setIrrigationStatus(IrrigationStatus.SUBMITTED);
					} catch (SensorException e) {
						irrigationSchedule.setIrrigationStatus(IrrigationStatus.ERROR);
					}
					irrigationScheduleRepository.save(irrigationSchedule);
				}
			}
		}
	}
}
