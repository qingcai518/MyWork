package jp.bctech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.bctech.entity.User;
import jp.bctech.service.UserDetailsServiceImpl;;

@Controller
public class UserController {
	@Autowired
	UserDetailsServiceImpl service;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/register")
	public User create(
			@RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password,
			@RequestParam(name = "name") String name
			) {
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPw = encoder.encode(password);
		user.setPassword(encodedPw);
		
		return service.save(user);
	}
}