package com.mye030.world_data_visualizer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "indicators_values")
@IdClass(IndicatorValueId.class)
public class IndicatorValue {
	
	@Id
	@Column(name = "country_id")
	private int countryId;
	
	@Id
	@Column(name = "year")
	private int year;
	
	@Id
	@Column(name = "indicator_id")
	private int indicatorId;
	
	@Column(name = "indicator_value")
	private double value;

	public IndicatorValue() {}
	
	public IndicatorValue(int countryId, int year, int indicatorId, int value) {
		super();
		this.countryId = countryId;
		this.year = year;
		this.indicatorId = indicatorId;
		this.value = value;
	}

	@Override
	public String toString() {
		return "IndicatorValue [countryId=" + countryId + ", year=" + year + ", indicatorId=" + indicatorId + ", value="
				+ value + "]";
	}
	
}
