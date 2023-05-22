package com.mye030.world_data_visualizer.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mye030.world_data_visualizer.model.ChartData;
import com.mye030.world_data_visualizer.model.ChartDataSets;
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
	
}
