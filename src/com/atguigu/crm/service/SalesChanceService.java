package com.atguigu.crm.service;

import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.mapper.SalesChanceMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.orm.PropertyFilter;
import com.atguigu.crm.orm.PropertyFilter.MatchType;
import com.atguigu.crm.orm.Utils;

@Service
public class SalesChanceService {
	@Autowired
	private SalesChanceMapper salesChanceMapper;
	
	@Transactional(readOnly=true)
	public Page<SalesChance> getPage(int pageNo, User createBy, int status){
		Page<SalesChance> page = new Page<>();
		page.setPageNo(pageNo);
		
		//1. 获取总的记录数
		long totalElements = salesChanceMapper.getTotalElements(createBy, status);
		page.setTotalElements((int)totalElements);
		
		//2. 获取当前页面的 List
		int fromIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = page.getPageSize() + fromIndex;
		List<SalesChance> content = salesChanceMapper.getContent(createBy, status, fromIndex, endIndex);
		page.setContent(content);
		
		//3. 组装 Page 并返回
		return page;
	}

	@Transactional
	public void save(SalesChance salesChance) {
		salesChanceMapper.save(salesChance);
	}
	@Transactional(readOnly=true)
	public SalesChance getById(Integer id) {
		return salesChanceMapper.getById(id);
	}
	
	@Transactional
	public void update(SalesChance salesChance) {
		 salesChanceMapper.update(salesChance);
	}
	@Transactional
	public void delete(Integer id) {
		salesChanceMapper.delete(id);
	}
	
	/**
	 * 从 Handler 中传入的 params 能否直接传给 Mapper 方法呢 ?
	 * 1. 表单传入的一定是一个字符串. 例如: 1990-1-1. 而目标的属性类型为 Date 类型. 则字符串不能直接使用. 而需要进行类型的转换.
	 * 2. 若进行的是 LIKE 比较. 则需要把 value 值的前后加上 %%.
	 * 
	 * 所以传入的参数名中必须包含额外的信息: 比较的方式, 和目标属性的类型. 
	 * <input type="text" name="search_LIKES_contact" />
	 * 
	 * search_ 为前缀. 可以在批量获取参数时使用.
	 * contact 属性名.
	 * LIKES 可以在分为两部分. LIKE 为比较方式。 S 为目标属性的类型. 
	 * 
	 * 所以可以对查询属性进行进一步的封装. 
	 * class PropertyFilter{
	 * 	private String propertyName; //contact
	 * 	private Oject propertyVal; //属性值. 由用户输入
	 * 	
	 * 	private class propertyType; //属性的类型. 有 S 来标记
	 * 	private MatchType matchType; //比较方式.
	 * 
	 * 	public enum MatchType{
	 * 		LIKE, EQ, GT, GE, LT, LE;
	 * 	}
	 * 
	 * 	public enum PropertyType{
	 * 		S(String.class), I(Integer.class), B(Boolean.class), F(Float.class), D(Date.class);
	 * 
	 * 		private Class propertyType; 
	 * 
	 * 		private PropertyType(Class propertyType){
	 * 			this.propertyType =  propertyType;
	 * 		}
	 * 
	 * 		public Class getPropertyType(){
	 * 			return this.propertyType;
	 * 		}
	 * 	}
	 * 
	 *  //getter, setter. 
	 * }
	 * 
	 * 3. 先把由 handler 传入的 params 转为 PropertyFilter 的集合. 再把该集合转为 mybatis 可用的 params. 
	 */
	@Transactional(readOnly=true)
	public Page<SalesChance> getPage(int pageNo, Map<String, Object> params) {
		Page<SalesChance> page=new Page<>();
		page.setPageNo(pageNo);
		
		//1,获取总的记录数
		//①把传入的params转为PropertyFilter的集合
		List<PropertyFilter> filters = Utils.parseHandlerParamsToPropertyFilters(params);
		//1.2 把 RropertyFilter 的集合转为 mybatis 可用的 params
		Map<String, Object> mybatisParams = Utils.parsePropertyFiltersToMyBatisParmas(filters);
		
		long totalElements = salesChanceMapper.getTotalElements2(mybatisParams);
		page.setTotalElements((int) totalElements);
		
		//2.获取当前页面的List
		int fromIndex=(page.getPageNo()-1)*page.getPageSize()+1;
		int endIndex=page.getPageSize()+fromIndex;
		mybatisParams.put("fromIndex", fromIndex);
		mybatisParams.put("endIndex", endIndex);
		
		List<SalesChance> content = salesChanceMapper.getContent2(mybatisParams);
		page.setContent(content);

		return page;
	}
	
	static{
		DateConverter dateConverter = new DateConverter();
		dateConverter.setPatterns(new String[]{"yyyy-MM-dd","yyyy-MM-dd hh:mm:ss"});
		ConvertUtils.register(dateConverter, Date.class);
	}
	

	
	@Transactional
	public void updateDispatch(SalesChance salesChance) {
		salesChanceMapper.updateDispatch(salesChance);
		
	}

	
}
