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
    
	@Query("SELECT year, SUM(femalePopulation + malePopulation) AS total_population "
			+ "FROM Populations "
			+ "WHERE country_id = :countryId "
			+ "GROUP BY year")
    List<Object[]> getTotalPopulationForCountry(@Param("countryId") int countryId); 
       
    @Query("SELECT year, SUM(malePopulation) \n"
    		+ "FROM Populations \n"
    		+ "WHERE country_id = :countryId \n"
    		+ "GROUP BY year")
    List<Object[]> getMalePopulationForCountry(@Param("countryId") int countryId);
    
    @Query("SELECT year, SUM(femalePopulation) \n"
    		+ "FROM Populations \n"
    		+ "WHERE country_id = :countryId \n"
    		+ "GROUP BY year")
    List<Object[]> getFemalePopulationForCountry(@Param("countryId") int countryId);
   
    @Query("SELECT year, SUM(femalePopulation + malePopulation) \n"
    		+ "FROM Populations \n"
    		+ "WHERE country_id = :countryId and age = :age \n"
    		+ "GROUP BY year")
    List<Object[]> getTotalPopulationForCountryAndAge(@Param("countryId") int countryId, @Param("age") int age);
     
    @Query("SELECT year, SUM(malePopulation) \n"
    		+ "FROM Populations \n"
    		+ "WHERE country_id = :countryId and age = :age \n"
    		+ "GROUP BY year")
    List<Object[]> getMalePopulationForCountryAndAge(@Param("countryId") int countryId, @Param("age") int age);
    
    @Query("SELECT year, SUM(femalePopulation) \n"
    		+ "FROM Populations \n"
    		+ "WHERE country_id = :countryId and age = :age \n"
    		+ "GROUP BY year")
    List<Object[]> getFemalePopulationForCountryAndAge(@Param("countryId") int countryId, @Param("age") int age);
    
}
