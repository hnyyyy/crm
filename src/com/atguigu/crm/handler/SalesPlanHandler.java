package com.atguigu.crm.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.SalesPlan;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.orm.Utils;
import com.atguigu.crm.service.SalesPlanService;


@Controller
public class SalesPlanHandler {
	@Autowired
	private SalesPlanService salesPlanService;
	
	@RequestMapping("/plan/make/{chance.id}")
	public String make(@PathVariable("chance.id") Integer id,Map<String,Object> map){
		SalesChance chance=salesPlanService.getChanceById(id);
		map.put("chance", chance);
		return "plan/make";
	}
	
	@RequestMapping(value="/plan/list")
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
		
		User createBy=(User) request.getSession().getAttribute("user");
		params.put("EQO_createBy", createBy);
		params.put("EQI_status", 2);
		Page<SalesPlan> page=salesPlanService.getPage(pageNo,params);
		map.put("page", page);
		return "plan/list";
	}
	
	
}
