package com.atguigu.crm.handler;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.orm.Utils;
import com.atguigu.crm.service.SalesChanceService;
import com.atguigu.crm.service.UserService;

@Controller
public class SalesChanceHandler {
	@Autowired
	private SalesChanceService salesChanceService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/chance/dispatch/{id}",method=RequestMethod.PUT)
	public String upDateDispatch(@PathVariable("id") Long id,
			SalesChance salesChance,
			RedirectAttributes attributes){
		salesChance.setId(id);
		salesChance.setStatus(2);
		salesChanceService.updateDispatch(salesChance);
		attributes.addFlashAttribute("message","修改成功");
		
		return "redirect:/chance/list";
	}
	
	
	@RequestMapping(value="/chance/dispatch/{id}",method=RequestMethod.GET)
	public String dispatch(@PathVariable("id") Integer id,Map<String,Object> map){
		map.put("chance", salesChanceService.getById(id));
		List<User> users=userService.getAllUser();
		map.put("users", users);
		return "chance/dispatch";
	}
	
	/**
	 * 带查询条件的分页
	 * 1. 如何在 handler 的方法中获取查询条件对应的参数 ?
	 * ①. 使用 @RequestParam 注解进行逐一的映射. 若参数较多则, 比较麻烦.
	 * ②. 若能批量的获取 "查询条件对应的参数(而不包含其他的参数)" : 
	 * 可以调用 WebUtils.getParametersStartingWith(request, prefix) 来获取指定前缀的请求参数的 Map. 
	 * 且该  Map 是去除了前缀的. 
	 * 
	 * 2. 点击 "翻页链接" 时, 如何能携带查询条件 ? 
	 * 1). 把查询条件放在 HttpSession 对象中. 最好是不这么做. 原则为: 能放在较小域对象中的 key-value, 就不放在较大
	 * 的域对象中. 
	 * 2). 把 1 得到的请求参数的 Map, 在序列化为一个查询字符串:
	 * {"LIKES_custName":"a","LIKES_title":"b","LIKES_contact":"c"} -->
	 * &search_LIKES_custName=a&search_LIKES_title=b&search_LIKES_contact=c
	 * 再把改查询字符串传回到页面上, 在点击 "翻页链接" 时, 携带该查询字符串, 则既可实现携带查询条件. 
	 * 
	 * 3. 关于中文乱码的问题:
	 * 1). request.setCharacterEncoding 只适用于 POST 	请求. 而不适用于 GET 请求
	 * 2). 在使用 tomcat 服务器时, 若希望 request.setCharacterEncoding 也适用于 GET 请求, 则需要在
	 * tomcat/conf/server.xml 文件中加入如下配置: useBodyEncodingForURI=true
	 * <Connector connectionTimeout="20000" port="8080" protocol="HTTP/1.1" redirectPort="8443" useBodyEncodingForURI="true"/>
	 * 
	 * 4. 再调用 Service 方法时, 传入 params 对应的 Map.
	 * 5. 把已知的查询条件集成到 params 对应的 Map 中. 
	 */
	@RequestMapping(value="/chance/list")
	public String list2(@RequestParam(value="pageNo",required=false) String pageNoStr,
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
		params.put("EQI_status", 1);
		Page<SalesChance> page=salesChanceService.getPage(pageNo,params);
		map.put("page", page);
		return "chance/list";
	}
	
	

	@RequestMapping(value="/chance/delete/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id,
			RedirectAttributes attributes,
			@RequestParam(value="pageNo",required=false,defaultValue="1") Integer pageNo){
		salesChanceService.delete(id);
		attributes.addFlashAttribute("message","删除成功");
		return "redirect:/chance/list/?pageNo="+pageNo;
	}
	
	@RequestMapping(value="/chance/create/{id}",method=RequestMethod.PUT)
	public String update(@PathVariable("id") Long id,
			SalesChance salesChance,
			RedirectAttributes attributes,
			@RequestParam(value="pageNo", required=false, defaultValue="1") Integer pageNo){
		salesChance.setId(id);
		salesChanceService.update(salesChance);
		attributes.addFlashAttribute("message","修改成功");
		return "redirect:/chance/list?pageNo="+pageNo;
	}
	
	@RequestMapping(value="/chance/create/{id}",method=RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id,Map<String,Object> map){
		map.put("chance", salesChanceService.getById(id));
		return "chance/input";
	}
	
	@RequestMapping(value="/chance/create",method=RequestMethod.POST)
	public String save(SalesChance salesChance,RedirectAttributes attributes){
		salesChance.setStatus(1);
		salesChanceService.save(salesChance);
		attributes.addFlashAttribute("message","操作成功");
		return "redirect:/chance/list";
	}
	
	/**
	 * 显示表单录入页面
	 * 在request中必须存放modelAttribute对应的bean
	 * @return
	 */
	@RequestMapping(value="/chance/create",method=RequestMethod.GET)
	public String create(Map<String,Object> map){
		map.put("chance", new SalesChance());
		return "chance/input";
	}
	
	@RequestMapping(value="/chance/list",method=RequestMethod.GET)
	public String list(@RequestParam(value="pageNo",required=false) String pageNoStr,
						Map<String, Object> map,
						HttpSession session){
		//1.获取pageNo
		int pageNo=1;
		try {
			pageNo=Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
		//2.获取当前登录用户
		User createBy = (User) session.getAttribute("user");
	
		//3.调用Service方法获取Page对象
		Page<SalesChance> page=salesChanceService.getPage(pageNo,createBy,1);
	
		//4.把Page加入到request域中
		map.put("page", page);
		
		return "chance/list";
	}
}
