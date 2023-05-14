package com.mye030.world_data_visualizer.model;

import java.io.Serializable;
import java.util.Objects;


public class IndicatorValueId implements Serializable {

	private static final long serialVersionUID = -7129914736413561110L;
	
	private int countryId;
	private int year;
	private int indicatorId;
	
	public IndicatorValueId() {
		super();
	}

	
	public IndicatorValueId(int countryId, int year, int indicatorId) {
		super();
		this.countryId = countryId;
		this.year = year;
		this.indicatorId = indicatorId;
	}


	@Override
	public int hashCode() {
		return Objects.hash(countryId, indicatorId, year);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IndicatorValueId other = (IndicatorValueId) obj;
		return countryId == other.countryId && indicatorId == other.indicatorId && year == other.year;
	}


	
}
