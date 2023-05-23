package com.mye030.world_data_visualizer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "populations")
@IdClass(PopulationsId.class)
public class Populations {
	@Id
	@Column(name = "country_id")
	private int countryId;
	
	@Id
	@Column(name = "year")
	private int year;
		
	@Id
	@Column(name = "age")
	private int age;
	
	@Column(name = "female_population")
	private double femalePopulation;

	@Column(name = "male_population")
	private double malePopulation;

	public Populations(int countryId, int year, int age, double femalePopulation, double malePopulation) {
		super();
		this.countryId = countryId;
		this.year = year;
		this.age = age;
		this.femalePopulation = femalePopulation;
		this.malePopulation = malePopulation;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getFemalePopulation() {
		return femalePopulation;
	}

	public void setFemalePopulation(double femalePopulation) {
		this.femalePopulation = femalePopulation;
	}

	public double getMalePopulation() {
		return malePopulation;
	}

	public void setMalePopulation(double malePopulation) {
		this.malePopulation = malePopulation;
	}

	@Override
	public String toString() {
		return "Populations [countryId=" + countryId + ", year=" + year + ", age=" + age + ", femalePopulation="
				+ femalePopulation + ", malePopulation=" + malePopulation + "]";
	}

}
