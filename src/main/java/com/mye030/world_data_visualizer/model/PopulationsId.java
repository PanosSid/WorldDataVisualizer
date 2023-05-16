package com.mye030.world_data_visualizer.model;

import java.io.Serializable;
import java.util.Objects;

public class PopulationsId  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int countryId;
	private int year;
	private String sex;
	private int age;
	
	public PopulationsId() {}
	
	public PopulationsId(int countryId, int year, String sex, int age) {
		super();
		this.countryId = countryId;
		this.year = year;
		this.sex = sex;
		this.age = age;
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, countryId, sex, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PopulationsId other = (PopulationsId) obj;
		return age == other.age && countryId == other.countryId && Objects.equals(sex, other.sex) && year == other.year;
	}

}
