package com.atguigu.crm.handler;

import java.io.Reader;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atguigu.crm.entity.Role;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.AuthorityService;
import com.atguigu.crm.service.RoleService;

@Controller
public class RoleHandler {
	@Autowired
	private RoleService roleService;
	@Autowired
	private AuthorityService authorityService;
	
	@RequestMapping("/role/list")
	public String list(@RequestParam(value="pageNo",required=false) String pageNoStr,
						Map<String, Object> map){
		int pageNo=1;
		try {
			pageNo=Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
		Page<Role> page=roleService.getPage(pageNo);
		map.put("page", page);
		return "role/list";
		
	}
	
	@RequestMapping(value="/role/create")
	public String create(Map<String, Object> map){
		map.put("role", new Role());
		return "role/input";
	}
	
	@RequestMapping(value="/role/save")
	public String save(Role role,RedirectAttributes attributes){
		roleService.save(role);
		attributes.addFlashAttribute("message", "保存成功");
		return "redirect:/role/list";
	}
	
	@RequestMapping("/role/assign/{role.id}")
	public String assign(@PathVariable("role.id") long id,Map<String,Object> map){
		
		Role role=roleService.getRoleById(id);
		map.put("role", role);
		map.put("parentAuthorities",authorityService.getParentAuthorities());
		
		
		return "role/assign";
	}
	
	@RequestMapping(value="/role/edit/{role.id}")
	public String update(@PathVariable("role.id") long id,RedirectAttributes attributes,
						Role role){
		role.setId(id);
		roleService.update(role);
		attributes.addFlashAttribute("message", "更新成功");
		return "redirect:/role/list";
	}
	
	
	
	
	
	
	
	@RequestMapping("/role/delete/{role.id}")
	public String delete(@PathVariable("role.id") long id,RedirectAttributes attributes){
		roleService.delete(id);
		attributes.addFlashAttribute("message","删除成功");
		return "redirect:/role/list";
	}
}
