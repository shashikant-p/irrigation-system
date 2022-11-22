package com.example.irrigationsystem.plot.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.example.irrigationsystem.common.ResponseObject;
import com.example.irrigationsystem.common.ResponseStatus;
import com.example.irrigationsystem.plot.dto.Plot;
import com.example.irrigationsystem.plot.service.PlotService;

@ExtendWith(MockitoExtension.class)
public class PlotControllerTest {

	@InjectMocks
	PlotController plotController;

	@Mock
	PlotService plotService;

	static Plot testPlot;
	
	static List<Plot> plots;
	
	@BeforeAll
	static void setup() {
		testPlot = Plot.builder()
				.address("Test Address").area("40")
				.city("Test City").plotId("1234")
				.province("Test Province").build();
		plots = new ArrayList<Plot>();
		plots.add(testPlot);
	}
	
	@Test
	public void testFindAll() {

		when(plotService.getPlots()).thenReturn(plots);
		
		ResponseEntity<ResponseObject> response = plotController.getPlots();

		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody().getStatus()).isEqualTo(ResponseStatus.SUCCESS);
		assertThat(((List) response.getBody().getData()).size()).isEqualTo(1);
		assertThat(((Plot) ((List) response.getBody().getData()).get(0)).getPlotId()).isEqualTo("1234");
	}
	
	@Test
	public void testFindByPlotId() {

		when(plotService.getPlot("1234")).thenReturn(testPlot);
		
		ResponseEntity<ResponseObject> response = plotController.getPlot("1234");

		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody().getStatus()).isEqualTo(ResponseStatus.SUCCESS);
		assertThat(((Plot) response.getBody().getData())).isNotNull();
		assertThat(((Plot) response.getBody().getData()).getPlotId()).isEqualTo("1234");
	}
}
