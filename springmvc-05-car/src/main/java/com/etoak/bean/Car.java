package com.etoak.bean;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;				 //主键自增
	@NotNull(message = "brand not empty")
	@NotEmpty(message = "brand not empty")
	private String brand;	//品牌
	
	@NotNull(message = "车系不能为空")
	@NotEmpty(message = "车系不能为空")
	private String series;			 //车系
	
	@NotNull(message = "价格不能为空")
	@Min(value = 1, message = "价格最小为1")
	@Max(value = 100,message = "价格最大是100")
	private Integer price;			 //价格(万元)
	
	private String licensingTime;	 //上牌时间
	private String level;			 //级别
	private String gearbox;			 //变速箱
	private String outputVolume;	 //排量
	private Integer mileage;		 //里程(万公里)
	private String location;		 //归属地
	private String pic;				 //图片地址
	
	@Size(min = 6 , max = 30 , message = "概述只能在6-12个字符之间")
	private String summary;		     //概述
	
	private String createTime;		 //创建时间
	 
}
