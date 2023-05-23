package com.mye030.world_data_visualizer.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		Map<String, List<String>> countryIndicatorsMap = appService.getCountriesAndTheirIndicators();
	    model.addAttribute("countryIndicatorsMap", countryIndicatorsMap);
		return new ModelAndView("chart_generator.html", model);
	}
		
	@PostMapping("/generateChart")
	public String generateChart(@RequestBody FormData formData, HttpSession session) {
		session.setAttribute("formData", formData);
		return "redirect:/"+formData.getChartType()+"Chart";
	}
	
	@GetMapping("/lineChart")
	public ModelAndView viewLineChart(HttpSession session, ModelMap model) {
		FormData formData = (FormData) session.getAttribute("formData");
		String jsonString = appService.getDataForLineChart(formData.getCountries(), formData.getIndicators(),
				formData.getAggregate(), formData.getMinYear(),formData.getMaxYear());
        model.addAttribute("dataGiven", jsonString);
        model.addAttribute("countries", formData.getCountries());
        model.addAttribute("indicators", formData.getIndicators());
		return new ModelAndView("linechart", model);
	}
	
	@GetMapping("/barChart")
	public ModelAndView viewBarChart(HttpSession session, ModelMap model) { 
		FormData formData = (FormData) session.getAttribute("formData");
		String jsonString = appService.getDataForBarChart(formData.getCountries(), formData.getIndicators(),
				formData.getAggregate(), formData.getMinYear(), formData.getMaxYear());
        model.addAttribute("dataGiven", jsonString);
        model.addAttribute("countries", formData.getCountries());
        model.addAttribute("indicators", formData.getIndicators());
		return new ModelAndView("barchart", model);
	}
	
	@GetMapping("/scatterChart")
	public ModelAndView viewScatterChart(HttpSession session, ModelMap model) {
		FormData formData = (FormData) session.getAttribute("formData");
		String jsonString = appService.getDataForScatterChart(formData.getCountries(), formData.getIndicators(),
				formData.getAggregate(), formData.getMinYear(), formData.getMaxYear());
		model.addAttribute("dataGiven", jsonString);
		model.addAttribute("countries", formData.getCountries());
		model.addAttribute("indicators", formData.getIndicators());
		return new ModelAndView("scatterchart", model);
	}
	
}