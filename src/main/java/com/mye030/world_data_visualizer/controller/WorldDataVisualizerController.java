package com.mye030.world_data_visualizer.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mye030.world_data_visualizer.service.CountryPopulationService;



@Controller
public class WorldDataVisualizerController {
	
	@Autowired
	private CountryPopulationService populationService;

	@GetMapping("/chartForm")
	public ModelAndView displayMainView() {
		return new ModelAndView("mainPage.html");
	}
		
	@RequestMapping("/lineChart")
	public ModelAndView multiSeriesJson(ModelMap model) {
		String jsonString = populationService.findByCountryIdAsJSONString(1);
        model.addAttribute("dataGiven", jsonString);
		return new ModelAndView("linechart", model);
	}

	
}
