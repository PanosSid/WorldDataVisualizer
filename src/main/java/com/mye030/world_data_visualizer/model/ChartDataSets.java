package com.mye030.world_data_visualizer.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mye030.world_data_visualizer.service.DataUtils;

public class ChartDataSets {
	private String chartType;
	private List<ChartData> dataList;
	
	public ChartDataSets(String chartType) {
		this.chartType = chartType;
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
		DataUtils.sortListOfNums(commonYears);
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
}
