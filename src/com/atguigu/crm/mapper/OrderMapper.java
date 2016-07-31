package com.atguigu.crm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.Order;

public interface OrderMapper {

	

	List<Order> getAllOrderByCustomerId(@Param("id") Integer id, @Param("fromIndex") int fromIndex, @Param("endIndex") int endIndex);

	long getTotalElements(Integer id);


}
