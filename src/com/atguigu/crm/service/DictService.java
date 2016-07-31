package com.atguigu.crm.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Dict;
import com.atguigu.crm.mapper.DictMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.orm.PropertyFilter;
import com.atguigu.crm.orm.Utils;

@Service
public class DictService {
	@Autowired
	private DictMapper dictMapper;

	@Transactional(readOnly=true)
	public Page<Dict> getPage(int pageNo, Map<String, Object> params) {
		Page<Dict> page=new Page<>();
		page.setPageNo(pageNo);
		
		//1,获取总的记录数
		//①把传入的params转为PropertyFilter的集合
		List<PropertyFilter> filters = Utils.parseHandlerParamsToPropertyFilters(params);
		//1.2 把 RropertyFilter 的集合转为 mybatis 可用的 params
		Map<String, Object> mybatisParams = Utils.parsePropertyFiltersToMyBatisParmas(filters);
		
		long totalElements = dictMapper.getTotalElements(mybatisParams);
		page.setTotalElements((int) totalElements);
		
		//2.获取当前页面的List
		int fromIndex=(page.getPageNo()-1)*page.getPageSize()+1;
		int endIndex=page.getPageSize()+fromIndex;
		mybatisParams.put("fromIndex", fromIndex);
		mybatisParams.put("endIndex", endIndex);
		
		List<Dict> content = dictMapper.getContent(mybatisParams);
		page.setContent(content);

		return page;
	}
	
	static{
		DateConverter dateConverter = new DateConverter();
		dateConverter.setPatterns(new String[]{"yyyy-MM-dd","yyyy-MM-dd hh:mm:ss"});
		ConvertUtils.register(dateConverter, Date.class);
	}
	
	@Transactional
	public void save(Dict dict) {
		dictMapper.save(dict);
	}
	
	@Transactional(readOnly=true)
	public Dict getDictById(Integer id) {
		return dictMapper.getDictById(id);
	}
	
	@Transactional
	public void update(Dict dict) {
		dictMapper.update(dict);
	}
	
	@Transactional
	public void delete(Long id) {
		dictMapper.delete(id);
	}
}
