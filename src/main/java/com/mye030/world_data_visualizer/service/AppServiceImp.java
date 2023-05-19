package com.mye030.world_data_visualizer.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mye030.world_data_visualizer.repository.CountryMetadataRepository;

@Service
public class AppServiceImp implements AppService {

	@Autowired
	private CountryMetadataRepository countryRepo;
	
	@Autowired
	private PopulationsService populationsService;
	
	@Autowired
	private IndicatorValuesService indicatorValuesService; 
	
	public String getDataForBarChart(List<String> countryNames, List<String> indicatorNames) {
		Map<String, HashMap<Number, Number>> data = new HashMap<>();
		for (int i = 0; i < countryNames.size(); i++) {
			List<Object[]> yearsAndValues = getYearsAndValues(countryNames.get(i), indicatorNames.get(i));
			List<Number> years = DataUtils.getNumbersAtIndex(yearsAndValues, 0);
			List<Number> values = DataUtils.getNumbersAtIndex(yearsAndValues, 1);
			data.put(countryNames.get(i)+i, DataUtils.convertListsToMap(years, values));	// the "+i" is used to diffrentiate the keys with the same name
		}
		BarChartFormatter bcf = new BarChartFormatter();
		return bcf.getFormattedBarChartData(data);
	}
	
	
	public String getValuesByCountryAndIndicatorAsJSONStr(List<String> countryNames, List<String> indicatorNames) {
		JSONArray array = new JSONArray();
		array.put(getValuesByCountryAndIndicatorAsJSONStr(countryNames.get(0), indicatorNames.get(0)));
		for (int i = 1; i < countryNames.size(); i++) {
			array.put(getValuesByCountryAndIndicatorAsJSONStr(countryNames.get(i), indicatorNames.get(i)));
			
		}
		return array.toString();
	}
	
	private String getValuesByCountryAndIndicatorAsJSONStr(String countryName, String indicatorName) {
		List<Object[]> yearsAndValues = getYearsAndValues(countryName, indicatorName);
		List<Number> years = DataUtils.getNumbersAtIndex(yearsAndValues, 0);
		List<Number> values = DataUtils.getNumbersAtIndex(yearsAndValues, 1);
		return DataUtils.convertListsOfNumsToJSONStr(years, values);
	}

	private List<Object[]> getYearsAndValues(String countryName, String indicatorName) {
		List<Object[]> yearsAndValues = null;
		if (populationsService.getAllPopulationsIndicators().contains(indicatorName)) {
			yearsAndValues = populationsService.getPopulationOfCountry(countryName, indicatorName);
		} else if (indicatorValuesService.getAllIndicatorsNames().contains(indicatorName)) {
			yearsAndValues = indicatorValuesService.getYearsAndValuesByCountryAndIndicatorNames(countryName, indicatorName);
		}
		return yearsAndValues;
	}
	
	@Override
	public List<String> getAllIndicatorNames() {	
		List<String> list = new ArrayList<String>(); 
		list.addAll(indicatorValuesService.getAllIndicatorsNames());
		list.addAll(populationsService.getAllPopulationsIndicators());
		Collections.sort(list);
		return list;
	}

	@Override
	public List<String> getAllCountryNames() {
		List<String> list = countryRepo.findAllNames(); 
		Collections.sort(list);
		return list;
	}
	
}
