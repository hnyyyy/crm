package com.atguigu.crm.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.mapper.CustomerMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.orm.PropertyFilter;
import com.atguigu.crm.orm.Utils;

@Service
public class CustomerService {
	@Autowired
	private CustomerMapper customerMapper;
	
	@Transactional
	public Page<Customer> getPage(int pageNo, Map<String, Object> params) {
		Page<Customer> page=new Page<>();
		page.setPageNo(pageNo);
		
		//1,获取总的记录数
		//①把传入的params转为PropertyFilter的集合
		List<PropertyFilter> filters = Utils.parseHandlerParamsToPropertyFilters(params);
		//1.2 把 RropertyFilter 的集合转为 mybatis 可用的 params
		Map<String, Object> mybatisParams = Utils.parsePropertyFiltersToMyBatisParmas(filters);
		
		long totalElements = customerMapper.getTotalElements(mybatisParams);
		page.setTotalElements((int) totalElements);
		
		//2.获取当前页面的List
		int fromIndex=(page.getPageNo()-1)*page.getPageSize()+1;
		int endIndex=page.getPageSize()+fromIndex;
		mybatisParams.put("fromIndex", fromIndex);
		mybatisParams.put("endIndex", endIndex);
		
		List<Customer> content = customerMapper.getContent(mybatisParams);
		page.setContent(content);

		return page;
	}
	
	static{
		DateConverter dateConverter = new DateConverter();
		dateConverter.setPatterns(new String[]{"yyyy-MM-dd","yyyy-MM-dd hh:mm:ss"});
		ConvertUtils.register(dateConverter, Date.class);
	}
	
	@Transactional(readOnly=true)
	public List<String> getAllRegion() {
		return customerMapper.getAllRegion();
	}
	
	@Transactional(readOnly=true)
	public List<String> getAllLevel() {
		return customerMapper.getAllLevel();
	}
	
	@Transactional(readOnly=true)
	public List<String> getAllSatify() {
		return customerMapper.getAllSatify();
	}
	
	@Transactional(readOnly=true)
	public List<String> getAllCredit() {
		return customerMapper.getAllCredit();
	}
	
	@Transactional(readOnly=true)
	public Customer getById(Integer id) {
		
		return customerMapper.getById(id);
	}
	
	@Transactional(readOnly=true)
	public List<Contact> getAllManagers() {
		return customerMapper.getAllManagers();
	}

	@Transactional
	public void update(Customer customer) {
		customerMapper.update(customer);
	}

	@Transactional
	public void deleteById(Integer id) {
		customerMapper.deleteById(id);
	}

}
