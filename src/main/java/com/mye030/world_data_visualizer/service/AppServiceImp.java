package com.mye030.world_data_visualizer.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mye030.world_data_visualizer.repository.CountryMetadataRepository;
import com.mye030.world_data_visualizer.repository.IndicatorMetadataRepository;
import com.mye030.world_data_visualizer.repository.IndicatorValueRepository;

@Service
public class AppServiceImp implements AppService {

	@Autowired
	private IndicatorMetadataRepository indicatorRepo;

	@Autowired
	private CountryMetadataRepository countryRepo;

	@Autowired
	private IndicatorValueRepository indicatorValuesRepo;

	public String getValuesByCountryAndIndicatorAsJSONStr(String countryName, String indicatorName) {
		List<Object[]> yearsAndValues = getYearsAndValuesByCountryAndIndicatorNames(countryName, indicatorName);
		List<Number> years = getNumbersAtIndex(yearsAndValues, 0);
		List<Number> values = getNumbersAtIndex(yearsAndValues, 1);
		return convertListsOfNumsToJSONStr(years, values);
	}

	private List<Object[]> getYearsAndValuesByCountryAndIndicatorNames(String countryName, String indicatorName) {
		int indicatorId = indicatorRepo.findIdByName(indicatorName);
		int countryId = countryRepo.findIdByName(countryName);
		List<Object[]> yearsAndValues = indicatorValuesRepo.findYearsAndValuesByCountryAndIndicator(countryId,
				indicatorId);
		return yearsAndValues;
	}

	private List<Number> getNumbersAtIndex(List<Object[]> list, int index) {
		List<Number> targetList = new ArrayList<Number>();
		for (Object[] arr : list) {
			targetList.add((Number) arr[index]);
		}
		return targetList;
	}

	private String convertListsOfNumsToJSONStr(List<Number> years, List<Number> values) {
		JSONArray array = new JSONArray();
		for (int i = 0; i < years.size(); i++) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("x", years.get(i));
			jsonObj.put("y", values.get(i));
			array.put(jsonObj);
		}
		return array.toString();

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
