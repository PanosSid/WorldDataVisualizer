package com.mye030.world_data_visualizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WorldDataVisualizerController {
	
	@GetMapping("/chartForm")
	public ModelAndView displayMainView() {
		return new ModelAndView("mainPage.html");
	}
	
	@PostMapping("/processForm")
	public void processForm() {
		System.out.println("---------");
	}

	
}
