package com.etoak.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 使用HttpServletRequest接收参数<BR>
 * 使用基本数据类型、String接收参数
 * 
 */
@Controller
@RequestMapping("/simple")
public class SimpleController {
	
	/**
	 * 第一种想request域传值的方式：使用HttpServletRequest
	 * 
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/servlet")
	public String servlet (HttpServletRequest request) {
		
		String name = request.getParameter("name");
		if(StringUtils.isEmpty(name)) {
			name = "World";
		}
		
		request.setAttribute("result", "Hello " + name);
		
		//webapp/pages/param.jsp
		return "param";
	}
	
	/**
	 * 第二种接收参数的方式：使用基本数据类型 + String
	 * 第二种想request域传值的方式：使用ModelAndView
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/simple",method = {RequestMethod.GET})
	public ModelAndView simple(@RequestParam(required = false,defaultValue = "1") int id,
							   String name) {
		
		System.out.println("id param - " + id);
		System.out.println("name param - " + name);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("result" , "MOdelAndView传值");
		mv.setViewName("param");
		
		return mv;
	}
	
}
