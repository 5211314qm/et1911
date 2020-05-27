package com.etoak.bean;


import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarVo extends Car{
	
	private static final long serialVersionUID = 1L;
	
	//级别名称
	private String levelName;
	
	//变速箱名称
	private String gearboxName;
	
	//排量名称
	private String outputVolumeName;
	
	@JsonIgnore	//	spring mvc 在返回结果中不封装使用JsonIgnore的数据
	private List<Map<String,Integer>> priceMapList;
	
	
	//车龄 0-1 或1-3
	@JsonIgnore
	private String vehicleAge;
	
	@JsonIgnore
	private Integer startYear;
	
	@JsonIgnore
	private Integer endYear;
	
	/*
	 * private Integer priceStart; private Integer priceEnd;
	 * 
	 * private String dateStart; private String dateEnd;
	 */
    
}
