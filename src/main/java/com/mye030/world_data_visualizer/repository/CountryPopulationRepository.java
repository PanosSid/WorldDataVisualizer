package com.mye030.world_data_visualizer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mye030.world_data_visualizer.model.CountryPopulation;
import com.mye030.world_data_visualizer.model.CountryPopulationId;

/*
 * Needs and Entity to work 
 */
@Repository
public interface CountryPopulationRepository extends JpaRepository<CountryPopulation, CountryPopulationId> {
	
	@Query(value = "SELECT population FROM countries_population g WHERE g.country_id = :country_id", nativeQuery = true)
	List<Integer> selectPopulationsByCountryIdForAllYears(@Param("country_id") int countryId);
	
//	Equivalent to findCountryPopulationById();
	@Query(value = "SELECT * FROM countries_population g WHERE g.country_id = :country_id", nativeQuery = true)
	List<CountryPopulation> findByCountryId(@Param("country_id") int countryId);
	
}
