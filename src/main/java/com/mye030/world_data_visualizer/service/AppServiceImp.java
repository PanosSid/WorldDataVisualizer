package com.mye030.world_data_visualizer.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
	
	
	@Override
	public String getDataForScatterChart(List<String> countryNames, List<String> indicatorNames, int aggr, int start, int end) {	
		List<Object[]> yearsAndValues1 = getYearsAndValues(countryNames.get(0), indicatorNames.get(0));
		Map<Number, Number> map1 = DataUtils.convertYearsAndValuesToMap(yearsAndValues1);
		map1 = DataUtils.filter(start, end, map1);
//		map1 = DataUtils.aggregateBy(aggr, map1);
		List<Number> years1 = new ArrayList<Number>((map1.keySet()));
		
		List<Object[]> yearsAndValues2 = getYearsAndValues(countryNames.get(0), indicatorNames.get(1));
		Map<Number, Number> map2 = DataUtils.convertYearsAndValuesToMap(yearsAndValues2);
		map2 = DataUtils.filter(start, end, map2);
//		map2 = DataUtils.aggregateBy(aggr, map2);
		List<Number> years2 = new ArrayList<Number>((map2.keySet()));

		List<Number> commonYears = DataUtils.findCommons(years1, years2);
		
		List<Number> xValues = new ArrayList<Number>();
		List<Number> yValues = new ArrayList<Number>();
		
		for (Number year : commonYears) {
			xValues.add(map1.get(year));
			yValues.add(map2.get(year));
		}

//		System.out.println(commonYears);
//		System.out.println(xValues);
//		System.out.println(yValues);
		return DataUtils.convertListsOfNumsToJSONStr(xValues, yValues);
	}
	
	@Override
	public String getDataForBarChart(List<String> countryNames, List<String> indicatorNames, int aggr, int start, int end) {
		Map<String, HashMap<Number, Number>> data = new LinkedHashMap<>();
		for (int i = 0; i < countryNames.size(); i++) {
			List<Object[]> yearsAndValues = getYearsAndValues(countryNames.get(i), indicatorNames.get(i));
			Map<Number, Number> data2 = DataUtils.convertYearsAndValuesToMap(yearsAndValues);
			Map<Number, Number> data3 = DataUtils.filter(start, end, data2);
			data.put(countryNames.get(i)+i, (HashMap<Number, Number>) data3);	// the "+i" is used to diffrentiate the keys with the same name
		}
		BarChartFormatter bcf = new BarChartFormatter();
		return bcf.getFormattedBarChartData(aggr, data);
	}
	
	@Override
	public String getDataForLineChart(List<String> countryNames, List<String> indicatorNames, int aggr, int start, int end) {
		JSONArray array = new JSONArray();
		for (int i = 0; i < countryNames.size(); i++) {
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
		
//		if (yearsAndValues == null) {
//			throw new RuntimeException("fdasfdsf.");
//		}
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
