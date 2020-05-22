package com.etoak.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.etoak.bean.Car;
import com.etoak.bean.Dict;
import com.etoak.service.CarService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/car")
@Slf4j
public class CarController {
	
	@Autowired
	CarService carService;
	
	@RequestMapping("/toAdd")
	public String toAdd() {
		return "add";
	}
	
	@RequestMapping("/queryGroupId")
	@ResponseBody
	public List<Dict> queryList(String groupId){
		return carService.queryList(groupId);
	}
	
	//同步的方式添加
    @PostMapping("/add")
    public String add(@RequestParam("file") MultipartFile file ,Car car){//多个文件就使用MultipartFile[]
        
    	//得到上传的文件名称
        String fileName = file.getOriginalFilename();
        
        log.info("文件名称 - {}",file.getOriginalFilename());
        log.info("param car - {}",car);
        
        //获取上传文件的后缀
        String suffix = FilenameUtils.getExtension(fileName);
        
        //随机生成32的新文件名称
        String prefix = UUID.randomUUID().toString().replaceAll("-","");
        
        //新的文件名称 也可以是uuid_fileName
        String newFileName = prefix + "." + suffix;
        
        //创建目标文件
        File destFile = new File("f:/foto",newFileName);
     
        try {
        	//文件上传
            file.transferTo(destFile);
            
            //设置图片地址
            car.setPic("/pic/" + newFileName);
            
            //添加进数据库
            carService.addCar(car);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/car/toAdd";
    }
   
}
