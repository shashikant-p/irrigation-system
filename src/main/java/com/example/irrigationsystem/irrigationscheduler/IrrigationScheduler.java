package com.example.irrigationsystem.irrigationscheduler;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.irrigationsystem.irrigationscheduler.service.SlotService;
import com.example.irrigationsystem.plot.dto.PlotConfig;

@Component
public class IrrigationScheduler {

	@Value("${irrigation.scheduler.frequency}")
	private Long irrigationSchedulerFrequency;

	@Autowired
	SlotService slotService;

	@Scheduled(fixedRateString = "${irrigation.scheduler.frequency}")
	public void scheduleSensorforIrrigation() {
		LocalTime endTime = LocalTime.now();
		LocalTime startTime = endTime.minusSeconds(irrigationSchedulerFrequency / 1000);
		List<PlotConfig> plotConfigs = slotService.getScheduledPlotConfigs(startTime, endTime);

		for (PlotConfig currConfig : plotConfigs) {
			slotService.addIrrigationSchedule(currConfig);
		}
	}
}
