package com.etoak.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etoak.bean.Car;
import com.etoak.bean.Dict;
import com.etoak.mapper.CarMapper;
import com.etoak.service.CarService;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	CarMapper carMapper;
	
	@Override
	public List<Dict> queryList(String groupId) {
		return carMapper.queryList(groupId);
	}

	@Override
	public int addCar(Car car) {
		return carMapper.addCar(car);
	}

}
