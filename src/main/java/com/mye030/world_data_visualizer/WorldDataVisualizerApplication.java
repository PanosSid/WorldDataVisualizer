package com.mye030.world_data_visualizer;




import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.mye030.world_data_visualizer.model.CountryPopulation;
import com.mye030.world_data_visualizer.model.CountryPopulationId;
import com.mye030.world_data_visualizer.repository.CountryPopulationRepository;
import com.mye030.world_data_visualizer.repository.GPATotalRepository;
import com.mye030.world_data_visualizer.service.CountryPopulationService;


@SpringBootApplication
@ComponentScan(basePackages = {"com.mye030.world_data_visualizer"})
public class WorldDataVisualizerApplication implements CommandLineRunner {
	
	@Autowired
	private CountryPopulationService populationService;
	
	@Autowired 
	private CountryPopulationRepository populationRepo;
	
	@Autowired
	private GPATotalRepository gpaRepo;
	
	
	public static void main(String[] args) {
		SpringApplication.run(WorldDataVisualizerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {		
		populationService.findByCountryIdAsJSONString(1);		
		System.out.println(populationRepo.findByCountryId(1));	// should return [100, 110, 120]
		System.out.println(populationRepo.selectPopulationsByCountryIdForAllYears(1));	// should return [100, 110, 120]
		System.out.println(getCountryPopulationByIds(1, 2020));
	
		System.out.println(gpaRepo.selectGDPTotalByCountryIdAndYear(8, 1990)); 	// should return 14.7
		System.out.println(gpaRepo.selectAllGDPTotalByCountryId(8)); 	// should return [14.7,13.2,16.8,22.4,28.9,30.5,34]
			
	}
	
	public CountryPopulation getCountryPopulationByIds(int countryId, int year) {
		Optional<CountryPopulation> optional =  populationRepo.findById(new CountryPopulationId(countryId, year));
		if (optional.isPresent()) {
		    return optional.get();
		} else {
		    System.out.println("Value is absent");
		    return null;
		}
	}
	
}
