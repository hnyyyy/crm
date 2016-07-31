package com.atguigu.crm.handler;

import java.util.List;
import java.util.Map;

import org.eclipse.jdt.internal.compiler.ast.FalseLiteral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.crm.entity.Order;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.OrderService;

@Controller
public class OrderHandler {
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/order/list/{customer.id}")
	public String list(@RequestParam(value="pageNo",required=false) String pageNoStr,
			@PathVariable("customer.id") Integer id,Map<String, Object> map){
		int pageNo=1;
		try {
			pageNo=Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
		Page<Order> page=orderService.getPage(pageNo,id);
		map.put("page", page);
		return "order/list";
	}
}
