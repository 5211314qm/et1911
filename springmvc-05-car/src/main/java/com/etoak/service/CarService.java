package com.etoak.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.etoak.bean.Car;
import com.etoak.bean.CarVo;
import com.etoak.bean.PageVo;

public interface CarService {

	/**
	 * 添加车辆信息
	 * @param car
	 */
	
	int addCar(Car car);
	
	/**
	 * 查询所有品牌
	 * @return
	 */
	List<String> queryBrand();
	
	/**
	 * 查询所有系列
	 * @return
	 */
	List<String> querySeries(String brand);

	List<CarVo> queryCar(int pageNumber, int pageSize, CarVo carVo, String priceList);
	
	PageVo<CarVo> queryList(int pageNumber, int pageSize, CarVo carVo, String[] priceList);
	
	/**
	 * 获取所有品牌
	 * @return
	 */
	List<String> getAllBrand();
	
	/**
	 * 根据品牌查询车系
	 * @param brand
	 * @return
	 */
	List<String> getSeriesByBrand(@Param("brand") String brand);
}
