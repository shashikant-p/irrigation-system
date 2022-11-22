package com.example.irrigationsystem.irrigationscheduler.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import com.example.irrigationsystem.common.IrrigationStatus;
import com.example.irrigationsystem.irrigationscheduler.model.IrrigationScheduleModel;
import com.example.irrigationsystem.irrigationscheduler.repository.IrrigationScheduleRepository;
import com.example.irrigationsystem.notifications.Notification;
import com.example.irrigationsystem.notifications.NotificationProvider;
import com.example.irrigationsystem.plot.controller.PlotController;
import com.example.irrigationsystem.plot.service.impl.PlotObjectMapper;
import com.example.irrigationsystem.sensor.Sensor;
import com.example.irrigationsystem.sensor.SensorException;

@Component
public class SensorRetriableService {

	private static final Logger logger = LoggerFactory.getLogger(PlotController.class);

	@Autowired
	@Qualifier("consoleNotifier")
	NotificationProvider notificationProvider;

	@Autowired
	IrrigationScheduleRepository irrigationScheduleRepository;

	@Autowired
	@Qualifier("mockSensor")
	Sensor sensor;

	@Autowired
	PlotObjectMapper plotObjectMapper;

	@Retryable(value = SensorException.class, maxAttempts = 2, backoff = @Backoff(delay = 50))
	public void startIrrigation(IrrigationScheduleModel irrigationSchedule) throws SensorException {

		logger.info("Sending irrigation details to sensor for plot {}", irrigationSchedule.getPlot().getPlotId());

		sensor.startIrrigation(plotObjectMapper.plotModelToPlot(irrigationSchedule.getPlot()),
				irrigationSchedule.getEndTime(), irrigationSchedule.getWaterQuantity());
		irrigationSchedule.setIrrigationStatus(IrrigationStatus.SUBMITTED);
		irrigationScheduleRepository.save(irrigationSchedule);
	}

	@Recover
	public void recover(SensorException e, IrrigationScheduleModel irrigationSchedule) {

		logger.info("Recovery after failure to irrigate due to sensor failure");

		irrigationSchedule.setIrrigationStatus(IrrigationStatus.ERROR);
		irrigationScheduleRepository.save(irrigationSchedule);

		Notification notification = Notification.builder().title("!Important - Irrigation Failure")
				.subject("Failed to Irrigate plot " + irrigationSchedule.getPlot().getPlotId())
				.message("Failure occured for the plot " + irrigationSchedule.getPlot().getPlotId() + " scheduled at "
						+ irrigationSchedule.getStartTime())
				.build();

		notificationProvider.sendNotification(notification);
	}
}
