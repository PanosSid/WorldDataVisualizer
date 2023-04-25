package com.mye030.world_data_visualizer.model;

import java.io.Serializable;
import java.util.Objects;

/*
 * Used as a composite primary key for entity CountryPopulation
 */
public class CountryPopulationId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1571056679459717746L;
	
	private int countryId;
	private int year;
	
		
	public CountryPopulationId() {
		super();
	}

	public CountryPopulationId(int countryId, int year) {
		super();
		this.countryId = countryId;
		this.year = year;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(countryId, year);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CountryPopulationId other = (CountryPopulationId) obj;
		return countryId == other.countryId && year == other.year;
	}
	
	
}
