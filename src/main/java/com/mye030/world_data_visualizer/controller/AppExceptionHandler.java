package com.mye030.world_data_visualizer.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mye030.world_data_visualizer.service.DataNotFoundException;

@ControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(DataNotFoundException.class)
	public String handleCustomException(DataNotFoundException ex, Model model) {
		model.addAttribute("msg", "There is no available data for the selected combination of country and indicator.");
		model.addAttribute("country", ex.getCountryName());
		model.addAttribute("indicator", ex.getIndicatroName());
		return "error-page";
	}
}