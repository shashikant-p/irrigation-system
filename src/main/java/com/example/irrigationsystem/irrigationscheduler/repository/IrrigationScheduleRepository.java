package com.example.irrigationsystem.irrigationscheduler.repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.irrigationsystem.common.IrrigationStatus;
import com.example.irrigationsystem.irrigationscheduler.model.IrrigationScheduleModel;

public interface IrrigationScheduleRepository extends JpaRepository<IrrigationScheduleModel, Long> {

	@Query("select ism from IrrigationScheduleModel ism where ism.irrigationStatus = ?1")
	Optional<List<IrrigationScheduleModel>> findbyStatus(IrrigationStatus irrigationStatus);

	@Query("select ism from IrrigationScheduleModel ism where ism.startTime between ?1 and ?2")
	Optional<List<IrrigationScheduleModel>> findBetweenStartTimes(LocalTime beginTime, LocalTime endTime);
}