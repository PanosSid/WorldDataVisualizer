package com.mye030.world_data_visualizer.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AppServiceImp implements AppService {

	@Autowired
	private PopulationService populationsService;
	
	@Autowired
	private IndicatorValuesService indicatorValuesService;

	private Map<String, List<String>> countryIndicatorsMap;
	
	@Override
	public String getDataForScatterChart(List<String> countryNames, List<String> indicatorNames, int aggr, int start, int end) {	
		ChartDataSets datasets = getChartDataSets(countryNames, indicatorNames);
		datasets.filter(start, end);
		datasets.subsetDataForCommonYears();
		datasets.aggregateBy(aggr);
		return datasets.convertToScatterChartJSONStr();
	}

	@Override
	public String getDataForBarChart(List<String> countryNames, List<String> indicatorNames, int aggr, int start, int end) {
		ChartDataSets datasets = getChartDataSets(countryNames, indicatorNames);
		datasets.filter(start, end);
		datasets.aggregateBy(aggr);
		return datasets.convertToBarChartJSONStr();
	}
	
	@Override
	public String getDataForLineChart(List<String> countryNames, List<String> indicatorNames, int aggr, int start, int end) {
		ChartDataSets datasets = getChartDataSets(countryNames, indicatorNames);
		datasets.filter(start, end);
		datasets.aggregateBy(aggr);
		return datasets.convertToJSONStr();
	}
	
	private ChartDataSets getChartDataSets(List<String> countryNames, List<String> indicatorNames) {
		ChartDataSets datasets = new ChartDataSets();
		for (int i = 0; i < countryNames.size(); i++) {
			ChartData chartData = getChartDataFromDb(countryNames.get(i), indicatorNames.get(i));
			datasets.addChartData(chartData);
		}
		return datasets;
	}
	
	private ChartData getChartDataFromDb(String countryName, String indicatorName) {
		List<Object[]> yearsAndValues = null;
		if (populationsService.getAllPopulationsIndicators().contains(indicatorName)) {
			yearsAndValues = populationsService.getPopulationOfCountry(countryName, indicatorName);			
		} else if (indicatorValuesService.getAllIndicatorsNames().contains(indicatorName)) {
			yearsAndValues = indicatorValuesService.getYearsAndValuesByCountryAndIndicatorNames(countryName, indicatorName);
		}
		
		if (yearsAndValues == null || yearsAndValues.isEmpty()) {
			throw new DataNotFoundException(countryName, indicatorName);
		}
		return new ChartData(countryName, indicatorName, yearsAndValues);
	}

	@Override
	public Map<String, List<String>> getCountriesAndTheirIndicators() {	
		if (countryIndicatorsMap != null) {
			return countryIndicatorsMap;
		}
		countryIndicatorsMap = indicatorValuesService.getAllIndicatorsPerCountry();
		// every country has data in populations table so add all population indicators to each one of them 
		List<String> populationIndicators = populationsService.getAllPopulationsIndicators();
		for (String country : countryIndicatorsMap.keySet()) {
			countryIndicatorsMap.get(country).addAll(populationIndicators);
		}		
		return countryIndicatorsMap;
	}
	
}
