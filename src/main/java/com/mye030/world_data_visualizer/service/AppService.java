package com.mye030.world_data_visualizer.service;

import java.util.List;

public interface AppService {
	
	List<String> getAllIndicatorNames();
	
	List<String> getAllCountryNames();
	
	String getDataForLineChart(List<String> countries, List<String> indicators);
	
	String getDataForBarChart(List<String> countryNames, List<String> indicatorNames);

	String getDataForScatterChart(List<String> countryNames, List<String> indicatorNames);
	
}
