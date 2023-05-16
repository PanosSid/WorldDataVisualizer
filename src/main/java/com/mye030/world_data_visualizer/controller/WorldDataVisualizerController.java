package com.mye030.world_data_visualizer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mye030.world_data_visualizer.service.AppService;

@RestController
public class WorldDataVisualizerController {
	
	@Autowired
	private AppService appService;
	
	@GetMapping("/chartForm")
	public ModelAndView displayMainView(ModelMap model) {
		List<String> indicators = appService.getAllIndicatorNames();
		model.addAttribute("indicators", indicators);
		List<String> countries = appService.getAllCountryNames();
		model.addAttribute("countries", countries);
		return new ModelAndView("chart_generator.html", model);
	}
		
	@PostMapping("/generateChart")
	public void generateChart(@RequestBody List<List<String>> pairs) {
		System.out.println(pairs);
	}
	
	@RequestMapping("/lineChart")
	public ModelAndView multiSeriesJson(ModelMap model) {
//		String jsonString = appService.getValuesByCountryAndIndicatorAsJSONStr("Greece", "Birth rate (births per 1.000 population)");
//		String jsonString = appService.getTotalPopulationForCountry("Greece");
		String jsonString = appService.getValuesByCountryAndIndicatorAsJSONStr("Greece", "Gross national income (GNI) per capita (2011 PPP $)");
		System.out.println(jsonString);
        model.addAttribute("dataGiven", jsonString);
		return new ModelAndView("linechart", model);
	}
	
}