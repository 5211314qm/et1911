package com.etoak.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.etoak.bean.Car;
import com.etoak.bean.CarVo;

public interface CarMapper {

	
	/**
	 * 添加Car
	 * @param car
	 */
	int addCar(Car car);
	
	/**
	 * 查询所有品牌
	 * @return
	 */
	List<String> queryBrand();
	
	/**
	 * 根据品牌查询系列
	 * @param brand
	 * @return
	 */
	List<String> querySeries(String brand);

	/**
	 * 根据封装的carVo来查询汽车
	 * @param carVo
	 * @return
	 */
	List<CarVo> queryCar(CarVo carVo);
	
	List<CarVo> queryList(CarVo carVo);
	
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
	
	List<String> getPrice();
	
}
