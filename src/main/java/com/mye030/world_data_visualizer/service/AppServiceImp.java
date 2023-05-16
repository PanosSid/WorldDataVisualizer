package com.mye030.world_data_visualizer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mye030.world_data_visualizer.repository.CountryMetadataRepository;
import com.mye030.world_data_visualizer.repository.IndicatorMetadataRepository;
import com.mye030.world_data_visualizer.repository.IndicatorValueRepository;
import com.mye030.world_data_visualizer.repository.PopulationsRepository;

@Service
public class AppServiceImp implements AppService {

	@Autowired
	private IndicatorMetadataRepository indicatorRepo;

	@Autowired
	private CountryMetadataRepository countryRepo;
	
	@Autowired
	private PopulationsRepository populationsRepo; 

	@Autowired
	private IndicatorValueRepository indicatorValuesRepo;

	public String getTotalPopulationForCountry(String countryName) {
		int countryId = countryRepo.findIdByName(countryName);
		List<Object[]> yearsAndValues = populationsRepo.getTotalPopulationForCountry(countryId);
		List<Number> years = DataUtils.getNumbersAtIndex(yearsAndValues, 0);
		List<Number> values = DataUtils.getNumbersAtIndex(yearsAndValues, 1);
		return DataUtils.convertListsOfNumsToJSONStr(years, values);
	}
	
	public String getValuesByCountryAndIndicatorAsJSONStr(String countryName, String indicatorName) {
		List<Object[]> yearsAndValues = getYearsAndValuesByCountryAndIndicatorNames(countryName, indicatorName);
		List<Number> years = DataUtils.getNumbersAtIndex(yearsAndValues, 0);
		List<Number> values = DataUtils.getNumbersAtIndex(yearsAndValues, 1);
		return DataUtils.convertListsOfNumsToJSONStr(years, values);
	}

	private List<Object[]> getYearsAndValuesByCountryAndIndicatorNames(String countryName, String indicatorName) {
		int indicatorId = indicatorRepo.findIdByName(indicatorName);
		int countryId = countryRepo.findIdByName(countryName);
		List<Object[]> yearsAndValues = indicatorValuesRepo.findYearsAndValuesByCountryAndIndicator(countryId,
				indicatorId);
		return yearsAndValues;
	}


	@Override
	public List<String> getAllIndicatorNames() {
		return indicatorRepo.findAllNames();
	}

	@Override
	public List<String> getAllCountryNames() {
		return countryRepo.findAllNames();
	}
	
	
}
