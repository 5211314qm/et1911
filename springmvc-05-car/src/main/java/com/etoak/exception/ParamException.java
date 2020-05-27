package com.etoak.exception;


/**
 * 自定义异常
 * @author 王贺一
 *
 */
public class ParamException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ParamException(String message) {
		super(message);
	}
	
}
