package ua.skillsup.practice.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.skillsup.practice.rest.filter.AuthorisationFilter;
import ua.skillsup.practice.rest.model.AuthorisationData;
import ua.skillsup.practice.rest.model.ClientOrder;
import ua.skillsup.practice.rest.model.RefillOrder;
import ua.skillsup.practice.rest.model.ResponseMessage;
import ua.skillsup.practice.rest.service.BarService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

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

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@ResponseBody
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ResponseMessage auth(@RequestBody AuthorisationData data,
	                            HttpSession session) {
		if (data.getLogin().equalsIgnoreCase("admin") && data.getPassword().equalsIgnoreCase("password123")) {
			session.setAttribute(AuthorisationFilter.AUTH_ATTR, LocalDateTime.now());
			return ResponseMessage.okMessage(null);
		} else {
			return ResponseMessage.errorMessage("Failed");
		}

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