package com.example.irrigationsystem.irrigationscheduler;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class IrrigationScheduler {


	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime() {
	}
}
