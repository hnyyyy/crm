package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import com.atguigu.crm.entity.Dict;

public interface DictMapper {

	long getTotalElements(Map<String, Object> mybatisParams);

	List<Dict> getContent(Map<String, Object> mybatisParams);

	void save(Dict dict);

	Dict getDictById(Integer id);

	void update(Dict dict);

	void delete(Long id);

	

}
