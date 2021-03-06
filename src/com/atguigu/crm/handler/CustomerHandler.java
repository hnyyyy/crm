package com.atguigu.crm.handler;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.orm.Utils;
import com.atguigu.crm.service.CustomerService;

@Controller
public class CustomerHandler {
	@Autowired
	private CustomerService customerService;
	
	@ResponseBody
	@RequestMapping(value="/customer/delete/{customerId}")
	public String delete(@PathVariable("customerId") Integer id,RedirectAttributes attributes){
		customerService.deleteById(id);
		return "1";
	}
	
	@RequestMapping(value="/customer/update/{customer.id}",method=RequestMethod.PUT)
	public String update(@PathVariable("customer.id") Long id,
						Customer customer,RedirectAttributes attributes){
		customer.setId(id);
		customerService.update(customer);
		attributes.addFlashAttribute("message", "修改成功");
		return "redirect:/customer/list";
	}
	
	@RequestMapping(value="/customer/edit/{customer.id}",method=RequestMethod.GET)
	public String edit(@PathVariable("customer.id") Integer id,Map<String,Object> map){
		map.put("customer", customerService.getById(id));
		map.put("regions", customerService.getAllRegion());
		map.put("levels", customerService.getAllLevel());
		map.put("satisfies", customerService.getAllSatify());
		map.put("credits", customerService.getAllCredit());
		map.put("managers", customerService.getAllManagers());
		return "customer/input";
	}
	
	
	@RequestMapping("/customer/list")
	public String list(@RequestParam(value="pageNo",required=false) String pageNoStr,
			Map<String, Object> map,
			HttpServletRequest request){
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		String queryString = Utils.encodeParamsToQueryString(params);
		map.put("queryString", queryString);
		int pageNo=1;
		try {
			pageNo=Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
	
		/*List<String> regions = customerService.getAllRegion();
		map.put("regions", regions);*/
		
		Page<Customer> page=customerService.getPage(pageNo,params);
		map.put("page", page);
		map.put("regions", customerService.getAllRegion());
		map.put("levels", customerService.getAllLevel());
		return "customer/list";
	}
}
