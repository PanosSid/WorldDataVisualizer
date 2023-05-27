package com.mye030.world_data_visualizer.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONObject;

public class ChartDataSets {
	private List<ChartData> dataList;
	
	public ChartDataSets() {
		dataList = new ArrayList<ChartData>();
	}
	
	public void addChartData(ChartData chartData) {
		dataList.add(chartData);
	}
	
	public void aggregateBy(int aggr) {
		if (aggr <= 0) {
			return;
		}
		for (ChartData cd : dataList) {
			cd.aggregateBy(aggr);
		}
	}
	
	public void filter(int minYear, int maxYear) {
		if (minYear <= 0 && maxYear <= 0) {
			return;
		}
		for (ChartData cd : dataList) {
			cd.filter(minYear, maxYear);
		}
	}
	
	public void subsetDataForCommonYears() {
		List<Number> commonYears = findCommonYears();
		for (ChartData cd : dataList) {
			cd.retainCommonsYears(commonYears);
		}
	}
	
	public List<Number> findCommonYears() {
		List<Number> commonYears = dataList.get(0).getYears();
		for (int i = 1; i < dataList.size(); i++) {
			commonYears.retainAll(dataList.get(i).getYears());
		}
		sortListOfNums(commonYears);
		return commonYears;
	}
	
	public String convertToJSONStr() {
		JSONArray array = new JSONArray();	
		for (ChartData cd : dataList) {
			array.put(cd.convertToJSONStr());
		}		
		return array.toString();
	}
	
	public String convertToBarChartJSONStr() {
		List<Number> commonYears = findCommonYears();
		JSONArray array = new JSONArray();
		for (Number year : commonYears) {
			JSONObject obj = new JSONObject();
			obj.put("group", year);
			obj.put("bars", getBarsFromChartData((int) year));
			array.put(obj);
		}
		return array.toString();
	}
	
	private JSONArray getBarsFromChartData(int year) {
		JSONArray barsArray = new JSONArray();
		int i = 0;
		for (ChartData cd : dataList) {
			barsArray.put(cd.convertToBarJSONObj(year, i));
			i++;
		}
		return barsArray;
		
	}
	
	public void sortListOfNums(List<Number> commonYears) {
		Collections.sort(commonYears, new Comparator<Number>() {
            @Override
            public int compare(Number n1, Number n2) {
                return n1.intValue() - n2.intValue();
            }
        });
	}

	public String convertToScatterChartJSONStr(List<String> countryNames, List<String> indicatorNames) {
		SortedSet<String> countries = new TreeSet<String>(countryNames);
		SortedSet<String> indicators = new TreeSet<String>(indicatorNames);
		Map<String, List<ChartData>> map = new LinkedHashMap<>();
		for (String country: countries) {
			List<ChartData> list = new ArrayList<ChartData>();
			for (String indicator : indicators) {
				list.add(getChartDataByCountryAndIndicator(country, indicator));
			}
			map.put(country, list);				
		}

		JSONArray outerArray = new JSONArray();	
		for (String countryName : map.keySet()) {
			List<Number> xValues = map.get(countryName).get(0).getValues();
			List<Number> yValues = map.get(countryName).get(1).getValues();
			JSONArray array = new JSONArray();
			for (int i = 0; i < xValues.size(); i++) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("x", xValues.get(i));
				jsonObj.put("y", yValues.get(i));
				array.put(jsonObj);
			}
			outerArray.put(array);
		}
		return outerArray.toString();
	}
	
	private ChartData getChartDataByCountryAndIndicator(String country, String indicator) {
		for (ChartData cd : dataList) {
			if (cd.getCountryName().equals(country) && cd.getIndicatorName().equals(indicator)) {
				return cd;
			}
		}
		throw new RuntimeException("ChartDataSets doesnt contain data for country: "+ country +" indicator: "+indicator);
	}

	public String convertListsOfNumsToJSONStr(List<Number> xValues, List<Number> yValues) {
		JSONArray array = new JSONArray();
		for (int i = 0; i < xValues.size(); i++) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("x", xValues.get(i));
			jsonObj.put("y", yValues.get(i));
			array.put(jsonObj);
		}
		return array.toString();
	}
	
}
