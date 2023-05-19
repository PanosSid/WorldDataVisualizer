package com.mye030.world_data_visualizer.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class BarChartFormatter {
	
	public String getFormattedBarChartData(Map<String, HashMap<Number, Number>> data) {
		List<Number> commonYears = findCommonYears(data);
		JSONArray array = new JSONArray();
		for (Number year : commonYears) {
			JSONObject obj = new JSONObject();
			obj.put("group", year);
			obj.put("bars", addValuesToBarsAsJSONStr(data, (int) year));
			array.put(obj);
		}
		return array.toString();
	}

	private List<Number> findCommonYears(Map<String, HashMap<Number, Number>> data) {
		List<String> countryNames = new ArrayList<String>(data.keySet());
		List<Number> commonYears = new ArrayList<Number>(data.get(countryNames.get(0)).keySet());
		for (int i = 1; i < countryNames.size(); i++) {
			commonYears.retainAll(data.get(countryNames.get(i)).keySet());
		}
		Collections.sort(commonYears, new Comparator<Number>() {
            @Override
            public int compare(Number n1, Number n2) {
                return n1.intValue() - n2.intValue();
            }
        });
		return commonYears;
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
