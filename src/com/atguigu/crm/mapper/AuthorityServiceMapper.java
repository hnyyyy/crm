package com.atguigu.crm.mapper;

import java.util.List;

import com.atguigu.crm.entity.Authority;

public interface AuthorityServiceMapper {

	List<Authority> getParentAuthorities();

}
