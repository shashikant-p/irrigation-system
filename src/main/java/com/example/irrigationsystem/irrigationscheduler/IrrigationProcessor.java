package com.example.irrigationsystem.irrigationscheduler;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.irrigationsystem.irrigationscheduler.service.SlotService;

@Component
public class IrrigationProcessor {

	@Value("${irrigation.processor.frequency}")
	private Long irrigationProcessorFrequency;

	@Autowired
	SlotService slotService;

	@Scheduled(fixedRateString = "${irrigation.processor.frequency}")

	public void scheduleSensorforIrrigation() {
		LocalTime endTime = LocalTime.now();
		LocalTime startTime = endTime.minusSeconds(irrigationProcessorFrequency / 1000);
		slotService.initiateIrrigation(startTime, endTime);
	}
}
