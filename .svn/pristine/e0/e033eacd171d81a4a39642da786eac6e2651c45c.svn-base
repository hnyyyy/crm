package com.atguigu.crm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.Role;

public interface RoleMapper {

	long getTotalElements();

	List<Role> getContent(@Param("fromIndex") int fromIndex, @Param("endIndex") int endIndex);

	void save(Role role);

	void delete(long id);

	Role getRoleById(long id);

	void update(Role role);

	void deleteByRoleId(Long id);

}
