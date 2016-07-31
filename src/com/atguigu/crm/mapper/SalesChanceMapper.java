package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.User;

public interface SalesChanceMapper {
	
	/**
	 * 有多个查询条件, 则参数可以采取如下的 3 种方式
	 * 1. 使用 @Param 注解进行逐一的映射. 麻烦
	 * 2. 传入一个定制的 bean. 需要额外定制 bean 对象. 
	 * 3. 传入一个 Map
	 */
	long getTotalElements2(Map<String, Object> params);
	
	List<SalesChance> getContent2(Map<String, Object> params);
	
	
	
	
	long getTotalElements(@Param("createBy") User createBy,@Param("status") int status);
	
	List<SalesChance> getContent(@Param("createBy") User createBy,@Param("status") int status,@Param("fromIndex") int fromIndex,@Param("endIndex") int endIndex);

	void save(SalesChance salesChance);

	SalesChance getById(Integer id);

	void update(SalesChance salesChance);

	void delete(Integer id);

	void updateDispatch(SalesChance salesChance);
}
