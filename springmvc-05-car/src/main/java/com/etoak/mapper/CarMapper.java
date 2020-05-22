package com.etoak.mapper;

import java.util.List;

import com.etoak.bean.Car;
import com.etoak.bean.Dict;

public interface CarMapper {

	/**
	 * 根据groupId查询字典数据表
	 * 
	 * @param groupId
	 * @return
	 */
	List<Dict> queryList(String groupId);
	
	/**
	 * 添加Car
	 * @param car
	 */
	int addCar(Car car);
}
