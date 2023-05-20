package com.mye030.world_data_visualizer.model;

import java.util.List;

public class FormData {
	private String chartType;
	private List<String> countries;
	private List<String> indicators;
	private int minYear;
	private int maxYear;
	private int aggregate;

	public FormData() {}
	
	public FormData(String chartType, List<String> countries, List<String> indicators, int maxYear, int minYear, int aggregate) {
		super();
		this.chartType = chartType;
		this.countries = countries;
		this.indicators = indicators;
		this.minYear = minYear;
		this.maxYear = maxYear;
		this.aggregate = aggregate;
	}

	public String getChartType() {
		return chartType;
	}

	public void setChartType(String chartType) {
		this.chartType = chartType;
	}

	public List<String> getCountries() {
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	public List<String> getIndicators() {
		return indicators;
	}

	public void setIndicators(List<String> indicators) {
		this.indicators = indicators;
	}

	public int getMinYear() {
		return minYear;
	}

	public void setMinYear(int minYear) {
		this.minYear = minYear;
	}

	public int getMaxYear() {
		return maxYear;
	}

	public void setMaxYear(int maxYear) {
		this.maxYear = maxYear;
	}

	public int isAggregate() {
		return aggregate;
	}

	public void setAggregate(int aggregate) {
		this.aggregate = aggregate;
	}

	@Override
	public String toString() {
		return "FormData [chartType=" + chartType + ", countries=" + countries + ", indicators=" + indicators
				+ ", minYear=" + minYear + ", maxYear=" + maxYear + ", aggregate=" + aggregate + "]";
	}
	
}