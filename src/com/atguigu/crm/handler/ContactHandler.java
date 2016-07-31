package com.atguigu.crm.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.crm.service.ContactService;

@Controller
public class ContactHandler {
	@Autowired
	private ContactService contactService;
	
	@RequestMapping("/contact/list/{customer.id}")
	public String list(@PathVariable("customer.id") Integer id,
						Map<String, Object> map){
		
		return "contact/list";
	}
}
