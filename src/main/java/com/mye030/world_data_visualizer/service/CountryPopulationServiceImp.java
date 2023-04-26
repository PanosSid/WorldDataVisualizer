package com.mye030.world_data_visualizer.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mye030.world_data_visualizer.model.CountryPopulation;
import com.mye030.world_data_visualizer.repository.CountryPopulationRepository;

@Service
public class CountryPopulationServiceImp implements CountryPopulationService {
	
	@Autowired 
	private CountryPopulationRepository populationRepo;
	
	public CountryPopulationServiceImp() {}
	
	@Override
	public String findByCountryIdAsJSONString(int countryId) {
		List<CountryPopulation> list = populationRepo.findByCountryId(countryId);
		JSONArray array = new JSONArray();
		for (CountryPopulation cp : list) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("x", cp.getYear());
			jsonObj.put("y", cp.getPopulation());
			array.put(jsonObj);
		}
		return array.toString();

	}

}
