package com.example.irrigationsystem.sensor;

import java.time.LocalTime;

import org.springframework.stereotype.Component;

import com.example.irrigationsystem.plot.dto.Plot;

@Component
public interface Sensor {
	void startIrrigation(Plot plot, LocalTime endTime, Integer waterQuantity) throws SensorException;
}
