package com.mye030.world_data_visualizer.repository;

import java.util.List;

import org.springframework.stereotype.Repository;


public interface GPATotalRepository {
	double selectGDPTotalByCountryIdAndYear(int countryId, int year);
	List<Double> selectAllGDPTotalByCountryId(int countryId);
}
