package com.mye030.world_data_visualizer.service;

import java.util.List;

public interface AppService {
	
	String getValuesByCountryAndIndicatorAsJSONStr(String countryName, String indicatorName);

	List<String> getAllIndicatorNames();

	List<String> getAllCountryNames();

	String getTotalPopulationForCountry(String string);
	
}
