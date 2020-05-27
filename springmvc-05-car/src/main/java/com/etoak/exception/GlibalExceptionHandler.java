package com.etoak.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常处理器
 * 注解@ControllerAdvice会在所有被Controller注解的方法上加一个拦截
 * 当控制器的方法有异常抛出的时候会被这个异常处理器拦截
 * @author 王贺一
 *
 */
@ControllerAdvice
@Slf4j
public class GlibalExceptionHandler {
	
	@ExceptionHandler(Et1911LoginException.class)
	public ModelAndView handlLoginException(Et1911LoginException e) {
		String msg = e.getMessage();
		log.error("msg",e);
		ModelAndView mv = new ModelAndView();
		mv.addObject("error",msg);
		mv.setViewName("login");
		return mv;
	}
	
	/**
	 * 表示这个方法只拦截ParamException异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ParamException.class)
	public ModelAndView handleParamException(ParamException e) {
		
		//异常信息
		String message = e.getMessage();
		
		log.error(message,e);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("paramErrors",message);
		
		//页面 ：？templates/add.html
		mv.setViewName("add");
		return mv;
		
	}
}
