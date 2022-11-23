package com.example.irrigationsystem.irrigationscheduler;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.irrigationsystem.irrigationscheduler.service.SlotService;

@Component
public class IrrigationProcessor {

	@Autowired
	SlotService slotService;

	@Scheduled(fixedRateString = "${irrigation.processor.frequency}")
	public void scheduleSensorforIrrigation() {
		LocalTime scheduledTime = LocalTime.now();
		slotService.initiateIrrigation(scheduledTime);
	}
}
