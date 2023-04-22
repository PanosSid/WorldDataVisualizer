package com.mye030.world_data_visualizer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import javax.persistence.Table;

@Entity
@Table(name = "countries_population")
@IdClass(CountryPopulationId.class)
public class CountryPopulation {
	
	@Id
//	@OneToOne
//    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
	@Column(name = "country_id")
	private int countryId;
	
	@Id
	@Column(name = "year")
	private int year;
	
	@Column(name = "population")
	private int population;
	
	
	public CountryPopulation() {}
	
	public CountryPopulation(int countryId, int year) {
		super();
		this.countryId = countryId;
		this.year = year;
	}
	
	public CountryPopulation(int countryId, int year, int population) {
		super();
		this.countryId = countryId;
		this.year = year;
		this.population = population;
	}
	
	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	@Override
	public String toString() {
		return "CountryPopulation [countryId=" + countryId + ", year=" + year + ", population=" + population + "]";
	}
	
	
	
}
