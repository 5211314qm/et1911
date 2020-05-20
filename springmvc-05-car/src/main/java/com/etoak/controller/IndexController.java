package com.etoak.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etoak.bean.User;

@Controller
public class IndexController {

	private List<User> userList = new ArrayList<>();
	
	@RequestMapping("/add")
	public String add (String name) {
		System.out.println(Thread.currentThread().getName());
		System.out.println(name);
		return "forward:/index";
	}
	
	@RequestMapping("/json")
	@ResponseBody
	public String json(@RequestBody Map<String,String> map) {
		System.out.println(map);
		
		return "success";
	}
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		
		//向request域传值
		request.setAttribute("user", new User(1,"requestUser","",20));
		
		//向session中传值
		request.getSession().setAttribute("sessionUser", new User(2,"sessionUser" ,"",21));
		
		//用于测试前端thymeleaf遍历List
		List<User> userList = new ArrayList<>();
		
		request.setAttribute("userList", userList);
		
		userList.add(new User(3,"张三","",28));
		userList.add(new User(4,"李四","",22));
		
		// webapp/templates/index.html
		return "index";
	}
}
