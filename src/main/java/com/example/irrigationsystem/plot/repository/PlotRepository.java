package com.example.irrigationsystem.plot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.irrigationsystem.plot.model.PlotModel;

public interface PlotRepository extends JpaRepository<PlotModel, Long> {

	@Query("select pe from PlotModel pe where pe.plotId = ?1")
	Optional<PlotModel> findByPlotId(String plotId);

	@Query("select pe from PlotModel pe")
	List<PlotModel> findAll();
}