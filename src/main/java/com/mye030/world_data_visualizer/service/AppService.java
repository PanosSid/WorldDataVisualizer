package com.mye030.world_data_visualizer.service;

import java.util.List;

public interface AppService {
	
	List<String> getAllIndicatorNames();
	
	List<String> getAllCountryNames();
	
	String getDataForLineChart(List<String> countries, List<String> indicators, int i, int j, int k);

	String getDataForBarChart(List<String> countryNames, List<String> indicatorNames, int aggr, int start, int end);

	String getDataForScatterChart(List<String> countries, List<String> indicators, int i, int j, int k);
	
}
