package com.atguigu.crm.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Product;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.orm.Utils;
import com.atguigu.crm.service.ProductService;

@Controller
public class ProductHandler {
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/product/delete/{product.id}")
	public String delete(@PathVariable("product.id") Long id,RedirectAttributes attributes){
		productService.delete(id);
		attributes.addFlashAttribute("message", "删除成功");
		return "redirect:/product/list";
	}
	
	@RequestMapping(value="/product/create/{product.id}",method=RequestMethod.PUT)
	public String update(@PathVariable("product.id") Long id,RedirectAttributes attributes,
						Product product){
		product.setId(id);
		productService.update(product);
		attributes.addFlashAttribute("message", "修改成功");
		return "redirect:/product/list";
	}
	
	@RequestMapping("/product/edit/{product.id}")
	public String edit(@PathVariable("product.id") Integer id,Map<String,Object> map){
		map.put("product", productService.getProductById(id));
		return "product/input";
	}
	
	@RequestMapping(value="/product/create",method=RequestMethod.POST)
	public String save(Product product,RedirectAttributes attributes){
		productService.save(product);
		attributes.addFlashAttribute("message", "保存成功");
		return "redirect:/product/list";
	}
	
	@RequestMapping("/product/create")
	public String create(Map<String,Object> map){
		map.put("product", new Product());
		return "product/input";
	}
	
	@RequestMapping("/product/list")
	public String list(@RequestParam(value="pageNo",required=false) String pageNoStr,
						Map<String, Object> map,HttpServletRequest request){
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		String queryString = Utils.encodeParamsToQueryString(params);
		map.put("queryString", queryString);
		int pageNo=1;
		try {
			pageNo=Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
		Page<Product> page=productService.getPage(pageNo,params);
		map.put("page", page);
		return "product/list";
	}
	
	
}
