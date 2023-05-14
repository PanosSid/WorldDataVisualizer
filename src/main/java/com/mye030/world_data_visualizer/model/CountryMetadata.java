package com.mye030.world_data_visualizer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "countries")
public class CountryMetadata {
	
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;

	public CountryMetadata() {}	
	
	public CountryMetadata(int id, String officialName) {
		super();
		this.id = id;
		this.name = officialName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOfficialName() {
		return name;
	}

	public void setOfficialName(String officialName) {
		this.name = officialName;
	}

	@Override
	public String toString() {
		return "CountryMetadata [id=" + id + ", officialName=" + name + "]";
	}
	
	
}
