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
import com.atguigu.crm.mapper.ProductMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.orm.PropertyFilter;
import com.atguigu.crm.orm.Utils;

@Service
public class ProductService {
	@Autowired
	private ProductMapper productMapper;
	
	@Transactional(readOnly=true)
	public Page<Product> getPage(int pageNo, Map<String, Object> params) {
		Page<Product> page=new Page<>();
		page.setPageNo(pageNo);
		List<PropertyFilter> propertyFilters = Utils.parseHandlerParamsToPropertyFilters(params);
		Map<String, Object> mybatisParams = Utils.parsePropertyFiltersToMyBatisParmas(propertyFilters);
		long totalElements=productMapper.getTotalElements(mybatisParams);
		page.setTotalElements((int)totalElements);
		
		int fromIndex=(page.getPageNo()-1)*page.getPageSize()+1;
		int endIndex=page.getPageSize()+fromIndex;
		mybatisParams.put("fromIndex", fromIndex);
		mybatisParams.put("endIndex", endIndex);
		List<Product> content= productMapper.getContent(mybatisParams);
		page.setContent(content);
		return page;
	}
	
	static{
		DateConverter dateConverter = new DateConverter();
		dateConverter.setPatterns(new String[]{"yyyy-MM-dd","yyyy-MM-dd hh:mm:ss"});
		ConvertUtils.register(dateConverter, Date.class);
	}

	@Transactional
	public void save(Product product) {
		productMapper.save(product);
	}
	
	@Transactional(readOnly=true)
	public List<Product> getAllProduct() {
		return productMapper.getAllProduct();
	}
	
	@Transactional(readOnly=true)
	public Product getProductById(Integer id) {
		return productMapper.getProductById(id);
	}
	
	@Transactional
	public void update(Product product) {
		productMapper.update(product);
	}
	
	@Transactional
	public void delete(Long id) {
		productMapper.deleteByProductId(id);
		productMapper.delete(id);	
	}
}
