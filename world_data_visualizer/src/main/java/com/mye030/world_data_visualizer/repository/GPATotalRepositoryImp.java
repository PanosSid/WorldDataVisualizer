package com.mye030.world_data_visualizer.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GPATotalRepositoryImp implements GPATotalRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public double selectGDPTotalByCountryIdAndYear(int countryId, int year) {
		String sql = "SELECT gdp_total_value FROM gdp_total WHERE country_id = "+countryId + " and year = "+year+";";
		return jdbcTemplate.queryForObject(sql, double.class);
	}
	
	public List<Double> selectAllGDPTotalByCountryId(int countryId) {
		String sql = "SELECT gdp_total_value FROM gdp_total WHERE country_id = "+countryId +";";
		return jdbcTemplate.queryForList(sql, Double.class);
	}
	
}
