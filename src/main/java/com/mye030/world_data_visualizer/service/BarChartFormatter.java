package com.mye030.world_data_visualizer.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;

public class BarChartFormatter {
	
	public String getFormattedBarChartData(int aggr, Map<String, HashMap<Number, Number>> data) {
		DataUtils.subsetDataForCommonYears(data);
		DataUtils.aggregateDataBy(aggr, data);
		List<Number> aggrCommonYears = getAggregatedYears(data);
		JSONArray array = new JSONArray();
		for (Number year : aggrCommonYears) {
			JSONObject obj = new JSONObject();
			obj.put("group", year);
			obj.put("bars", addValuesToBarsAsJSONStr(data, (int) year));
			array.put(obj);
		}
		return array.toString();
	}
	

	private List<Number> getAggregatedYears(Map<String, HashMap<Number, Number>> data) {
		Entry<String, HashMap<Number, Number>> entry = data.entrySet().iterator().next();
		String key = entry.getKey();
		List<Number> years = new ArrayList<Number>(data.get(key).keySet());
		DataUtils.sortListOfNums(years);
		return years;
	}


	private JSONArray addValuesToBarsAsJSONStr(Map<String, HashMap<Number, Number>> dataMap, int year) {
		JSONArray barsArray = new JSONArray();
		for (String countryName : dataMap.keySet()) {
			JSONObject obj2 = new JSONObject();
			obj2.put("name", countryName);
			obj2.put("value", dataMap.get(countryName).get(year));
			barsArray.put(obj2);
		}
		return barsArray;
	}
	
}
