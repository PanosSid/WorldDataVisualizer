package com.mye030.world_data_visualizer.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mye030.world_data_visualizer.repository.CountryMetadataRepository;
import com.mye030.world_data_visualizer.repository.IndicatorMetadataRepository;
import com.mye030.world_data_visualizer.repository.IndicatorValueRepository;

@Service
public class IndicatorValuesServiceImp implements IndicatorValuesService {
	
	@Autowired
	private IndicatorValueRepository indicatorValuesRepo;
	
	@Autowired
	private IndicatorMetadataRepository indicatorRepo;

	@Autowired
	private CountryMetadataRepository countryRepo;
	
	private List<String> indicatorsNames;
	
	
	@Override
	public Map<String, List<String>> getAllIndicatorsPerCountry() {
		List<Object[]> rows = indicatorValuesRepo.findAllIndicatorsPerCountry();
		return convertRowsToCountryIndicatorsMap(rows);
	}

	private Map<String, List<String>> convertRowsToCountryIndicatorsMap(List<Object[]> rows) {
		Map<String, List<String>> map = new LinkedHashMap<>();
		for (Object[] row : rows) {
			String country = (String) row[0];
			String indicator = (String) row[1];
			if (!map.containsKey(country)) {
				map.put(country, new ArrayList<String>());
			}
			List<String> indicatorsOfCountry = map.get(country);
			indicatorsOfCountry.add(indicator);
		}
		return map;
	}
	
	@Override
	public List<String> getAllIndicatorsNames() {
		if (indicatorsNames == null) {
			indicatorsNames = indicatorRepo.findAllNames();
		}
		return indicatorsNames;
	}
	
	@Override
	public List<Object[]> getYearsAndValuesByCountryAndIndicatorNames(String countryName, String indicatorName) {
		int indicatorId = indicatorRepo.findIdByName(indicatorName);
		int countryId = countryRepo.findIdByName(countryName);
		return indicatorValuesRepo.findYearsAndValuesByCountryAndIndicator(countryId, indicatorId);
	}
	
}
