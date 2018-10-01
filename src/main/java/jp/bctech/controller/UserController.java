package jp.bctech.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class UserController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
}
