package com.etoak.service;

import java.util.List;

import com.etoak.bean.Car;
import com.etoak.bean.Dict;

public interface CarService {

	/**
	 * 根据groupId查询字典列表
	 * @param groupId
	 * @return
	 */
	List<Dict> queryList(String groupId);
	
	/**
	 * 添加车辆信息
	 * @param car
	 */
	
	int addCar(Car car);
}
