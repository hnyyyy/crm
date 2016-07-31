package com.atguigu.crm.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Product;
import com.atguigu.crm.entity.Storage;
import com.atguigu.crm.mapper.StorageMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.orm.PropertyFilter;
import com.atguigu.crm.orm.Utils;

@Service
public class StorageService {
	@Autowired
	private StorageMapper storageMapper;
	
	@Transactional(readOnly=true)
	public Page<Storage> getPage(int pageNo, Map<String, Object> params) {
		Page<Storage> page=new Page<>();
		page.setPageNo(pageNo);
		List<PropertyFilter> propertyFilters = Utils.parseHandlerParamsToPropertyFilters(params);
		Map<String, Object> mybatisParams = Utils.parsePropertyFiltersToMyBatisParmas(propertyFilters);
		long totalElements=storageMapper.getTotalElements(mybatisParams);
		page.setTotalElements((int)totalElements);
		
		int fromIndex=(page.getPageNo()-1)*page.getPageSize()+1;
		int endIndex=page.getPageSize()+fromIndex;
		mybatisParams.put("fromIndex", fromIndex);
		mybatisParams.put("endIndex", endIndex);
		List<Storage> content= storageMapper.getContent(mybatisParams);
		page.setContent(content);
		return page;
	}
	
	static{
		DateConverter dateConverter = new DateConverter();
		dateConverter.setPatterns(new String[]{"yyyy-MM-dd","yyyy-MM-dd hh:mm:ss"});
		ConvertUtils.register(dateConverter, Date.class);
	}
	
	@Transactional
	public void save(Storage storage) {
		storageMapper.save(storage);
	}
	
	@Transactional(readOnly=true)
	public Storage getStorageById(Integer id) {
		return storageMapper.getStorageById(id);
	}
	
	@Transactional
	public void update(Storage storage) {
		storageMapper.update(storage);
	}

	@Transactional
	public void delete(Long id) {
		storageMapper.delete(id);
	}
}
