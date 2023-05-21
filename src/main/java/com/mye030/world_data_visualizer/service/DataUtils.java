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
	
	public static String convertMapOfNumsToJSONStr(Map<Number, Number> map) {
		JSONArray array = new JSONArray();		
		List<Number> keys = new ArrayList<Number>(map.keySet()); 
		DataUtils.sortListOfNums(keys);
		for (Number key: keys) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("x", key);
			jsonObj.put("y", map.get(key));
			array.put(jsonObj);
		}
		return array.toString();
	}

	public static Map<Number, Number> convertYearsAndValuesToMap(List<Object[]> yearsAndValues) {
		HashMap<Number, Number> result = new HashMap<Number, Number>();
		List<Number> years = DataUtils.getNumbersAtIndex(yearsAndValues, 0);
		List<Number> values = DataUtils.getNumbersAtIndex(yearsAndValues, 1);
		for (int i = 0; i < years.size(); i++) {
			result.put(years.get(i), values.get(i));
		}
		return result;
	}
	
	public static void subsetDataForCommonYears(Map<String, HashMap<Number, Number>> data) {
		List<Number> commonYears = findCommonYears(data);
		for (String key : data.keySet()) {
			data.get(key).keySet().retainAll(commonYears);
		}
	}
	
	public static List<Number> findCommonYears(Map<String, HashMap<Number, Number>> data) {
		List<String> countryNames = new ArrayList<String>(data.keySet());
		List<Number> commonYears = new ArrayList<Number>(data.get(countryNames.get(0)).keySet());
		for (int i = 1; i < countryNames.size(); i++) {
			commonYears.retainAll(data.get(countryNames.get(i)).keySet());
		}
		DataUtils.sortListOfNums(commonYears);
		return commonYears;
	}
	

	public static List<Number> findCommons(List<Number> list1, List<Number> list2) {
		List<Number> commonYears = new ArrayList<Number>(list1);
		commonYears.retainAll(list2);
		sortListOfNums(commonYears);
		return commonYears;
	}
	
	public static void sortListOfNums(List<Number> commonYears) {
		Collections.sort(commonYears, new Comparator<Number>() {
            @Override
            public int compare(Number n1, Number n2) {
                return n1.intValue() - n2.intValue();
            }
        });
	}
	
	public static void aggregateDataBy(int aggr, Map<String, HashMap<Number, Number>> data) {
		for (String key: data.keySet()) {
			data.put(key, (HashMap<Number, Number>) aggregateMapBy(aggr, data.get(key)));
		}
	}

	public static Map<Number, Number> aggregateMapBy(int aggr, Map<Number, Number> dataMap) {
		if (aggr <= 0 || dataMap.keySet().size() < 10 ) {
			return dataMap;
		}
		Map<Number, Number> aggregatedData = new LinkedHashMap<Number, Number>();
		List<Double> group = new ArrayList<>();
        Number groupNumber = null;

        for (Map.Entry<Number, Number> entry : dataMap.entrySet()) {
            double value = entry.getValue().doubleValue();
            group.add(value);

            if (groupNumber == null) {
                groupNumber = entry.getKey();
            }

            if (group.size() == aggr) {
                double aggregateValue = calculateAggregateValue(group);
                aggregatedData.put(groupNumber, aggregateValue);

                group.clear();
                groupNumber = null;
            }
        }

        // Check if there are remaining data points that didn't form a complete group
        if (!group.isEmpty()) {
            double aggregateValue = calculateAggregateValue(group);
            aggregatedData.put(groupNumber, aggregateValue);
        }

        return aggregatedData;
	}

	private static double calculateAggregateValue(List<Double> group) {
		double sum = 0;
		for (double value : group) {
			sum += value;
		}
		return sum / group.size();
	}

	public static Map<Number, Number> filter(int start, int end, Map<Number, Number> dataMap) {
		if (start <=0 || end <= 0 || dataMap.keySet().size() < 10) {
			return dataMap; // do nothing
		}
		Map<Number, Number> filteredData = new LinkedHashMap<Number, Number>();
		for (Number year : dataMap.keySet()) {
			if (start <= year.intValue() && year.intValue() <= end) {
				filteredData.put(year,dataMap.get(year));
			}
		}
		return filteredData;
	}
	
//	public static void main(String args[]) {
//		Map<Number, Number> data = new LinkedHashMap<Number, Number>();
//		for (int i = 0; i <= 53; i++) {
//			data.put(2000+i, i);
//		}
//		System.out.println(data);
//		Map<Number, Number> data2 = DataUtils.aggregateBy(5, data);
//		Map<Number, Number> data3 = DataUtils.filter(2005, 2015, data);
//		System.out.println(data2);
//		System.out.println(data3);
//	}

}