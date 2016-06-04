package ua.skillsup.practice.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.skillsup.practice.rest.service.BarService;

@Controller
public class MainController {

	@Autowired
	private BarService bar;

	@RequestMapping(value = "/pub", method = RequestMethod.GET)
    public String getPub(Model model) {
		model.addAttribute("beerKinds", bar.getBeerKinds());
		model.addAttribute("contactInformation", bar.getContactInformation());
		model.addAttribute("barStatus", bar.getBarStatus());
		return "pub";
    }

}