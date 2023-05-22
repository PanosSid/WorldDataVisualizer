package com.mye030.world_data_visualizer.service;

import java.util.List;
import java.util.Map;

public interface AppService {
	
	List<String> getAllIndicatorNames();
	
	List<String> getAllCountryNames();
	
	Map<String, List<String>> getCountriesAndTheirIndicators();
	
	String getDataForLineChart(List<String> countries, List<String> indicators, int aggr, int minYear, int maxYear);

	String getDataForBarChart(List<String> countryNames, List<String> indicatorNames, int aggr, int minYear, int maxYear);

	String getDataForScatterChart(List<String> countries, List<String> indicators, int aggr, int minYear, int maxYear);
	
}
