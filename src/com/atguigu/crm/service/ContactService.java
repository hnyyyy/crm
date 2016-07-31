package com.atguigu.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crm.mapper.ContactMapper;

@Service
public class ContactService {
	@Autowired
	private ContactMapper contactMapper;
}
