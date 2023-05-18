package com.mye030.world_data_visualizer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mye030.world_data_visualizer.model.Populations;
import com.mye030.world_data_visualizer.model.PopulationsId;

@Repository
public interface PopulationsRepository  extends JpaRepository<Populations, PopulationsId> {
    
	@Query("SELECT year, SUM(population)\n"
			+ "FROM Populations\n"
			+ "WHERE country_id = :countryId\n"
			+ "GROUP BY year")
    List<Object[]> getTotalPopulationForCountry(@Param("countryId") int countryId);

    
    @Query("SELECT FLOOR(year / :aggrYears) * :aggrYears AS start_year, SUM(population)/:aggrYears AS total_population_5yr \n"
    		+ "FROM Populations \n"
    		+ "WHERE country_id = :countryId\n"
    		+ "GROUP BY FLOOR(year / :aggrYears)")
    List<Object[]> getTotalPopulationForCountryByAggregateYears(@Param("countryId") int countryId, @Param("aggrYears") int aggrYears);
    
    
    @Query("SELECT year, SUM(population)\n"
			+ "FROM Populations\n"
			+ "WHERE country_id = :countryId and sex = :sex\n"
			+ "GROUP BY year")
    List<Object[]> getPopulationForCountryAndSex(@Param("countryId") int countryId, @Param("sex") String gender);
    
    @Query("SELECT year, SUM(population) \n"
    		+ "FROM Populations \n"
    		+ "WHERE country_id = :countryId and age = :age \n"
    		+ "GROUP BY year")
    List<Object[]> getTotalPopulationForCountryAndAge(@Param("countryId") int countryId, @Param("age") int age);
    
    
    @Query("SELECT year, SUM(population) \n"
    		+ "FROM Populations \n"
    		+ "WHERE country_id = :countryId and age = :age and sex = :sex\n"
    		+ "GROUP BY year")
    List<Object[]> getPopulationForCountryAndAgeAndSex(@Param("countryId") int countryId, @Param("age") int age, @Param("sex") String gender);

}
