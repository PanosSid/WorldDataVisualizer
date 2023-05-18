package com.mye030.world_data_visualizer.model;

import java.util.List;

public class FormData {
	private String chartType;
	private List<String> countries;
	private List<String> indicators;
	
	public FormData() {}
	
	public FormData(String chartType, List<String> countries, List<String> indicators) {
		super();
		this.chartType = chartType;
		this.countries = countries;
		this.indicators = indicators;
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

	@Override
	public String toString() {
		return "FormData [chartType=" + chartType + ", countries=" + countries + ", indicators=" + indicators + "]";
	}
	
	
}
