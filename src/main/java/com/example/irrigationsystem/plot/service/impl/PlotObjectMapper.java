package com.example.irrigationsystem.plot.service.impl;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.irrigationsystem.plot.dto.Plot;
import com.example.irrigationsystem.plot.dto.PlotConfig;
import com.example.irrigationsystem.plot.model.PlotConfigModel;
import com.example.irrigationsystem.plot.model.PlotModel;

@Mapper(componentModel = "spring")
public interface PlotObjectMapper {

	List<Plot> plotModelsToPlots(List<PlotModel> plotModels);

	@Mapping(target = "configs", ignore = true)
	Plot plotModelToPlot(PlotModel plotModel);

	@Mapping(target = "createdDate", ignore = true)
	@Mapping(target = "modifiedDate", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "configs", ignore = true)
	@Mapping(target = "plotId", ignore = true)
	PlotModel plotToPlotModel(Plot plot);

	@Mapping(target = "createdDate", ignore = true)
	@Mapping(target = "modifiedDate", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "configs", ignore = true)
	@Mapping(target = "plotId", ignore = true)
	void updatePlotToPlotModel(Plot plot, @MappingTarget PlotModel plotModel);

	List<PlotConfig> plotConfigModelsToPlotConfigs(List<PlotConfigModel> plotModels);

	PlotConfig plotConfigModelToPlotConfig(PlotConfigModel plotConfigModel);

	@Mapping(target = "createdDate", ignore = true)
	@Mapping(target = "modifiedDate", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "plot", ignore = true)
	@Mapping(target = "active", ignore = true)
	PlotConfigModel plotConfigToPlotConfigModel(PlotConfig plotConfig);

	@Mapping(target = "createdDate", ignore = true)
	@Mapping(target = "modifiedDate", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "plot", ignore = true)
	void updatePlotConfigToPlotConfigModel(PlotConfig plotConfig, @MappingTarget PlotConfigModel plotConfigModel);
}
