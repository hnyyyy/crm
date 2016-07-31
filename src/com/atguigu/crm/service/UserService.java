package com.atguigu.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.User;
import com.atguigu.crm.mapper.UserMapper;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	
	@Transactional(readOnly=true)
	public User login(String name,String password){
		User user = userMapper.getUserByName(name);
		if(user!=null&&user.getEnabled()==1&&
				user.getPassword().equals(password)){
			return user;
		}
		
		return null;
	}

	public List<User> getAllUser() {
		return userMapper.getAllUser();
	}
	
	@Transactional(readOnly=true)
	public User getByUserName(String name) {
		return userMapper.getUserByName(name);
	}
}
