package com.mye030.world_data_visualizer.service;

public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String countryName;
	private String indicatroName;
	
	public DataNotFoundException() {
		super();
	}

	public DataNotFoundException(String message) {
		super(message);
	}

	public DataNotFoundException(String countryName, String indicatorName) {
		super();
		this.countryName = countryName;
		this.indicatroName = indicatorName;
	}
	
	public String getCountryName() {
		return countryName;
	}

	public String getIndicatroName() {
		return indicatroName;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}
	
	
	
	

}
