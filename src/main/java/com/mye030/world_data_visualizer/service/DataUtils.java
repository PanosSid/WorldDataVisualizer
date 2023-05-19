package com.mye030.world_data_visualizer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class DataUtils {
	
	public static List<Number> getNumbersAtIndex(List<Object[]> list, int index) {
		List<Number> targetList = new ArrayList<Number>();
		for (Object[] arr : list) {
			targetList.add((Number) arr[index]);
		}
		return targetList;
	}

	public static String convertListsOfNumsToJSONStr(List<Number> years, List<Number> values) {
		JSONArray array = new JSONArray();
		for (int i = 0; i < years.size(); i++) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("x", years.get(i));
			jsonObj.put("y", values.get(i));
			array.put(jsonObj);
		}
		return array.toString();
	}
	
	public static HashMap<Number, Number> convertListsToMap(List<Number> years, List<Number> values){
		HashMap<Number, Number> result = new  HashMap<Number, Number> ();
		for (int i = 0; i < years.size(); i++) {
		    result.put(years.get(i), values.get(i));
		}
		return result;
	}

}