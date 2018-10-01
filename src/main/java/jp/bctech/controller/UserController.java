package jp.bctech.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public User create(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password,
			@RequestParam(name = "name") String name) {
		
		System.out.println("==== user name : " + name);
		System.out.println("==== user password : " + password);
		System.out.println("==== user name: " + name);
		
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		
		User result = service.save(user);
		System.out.println(result.getName());
		System.out.println(result.getPassword());
		System.out.println(result.getEmail());
		System.out.println(result.getCreatedAt());
		System.out.println(result.getUpdateAt());
		
		
		
		return result;
		
	}
}