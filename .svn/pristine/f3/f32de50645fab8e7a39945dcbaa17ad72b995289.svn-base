package com.atguigu.crm.service;

import java.sql.Date;
import java.util.List;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Order;
import com.atguigu.crm.mapper.CustomerActivityMapper;
import com.atguigu.crm.orm.Page;


@Service
public class CustomerActivityService {
	@Autowired
	private CustomerActivityMapper customerActivityMapper;

	@Transactional(readOnly=true)
	public Page<Order> getPage(int pageNo, Integer id) {
		Page<Order> page=new Page<>();
		page.setPageNo(pageNo);
		long totalElements=customerActivityMapper.getTotalElements(id);
		page.setTotalElements((int)totalElements);
		
		int fromIndex=(page.getPageNo()-1)*page.getPageSize()+1;
		int endIndex=fromIndex+page.getPageSize();
		List<Order> content=customerActivityMapper.getAllActivityByCustomerId(id,fromIndex,endIndex);
		page.setContent(content);
		return page;
	}
	
	static{
		DateConverter dateConverter = new DateConverter();
		dateConverter.setPatterns(new String[]{"yyyy-MM-dd","yyyy-MM-dd hh:mm:ss"});
		ConvertUtils.register(dateConverter, Date.class);
	}
}
