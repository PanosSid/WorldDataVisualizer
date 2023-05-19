package com.mye030.world_data_visualizer.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mye030.world_data_visualizer.model.FormData;
import com.mye030.world_data_visualizer.service.AppService;

@Controller
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
	public ModelAndView generateChart(@RequestBody FormData formData, ModelMap model) {
		model.addAttribute("indicators", formData.getIndicators());
		model.addAttribute("countries", formData.getCountries());
        return new ModelAndView("redirect:/"+formData.getChartType()+"Chart", model);
	}
	
	@GetMapping("/lineChart")
	public ModelAndView viewLineChart(@RequestParam("countries") List<String> countries, @RequestParam("indicators") List<String> indicators, ModelMap model) {
		String jsonString = appService.getValuesByCountryAndIndicatorAsJSONStr(countries, indicators);
        model.addAttribute("dataGiven", jsonString);
        model.addAttribute("countries", countries);
        model.addAttribute("indicators", indicators);
		return new ModelAndView("linechart", model);
	}
	
	@GetMapping("/barChart")
	public ModelAndView viewBarChart(@RequestParam("countries") List<String> countries, @RequestParam("indicators") List<String> indicators, ModelMap model) { 
		String jsonString = appService.getDataForBarChart(countries, indicators);
        model.addAttribute("dataGiven", jsonString);
        model.addAttribute("countries", countries);
        model.addAttribute("indicators", indicators);
		return new ModelAndView("barchart", model);
	}
	
	@GetMapping("/scatterPlot")
	public ModelAndView viewScatterChart(/*@RequestParam("country") String country, @RequestParam("indicators") List<String> indicators, */ModelMap model) {
		String country = "countryName";
		List<String> indicators = Arrays.asList("indicator1", "indicator2");
//		String jsonString = appService.getValuesByCountryAndIndicatorAsJSONStr(country, indicators);
//		model.addAttribute("dataGiven", jsonString);
		return new ModelAndView("scatter_plot", model);
	}
	
}