package com.mye030.world_data_visualizer.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mye030.world_data_visualizer.service.CountryPopulationService;

@Controller
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
		
	@RequestMapping("/generateChart")
	public void generateChart(@RequestParam("indicator") String param, Model model) {
		System.out.println(param);
	}
	
	@RequestMapping("/lineChart")
	public ModelAndView multiSeriesJson(ModelMap model) {
		String jsonString = populationService.findByCountryIdAsJSONString(1);
        model.addAttribute("dataGiven", jsonString);
		return new ModelAndView("linechart", model);
	}
	
}
