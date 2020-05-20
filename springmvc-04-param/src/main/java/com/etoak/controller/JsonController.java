package com.etoak.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etoak.bean.Student;
import com.etoak.bean.User;
import com.etoak.vo.ResultVo;

@RestController //@Controller + @ResponBody(会应用到所有方法上)
@RequestMapping("/json")
public class JsonController {

	@PostMapping(value="/jsonToMap",produces = "application/json;charset=UTF-8")
	public Object jsonToMap(@RequestBody Map<String,Object> jsonMap) { //@RequestBody可以把json数据映射到Map
		System.out.println(jsonMap);
		
		return "{\"msg\":\"success\"}";
	}
	
	/**
	 * 将JSON转成List
	 * @param userList
	 * @return
	 */
	@PostMapping("/jsonToList")
	public Map<String,Object> jsonToList(@RequestBody List<User> userList){
		
		userList.forEach(x -> System.out.println(x));
		
		//封装结果，spring mvc可以自动将map转成json
		Map<String,Object> resultMap = new HashMap<>();
		
		resultMap.put("code",200);
		resultMap.put("msg", "success");
		
		return resultMap;
	}
	
	@PostMapping("/jsonToBean")
	public ResultVo jsonToBean(@RequestBody Student student) {
		
		System.out.println(student);
		
		return new ResultVo(200,"success");
	}
}
