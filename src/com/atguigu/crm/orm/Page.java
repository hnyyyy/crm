package com.atguigu.crm.orm;

import java.util.List;

public class Page<T> {
	//当前页数
	private int pageNo;
	//每页显示的记录数
	private int pageSize=3;
	//总记录数
	private int totalElements;
	private List<T> content;
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		if(pageNo<1){
			pageNo=1;
		}
		this.pageNo=pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
		//校验pageNo的合法性
		if(this.pageNo>getTotalPages()){
			this.pageNo=getTotalPages();
		}
	}
	public List<T> getContent() {
		return content;
	}
	public void setContent(List<T> content) {
		this.content = content;
	}
	
	//获取总的页数
	public int getTotalPages(){
		int totalPages=0;
		totalPages=this.totalElements/this.pageSize;
		if(this.totalElements%this.pageSize!=0){
			totalPages++;
		}
		return totalPages;
	}
	
	//判断是否含有下一页
	public boolean isHasNextPage(){
		return this.pageNo<getTotalPages();
	}
	
	//动态获得下一页页数
	public int getNextPage(){
		if(isHasNextPage()){
			return this.pageNo+1;
		}
		return this.pageNo;
	}
	
	//判断是否含有上一页
	public boolean isHasPrevPage(){
		return this.pageNo>1;
	}
	
	//动态获得上一页页码
	public int getPrevPage(){
		if(isHasPrevPage()){
			return this.pageNo-1;
		}
		return this.pageNo;
	}
	
}
