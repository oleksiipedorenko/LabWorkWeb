package ua.skillsup.practice.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.skillsup.practice.rest.model.ClientOrder;
import ua.skillsup.practice.rest.model.RefillOrder;
import ua.skillsup.practice.rest.model.ResponseMessage;
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

	@ResponseBody
	@RequestMapping(value = "/barStatus", method = RequestMethod.GET)
	public ResponseMessage getBarStatus() {
		return ResponseMessage.okMessage(bar.getBarStatus());
	}

	@ResponseBody
	@RequestMapping(value = "/clientOrder", method = RequestMethod.POST)
	public ResponseMessage executeClientOrder(@RequestBody ClientOrder clientOrder) {
		return ResponseMessage.okMessage(bar.executeClientOrder(clientOrder));
	}

	@ResponseBody
	@RequestMapping(value = "/refillOrder", method = RequestMethod.POST)
	public ResponseMessage executeRefillOrder(@RequestBody RefillOrder refillOrder) {
		return ResponseMessage.okMessage(bar.executeRefillOrder(refillOrder));
	}

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ResponseMessage handleException(Exception e) {
		return ResponseMessage.errorMessage(e.getMessage());
	}
}
