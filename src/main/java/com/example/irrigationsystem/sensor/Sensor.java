package com.example.irrigationsystem.sensor;

import java.time.LocalTime;

import com.example.irrigationsystem.plot.dto.Plot;

public interface Sensor {
	void startIrrigation(Plot plot, LocalTime endTime, Integer waterQuantity) throws SensorException;
}
