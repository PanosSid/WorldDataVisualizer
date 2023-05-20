package com.mye030.world_data_visualizer.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

	public static String convertListsOfNumsToJSONStr(List<Number> xValues, List<Number> yValues) {
		JSONArray array = new JSONArray();
		for (int i = 0; i < xValues.size(); i++) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("x", xValues.get(i));
			jsonObj.put("y", yValues.get(i));
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
	
	public static List<Number> findCommons(List<Number> list1, List<Number> list2){
		List<Number> commonYears = new ArrayList<Number>(list1);
		commonYears.retainAll(list2);
	
		Collections.sort(commonYears, new Comparator<Number>() {
            @Override
            public int compare(Number n1, Number n2) {
                return n1.intValue() - n2.intValue();
            }
        });
		
		return commonYears;
	}
	
}