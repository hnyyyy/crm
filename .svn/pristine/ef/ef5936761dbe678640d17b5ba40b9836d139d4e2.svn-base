package com.atguigu.crm.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Dict;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.orm.Utils;
import com.atguigu.crm.service.DictService;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Controller
public class DictHandler {
	@Autowired
	private DictService dictService;
	
	@ResponseBody
	@RequestMapping(value="/dict/delete/{dictId}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("dictId") Long id){
		dictService.delete(id);
		return "1";
	}
	
	@RequestMapping(value="/dict/create/{dict.id}",method=RequestMethod.PUT)
	public String update(@PathVariable("dict.id") Long id,RedirectAttributes attributes,
						Dict dict){
		dict.setId(id);
		dictService.update(dict);
		attributes.addFlashAttribute("message", "更新成功");
		return "redirect:/dict/list";
	}
	
	@RequestMapping("/dict/edit/{dict.id}")
	public String edit(@PathVariable("dict.id") Integer id,Map<String,Object> map){
		map.put("dict", dictService.getDictById(id));
		return "dict/input";
	}
	
	@RequestMapping(value="/dict/create",method=RequestMethod.POST)
	public String save(Dict dict,RedirectAttributes attributes){
		dictService.save(dict);
		attributes.addFlashAttribute("message", "保存成功");
		return "redirect:/dict/list";
	}
	
	@RequestMapping("/dict/create")
	public String create(Map<String,Object> map){
		map.put("dict", new Dict());
		return "dict/input";
	}
	
	@RequestMapping("/dict/list")
	public String list(@RequestParam(value="pageNo",required=false) String pageNoStr,
						Map<String, Object> map,HttpServletRequest request){
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		String queryString = Utils.encodeParamsToQueryString(params);
		map.put("queryString", queryString);
		int pageNo=1;
		try {
			pageNo=Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
		Page<Dict> page=dictService.getPage(pageNo,params);
		map.put("page", page);
		return "dict/list";
	}
}
