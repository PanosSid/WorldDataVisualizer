package com.mye030.world_data_visualizer.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mye030.world_data_visualizer.service.DataUtils;

public class ChartData {
	private String countryName;
	private String indicatorName;
	private Map<Number, Number> yearsAndValues;
	
	public ChartData(String countryName, String indicatorName, List<Object[]> dbData) {
		super();
		this.countryName = countryName;
		this.indicatorName = indicatorName;
		this.yearsAndValues = new LinkedHashMap<Number, Number>();
		for (Object[] obj : dbData) {
			this.yearsAndValues.put((Number) obj[0], (Number) obj[1]);
		}
	}
	
	public String getCountryName() {
		return countryName;
	}

	public List<Number> getYears(){
		return new ArrayList<Number>(yearsAndValues.keySet());
	}
	
	public List<Number> getValues(){
		List<Number> values = new ArrayList<Number>();
		for (Number year : yearsAndValues.keySet()) {
			values.add(yearsAndValues.get(year));
		}
		return values;
	}

	public void aggregateBy(int aggr) {
		if (aggr <= 0 || yearsAndValues.keySet().size() < 10 ) {
			return;
		}
		Map<Number, Number> aggregatedData = new LinkedHashMap<Number, Number>();
		List<Double> group = new ArrayList<>();
        Number groupNumber = null;

        for (Map.Entry<Number, Number> entry : yearsAndValues.entrySet()) {
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
        
        yearsAndValues = aggregatedData;
	}

	private static double calculateAggregateValue(List<Double> group) {
		double sum = 0;
		for (double value : group) {
			sum += value;
		}
		return sum / group.size();
	}

	public void filter(int start, int end) {
		if (start <=0 || end <= 0 || yearsAndValues.keySet().size() < 10) {
			return;
		}
		Map<Number, Number> filteredData = new LinkedHashMap<Number, Number>();
		for (Number year : yearsAndValues.keySet()) {
			if (start <= year.intValue() && year.intValue() <= end) {
				filteredData.put(year, yearsAndValues.get(year));
			}
		}
		yearsAndValues = filteredData;
	}
	
	public String convertToJSONStr() {
		JSONArray array = new JSONArray();		
		List<Number> keys = new ArrayList<Number>(yearsAndValues.keySet()); 
		sortListOfNums(keys);
		for (Number key: keys) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("x", key);
			jsonObj.put("y", yearsAndValues.get(key));
			array.put(jsonObj);
		}
		return array.toString();
	}
	
	public void sortListOfNums(List<Number> commonYears) {
		Collections.sort(commonYears, new Comparator<Number>() {
            @Override
            public int compare(Number n1, Number n2) {
                return n1.intValue() - n2.intValue();
            }
        });
	}

	public void retainCommonsYears(List<Number> commonYears) {
		yearsAndValues.keySet().retainAll(commonYears);
		
	}

	public JSONObject convertToBarJSONObj(int year, int i) {
		JSONObject obj = new JSONObject();
		obj.put("name", countryName+i);
		obj.put("value", yearsAndValues.get(year));
		return obj;
	}

}
