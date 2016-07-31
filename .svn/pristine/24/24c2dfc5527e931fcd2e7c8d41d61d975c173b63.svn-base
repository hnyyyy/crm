package com.atguigu.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Authority;
import com.atguigu.crm.mapper.AuthorityServiceMapper;

@Service
public class AuthorityService {
	@Autowired
	private AuthorityServiceMapper authorityServiceMapper;
	
	@Transactional(readOnly=true)
	public List<Authority> getParentAuthorities() {
		return authorityServiceMapper.getParentAuthorities();
	}
}
