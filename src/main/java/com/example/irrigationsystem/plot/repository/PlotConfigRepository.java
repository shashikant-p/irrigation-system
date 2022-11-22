package com.example.irrigationsystem.plot.repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.irrigationsystem.plot.model.PlotConfigModel;

public interface PlotConfigRepository extends JpaRepository<PlotConfigModel, Long> {

	@Query("select pe from PlotConfigModel pe where pe.plot.plotId = ?1")
	Optional<List<PlotConfigModel>> findByPlotId(String plotId);

	@Query("select pe from PlotConfigModel pe where pe.plotConfigId = ?1")
	Optional<List<PlotConfigModel>> findById(String plotConfigId);

	@Query("select pe from PlotConfigModel pe where pe.startTime between ?1 and ?2 and pe.active = true")
	Optional<List<PlotConfigModel>> findBetweenStartTimes(LocalTime beginTime, LocalTime endTime);

}