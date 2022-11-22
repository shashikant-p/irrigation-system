package com.example.irrigationsystem.sensor.impl;

import java.time.LocalTime;

import org.springframework.stereotype.Component;

import com.example.irrigationsystem.plot.dto.Plot;
import com.example.irrigationsystem.sensor.Sensor;
import com.example.irrigationsystem.sensor.SensorException;

@Component("mockSensor")
public class MockSensorImpl implements Sensor {

	@Override
	public void startIrrigation(Plot plot, LocalTime endTime, Integer waterQuantity) throws SensorException {
		System.out.println("=== Sensor called ===");
	}
}
