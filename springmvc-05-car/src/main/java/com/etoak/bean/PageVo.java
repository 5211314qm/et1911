package com.etoak.bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVo<T> {

	//页码，当前页
	private int pageNum;
	
	//每页显示记录数
	private int pageSize;
	
	//记录
	private List<T> rows;
	
	//总记录数
	private long total;
	
	//总页数
	private int pageCount;
	
	//是否第一页
	private boolean first;
	
	//是否是最后一页
	private boolean last;
	
}
