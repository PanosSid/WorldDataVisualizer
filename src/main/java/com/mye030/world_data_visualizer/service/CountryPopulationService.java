package com.mye030.world_data_visualizer.service;

import org.springframework.stereotype.Service;

@Service
public interface CountryPopulationService {
	
	String findByCountryIdAsJSONString(int countryId);
	
}
