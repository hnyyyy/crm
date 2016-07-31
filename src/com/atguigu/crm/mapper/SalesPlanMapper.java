package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;



import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.SalesPlan;

public interface SalesPlanMapper {

	long getTotalElements(Map<String, Object> mybatisParams);

	List<SalesPlan> getContent(Map<String, Object> mybatisParams);

	SalesChance getChanceById(Integer id);

}
