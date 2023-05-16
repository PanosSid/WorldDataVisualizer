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
	@Column(name = "sex")
	private String sex;
	
	@Id
	@Column(name = "age")
	private int age;
	
	@Column(name = "population")
	private double population;

	public Populations(int countryId, int year, String sex, int age, double population) {
		super();
		this.countryId = countryId;
		this.year = year;
		this.sex = sex;
		this.age = age;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getPopulation() {
		return population;
	}

	public void setPopulation(double population) {
		this.population = population;
	}

	@Override
	public String toString() {
		return "Populations [countryId=" + countryId + ", year=" + year + ", sex=" + sex + ", age=" + age
				+ ", population=" + population + "]";
	}
	
		
	
}
