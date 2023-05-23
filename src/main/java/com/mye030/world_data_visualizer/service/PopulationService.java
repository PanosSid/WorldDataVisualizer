package com.mye030.world_data_visualizer.service;

import java.util.List;

public interface PopulationService {

	List<String> getAllPopulationsIndicators();

	List<Object[]> getPopulationOfCountry(String countryName, String indicatorName);

}