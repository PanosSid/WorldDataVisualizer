package com.mye030.world_data_visualizer.service;

import java.util.List;

public interface AppService {
	
	List<String> getAllIndicatorNames();
	
	List<String> getAllCountryNames();
	
	String getValuesByCountryAndIndicatorAsJSONStr(List<String> countries, List<String> indicators);
	
	String getDataForBarChart(List<String> countryNames, List<String> indicatorNames);
	
}
