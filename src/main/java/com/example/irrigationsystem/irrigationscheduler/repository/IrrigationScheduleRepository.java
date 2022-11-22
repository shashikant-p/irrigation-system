package com.example.irrigationsystem.irrigationscheduler.repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.irrigationsystem.common.IrrigationStatus;
import com.example.irrigationsystem.irrigationscheduler.model.IrrigationScheduleModel;

public interface IrrigationScheduleRepository extends JpaRepository<IrrigationScheduleModel, Long> {

	@Query("select ism from IrrigationScheduleModel ism where ism.irrigationStatus = ?1")
	Optional<List<IrrigationScheduleModel>> findbyStatus(IrrigationStatus irrigationStatus);

	@Query("select ism from IrrigationScheduleModel ism where ism.startTime< :schedulerTime and ism.irrigationStatus = :status")
	Optional<List<IrrigationScheduleModel>> findBetweenStartTimes(@Param("schedulerTime") LocalTime schedulerTime,
			@Param("status") IrrigationStatus status);
}