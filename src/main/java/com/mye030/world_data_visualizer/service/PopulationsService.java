package com.mye030.world_data_visualizer.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mye030.world_data_visualizer.repository.CountryMetadataRepository;
import com.mye030.world_data_visualizer.repository.PopulationsRepository;

@Service
public class PopulationsService {

	@Autowired 
	private PopulationsRepository populationsRepo;
	
	@Autowired
	private CountryMetadataRepository countryRepo;
	
	private Map<String, Integer> indicatorAgeMap;
	private List<String> allAgesPopulationIndicators;
			
	public PopulationsService() {
		allAgesPopulationIndicators = Arrays.asList("Total population all ages",
				"Male population all ages", "Female population all ages");
		initPopulationIndicatorsPerAge();
	}
	
	private void initPopulationIndicatorsPerAge(){
		indicatorAgeMap = new HashMap<String, Integer>();
		List<String> prefixes = Arrays.asList("Total", "Male", "Female");
		for (int i = 0; i <= 100; i++ ) {
			for (String prefix : prefixes) {
				String indicatorName = prefix+ " population at age "+ i;
				indicatorAgeMap.put(indicatorName, i);
			}
		}
	}
	
	public List<String> getAllPopulationsIndicators(){
		List<String> populationsIndicators = new ArrayList<String>();
		populationsIndicators.addAll(allAgesPopulationIndicators);
		populationsIndicators.addAll(indicatorAgeMap.keySet());
		return populationsIndicators;
	}
	
	public List<Object[]> getPopulationOfCountry(String countryName, String indicatorName) {
		int countryId = countryRepo.findIdByName(countryName);
		return getPopulationBasedOnIndicator(indicatorName, countryId);
	}

	private List<Object[]> getPopulationBasedOnIndicator(String indicatorName, int countryId) {
		if (allAgesPopulationIndicators.contains(indicatorName)) {
			return getPopulationBasedOnGender(indicatorName, countryId);
		} else  {
			return getPopulationForGenderAndAge(indicatorName, countryId);
		}
	}

	private List<Object[]> getPopulationBasedOnGender(String indicatorName, int countryId) {
		if (indicatorName.startsWith("Male")) {
			return populationsRepo.getPopulationForCountryAndSex(countryId, "M");
		} else if (indicatorName.startsWith("Female")) {
			return populationsRepo.getPopulationForCountryAndSex(countryId, "F");
		} else {
			return populationsRepo.getTotalPopulationForCountry(countryId);
		}
	}
	
	private List<Object[]> getPopulationForGenderAndAge(String indicatorName, int countryId) {	
		if (indicatorName.startsWith("Male")) {
			return populationsRepo.getPopulationForCountryAndAgeAndSex(countryId, indicatorAgeMap.get(indicatorName), "M");
		} else if (indicatorName.startsWith("Female")) {
			return populationsRepo.getPopulationForCountryAndAgeAndSex(countryId, indicatorAgeMap.get(indicatorName), "F");
		} else {
			return populationsRepo.getTotalPopulationForCountryAndAge(countryId, indicatorAgeMap.get(indicatorName));
		}
	}
	
}
