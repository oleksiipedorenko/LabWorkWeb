package ua.skillsup.practice.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.skillsup.practice.rest.filter.LoginFilter;
import ua.skillsup.practice.rest.model.AuthData;
import ua.skillsup.practice.rest.model.ClientOrder;
import ua.skillsup.practice.rest.model.ResponseMessage;
import ua.skillsup.practice.rest.service.BarService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
public class LoginController {

	@Autowired
	private BarService bar;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLogin() {
		return "login";
	}

	@ResponseBody
	@RequestMapping(value = "/submitLogin", method = RequestMethod.POST)
	public ResponseMessage submitLogin(@RequestBody AuthData authData,
	                                   HttpServletRequest request) {

		if (authData.getLogin().equals("admin")
				&& authData.getPassword().equals("123456")) {
			request.getSession().setAttribute(LoginFilter.LOGIN_ATTR, LocalDateTime.now());

			return ResponseMessage.okMessage(bar.getBarStatus());
		} else {
			return ResponseMessage.errorMessage("Wrong authorisation");
		}

	}
}