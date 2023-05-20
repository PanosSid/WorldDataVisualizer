package com.mye030.world_data_visualizer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mye030.world_data_visualizer.repository.CountryMetadataRepository;
import com.mye030.world_data_visualizer.repository.IndicatorMetadataRepository;
import com.mye030.world_data_visualizer.repository.IndicatorValueRepository;

@Service
public class IndicatorValuesService {
	
	@Autowired
	private IndicatorValueRepository indicatorValuesRepo;
	
	@Autowired
	private IndicatorMetadataRepository indicatorRepo;

	@Autowired
	private CountryMetadataRepository countryRepo;
	
	private List<String> indicatorsNames; 
	
	public List<String> getAllIndicatorsNames() {
		if (indicatorsNames == null) {
			indicatorsNames = indicatorRepo.findAllNames();
		}
		return indicatorsNames;
	}
	public List<Object[]> getYearsAndValuesByCountryAndIndicatorNames(String countryName, String indicatorName) {
		int indicatorId = indicatorRepo.findIdByName(indicatorName);
		int countryId = countryRepo.findIdByName(countryName);
		return indicatorValuesRepo.findYearsAndValuesByCountryAndIndicator(countryId, indicatorId);
	}
	
	public List<Object[]> getAVG(String countryName, String indicatorName, int aggrYears) {
		int indicatorId = indicatorRepo.findIdByName(indicatorName);
		int countryId = countryRepo.findIdByName(countryName);
		return indicatorValuesRepo.findYearsAndValuesByCountryAndIndicator(countryId, indicatorId);
	}
}
