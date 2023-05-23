package com.mye030.world_data_visualizer.service;

import java.util.List;

public interface IndicatorValuesService {

	List<String> getAllIndicatorsNames();

	List<Object[]> getYearsAndValuesByCountryAndIndicatorNames(String countryName, String indicatorName);

}