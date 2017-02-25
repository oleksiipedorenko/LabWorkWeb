package ua.skillsup.practice.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.skillsup.practice.rest.exceptions.NotEnoughBeerException;
import ua.skillsup.practice.rest.filter.AuthFilter;
import ua.skillsup.practice.rest.model.ClientOrder;
import ua.skillsup.practice.rest.model.Credentials;
import ua.skillsup.practice.rest.model.ResponseMessage;
import ua.skillsup.practice.rest.service.BarService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
public class MainController {

	@Autowired
	private BarService bar;

	@RequestMapping(value = "/pub", method = RequestMethod.GET)
    public String getPub(Model model) {
		model.addAttribute("barStatus", bar.getBarStatus());
		model.addAttribute("beerKinds", bar.getBeerKinds());
		model.addAttribute("contactInfo", bar.getContactInformation());
		return "pub";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
		return "/login";
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage auth(@RequestBody Credentials credentials,
                                HttpServletRequest request) {
		if (credentials.getLogin().equalsIgnoreCase("admin")
				&& credentials.getPassword().equalsIgnoreCase("qwerty")) {
			request.getSession()
					.setAttribute(AuthFilter.LOG_ATTR_NAME, LocalDateTime.now());
			return ResponseMessage.okMessage(null);
		}
		return ResponseMessage.errorMessage("Wrong credentials!");
    }

    @RequestMapping(value = "/clientOrder", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage clientOrder(@RequestBody ClientOrder clientOrder) {
		return ResponseMessage.okMessage(bar.executeClientOrder(clientOrder));
    }

    @ResponseBody
    @ExceptionHandler(NotEnoughBeerException.class)
    public ResponseMessage handleException(Exception ex) {
		return ResponseMessage.errorMessage(ex.getMessage());
    }

}