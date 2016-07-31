package com.atguigu.crm.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.Order;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.CustomerActivityService;
import com.atguigu.crm.service.CustomerService;

@Controller
public class CustomerActivityHandler {
	@Autowired
	private CustomerActivityService customerActivityService;
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/activity/list/{customer.id}")
	public String list(@RequestParam(value="pageNo",required=false) String pageNoStr,
			@PathVariable("customer.id") Integer id,Map<String, Object> map){
		int pageNo=1;
		try {
			pageNo=Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
		Page<Order> page=customerActivityService.getPage(pageNo,id);
		map.put("page", page);
		Customer customer = customerService.getById(id);
		map.put("customer", customer);
		return "activity/list";
	}
}