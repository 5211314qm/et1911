package com.etoak.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.etoak.bean.Car;
import com.etoak.bean.CarVo;
import com.etoak.bean.PageVo;
import com.etoak.exception.ParamException;
import com.etoak.page.Page;
import com.etoak.service.CarService;
import com.github.pagehelper.PageInfo;

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
	
	//同步的方式添加
    @PostMapping("/add")
    public String add(@RequestParam("file") MultipartFile file ,@Valid Car car,
    		BindingResult bindingResult,HttpServletRequest request) {//多个文件就使用MultipartFile[]
        
    	//得到上传的文件名称
        String fileName = file.getOriginalFilename();
        
        log.info("文件名称 - {}",file.getOriginalFilename());
        log.info("param car - {}",car);
        
        //先校验Car
        //获取所有检验结果
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        
        //如果校验失败结果集不为空，则去除错误结果集
        if(!CollectionUtils.isEmpty(allErrors)) {
        	StringBuffer errorBuf = new StringBuffer();
        	for(ObjectError error : allErrors) {
        		String errorMessage = error.getDefaultMessage();
        		errorBuf.append(errorMessage).append(";");
        	}
        	//使用请求转发回到车辆添加页面
        	// request.setAttribute("paramErrors", errorBuf.toString());
        	// return "forward:/car/toAdd";
        	throw new ParamException(errorBuf.toString());
        }
        
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/car/toAdd";
    }
    
    @RequestMapping("toList")
    public String toList() {
    	return "list";
    }
    
    /**
     * 查询所有品牌
     */
    @PostMapping("/queryBrand")
    @ResponseBody
    public List<String> queryBrand(){
    	return carService.queryBrand();
    }
    
    /**
     * 根据品牌查询系列
     */
    @PostMapping("/querySeries")
    @ResponseBody
    public List<String> querySeries(@RequestParam(required = false,defaultValue = "") String brand){
    	return carService.querySeries(brand);
    }
    
    /**
     * 根据条件查询汽车
     */
    @PostMapping("/queryCar")
    @ResponseBody
    public Page<CarVo>queryCar(
    		@RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "8") int pageSize,
            CarVo carVo,
            String priceList){

    	Page<CarVo> page = new Page<>();
    	
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        
        List<CarVo> rows = carService.queryCar(pageNumber, pageSize,carVo,priceList);
        
        PageInfo<CarVo> info = new PageInfo<CarVo>(rows);
        
        page.setTotal(((Long)info.getTotal()).intValue());
        page.setRows(info.getList());
        
        return page;
    }
    
    @RequestMapping("toListCar")
    public String toListCar() {
    	return "listCar";
    }
    
    /**
     * 车辆列表查询
     * @param pageNum
     * @param pageSize
     * @param carVo
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public PageVo<CarVo> queryList(
    		@RequestParam(required = false,defaultValue = "1") int pageNumber,
    		@RequestParam(required = false,defaultValue = "8") int pageSize,
    		CarVo carVo,
    		String[] priceList ){
    	
    	log.info("param - {}",carVo);
    	
    	return carService.queryList(pageNumber, pageSize, carVo,priceList);
    }
    
    /**
     * 查询所有品牌
     * @return
     */
    @GetMapping("/getBrand")
    @ResponseBody
    public List<String> getBrand(){
    	return carService.getAllBrand();
    }
    
    /**
     * 根据品牌查询车系，如果品牌为空，则随机获取10条
     * @param brand
     * @return
     */
    @GetMapping("/getSeries")
    @ResponseBody
    public List<String> getSeries(String brand){
    	return carService.getSeriesByBrand(brand);
    }
   
}
