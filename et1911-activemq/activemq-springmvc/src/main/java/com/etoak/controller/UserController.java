package com.etoak.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.etoak.bean.User;
import com.etoak.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping("/toReg")
	public String toReg() {
		System.out.println("sss");
		// src/main/resources/templates/reg.html
		return "reg";
	}
	
	@PostMapping("/reg")
	public String reg(User user) {
		
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		user.setUserId(uuid);
		
		
		
		userService.addUser(user);
		
		return "reg";
	}
	
}
