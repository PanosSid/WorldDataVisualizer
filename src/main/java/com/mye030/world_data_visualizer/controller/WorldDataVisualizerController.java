package com.mye030.world_data_visualizer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ModelAndView viewLineChart(@ModelAttribute("countries") List<String> countries, @ModelAttribute("indicators") List<String> indicators, ModelMap model) {
		String jsonString = appService.getValuesByCountryAndIndicatorAsJSONStr(countries.get(0), indicators.get(0));
        model.addAttribute("dataGiven", jsonString);
		return new ModelAndView("linechart", model);
	}
	
	@GetMapping("/barChart")
	public ModelAndView viewBarChart(@ModelAttribute("countries") List<String> countries, @ModelAttribute("indicators") List<String> indicators, ModelMap model) {
        model.addAttribute("chartTitle", "Bar chart for "+countries+"\n"+indicators);
		return new ModelAndView("not_implemented", model);
	}
	
	@GetMapping("/scatterChart")
	public ModelAndView viewScatterChart(@ModelAttribute("countries") List<String> countries, @ModelAttribute("indicators") List<String> indicators, ModelMap model) {
        model.addAttribute("chartTitle", "Scatter chart for "+countries+"\n"+indicators);
		return new ModelAndView("not_implemented", model);
	}
	
}