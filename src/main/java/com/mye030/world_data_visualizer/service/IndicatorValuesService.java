package com.mye030.world_data_visualizer.service;

import java.util.List;
import java.util.Map;

public interface IndicatorValuesService {

	List<String> getAllIndicatorsNames();

	List<Object[]> getYearsAndValuesByCountryAndIndicatorNames(String countryName, String indicatorName);

	Map<String, List<String>> getAllIndicatorsPerCountry();

}