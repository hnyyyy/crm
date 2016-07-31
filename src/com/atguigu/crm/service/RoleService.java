package com.atguigu.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Role;
import com.atguigu.crm.mapper.RoleMapper;
import com.atguigu.crm.orm.Page;

@Service
public class RoleService {
	@Autowired
	private RoleMapper roleMapper;

	public Page<Role> getPage(int pageNo) {
		Page<Role> page=new Page<>();
		page.setPageNo(pageNo);
		
		long totalElements=roleMapper.getTotalElements();
		page.setTotalElements((int)totalElements);
		
		int fromIndex=(page.getPageNo()-1)*page.getPageSize()+1;
		int endIndex=fromIndex+page.getPageSize();
		List<Role> content=roleMapper.getContent(fromIndex,endIndex);
		page.setContent(content);
		return page;
	}
	
	@Transactional
	public void save(Role role) {
		roleMapper.save(role);
	}
	
	@Transactional
	public void delete(long id) {
		roleMapper.deleteByRoleId(id);
		roleMapper.delete(id);
	}
	
	@Transactional(readOnly=true)
	public Role getRoleById(long id) {
		return roleMapper.getRoleById(id);
	}
	
	@Transactional
	public void update(Role role) {
		roleMapper.deleteByRoleId(role.getId());
		roleMapper.update(role);
	}
}
