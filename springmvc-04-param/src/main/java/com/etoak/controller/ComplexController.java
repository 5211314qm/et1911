package com.etoak.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etoak.bean.Student;

/**
 * 测试java bean接收参数
 * 测试数组接收参数
 * 测试List接收参数
 * 测试Map接收参数
 * 
 * @author 王贺一
 *
 */
@Controller
@RequestMapping("/complex")
public class ComplexController {
	
	//Get请求：@RequestMappingmethod属性定义
	//Get请求：@GetMapping
	
	@GetMapping("/bean")
	public String bean(Student student,Model model) {
		
		System.out.println(student);
		
		model.addAttribute("result2", "使用Model传值");
		
		//请求转发到、simple/simple(这个请求在SimpleController里)
		return "forward:/simple/simple?id=321";
	}
	
	/**
	 * 测试数组接收参数
	 * 使用ModelMap向request域传参
	 * 
	 * @param hobby
	 * @param modelMap
	 * @return
	 */
	@PostMapping("/array")
	public String array(String[] hobby,ModelMap modelMap) {
		for (String hobbyStr : hobby) {
			System.out.println("hobby - " + hobbyStr);
		}
		
		modelMap.addAttribute("result","使用ModelMap传参");
		return "param";
	}
	
	/**
	 * 第五中接收参数的方式：使用List（封装到java bean中）
	 * 第五种想request域传值的方式
	 * 
	 * @param student
	 * @param map
	 * @return
	 */
	@PostMapping("/list")
	public String list(Student student,Map<String,Object> map) {
		
		List<String> hobbyList = student.getHobbyList();
		if(!CollectionUtils.isEmpty(hobbyList)) {
			hobbyList.forEach(x -> System.out.println("hobbyList - " + x));
		}
		
		map.put("result","使用Map传参");
		return "param";
	}
	
	/**
	 * 使用Map传参方式（Map封装在java bean中）
	 * 
	 * @param student
	 * @return
	 */
	
	@PostMapping(value="/map" ,produces= {"text/plain"})
	@ResponseBody	//可以返回json、xml、纯文本
	public String map(Student student) {
		
		System.out.println(student.getStuMap());
	
		return "success";
	}
	
}
