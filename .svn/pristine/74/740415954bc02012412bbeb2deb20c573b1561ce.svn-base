package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.entity.Customer;

public interface CustomerMapper {

	long getTotalElements(Map<String, Object> mybatisParams);

	List<Customer> getContent(Map<String, Object> mybatisParams);

	List<String> getAllRegion();
	List<String> getAllLevel();
	List<String> getAllSatify();
	List<String> getAllCredit();
	

	Customer getById(Integer id);

	List<Contact> getAllManagers();

	void update(Customer customer);

	void deleteById(Integer id);

	
	
}
