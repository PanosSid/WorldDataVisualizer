package com.mye030.world_data_visualizer.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mye030.world_data_visualizer.service.CountryPopulationService;

@RestController
public class WorldDataVisualizerController {
	
	@Autowired
	private CountryPopulationService populationService;

	@GetMapping("/chartForm")
	public ModelAndView displayMainView(ModelMap model) {
		List<String> indicators = Arrays.asList("metric1", "metric2", "metric3");
		model.addAttribute("indicators", indicators);
		List<String> countries = Arrays.asList("country1", "country2", "country3");
		model.addAttribute("countries", countries);
		return new ModelAndView("chart_generator.html", model);
	}
		
	@PostMapping("/generateChart")
	public void generateChart(@RequestBody String data) {
		System.out.println("Data from view: "data);
		String dataArray[] = data.split(",");
		List<String> countries = new ArrayList<String>();
		List<String> indicators = new ArrayList<String>();
		for (int i = 0; i < dataArray.length; i++) {
			if (i % 2 == 0) {
				indicators.add(dataArray[i]);
			} else {
				countries.add(dataArray[i]);
			}
		}
		System.out.println("Countries: "+countries);
		System.out.println("Indicators: "+indicators);
	}
	
	@RequestMapping("/lineChart")
	public ModelAndView multiSeriesJson(ModelMap model) {
		String jsonString = populationService.findByCountryIdAsJSONString(1);
        model.addAttribute("dataGiven", jsonString);
		return new ModelAndView("linechart", model);
	}
	
}
