package com.etoak.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 字典表对应实体类
 * 
 * @author 王贺一
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dict implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String groupId;
	
	private String name;
	
	private String value;
	
	private Integer sort;
}
