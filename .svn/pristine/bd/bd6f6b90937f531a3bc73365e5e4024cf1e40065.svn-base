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










import com.atguigu.crm.entity.Product;
import com.atguigu.crm.entity.Storage;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.orm.Utils;
import com.atguigu.crm.service.ProductService;
import com.atguigu.crm.service.StorageService;

@Controller
public class StorageHandler {
	@Autowired
	private StorageService storageService;
	@Autowired
	private ProductService productService;
	
	@ResponseBody
	@RequestMapping(value="/storage/delete/{storageId}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("storageId") Long id){
		storageService.delete(id);
		return "1";
	}

	@RequestMapping(value="/storage/create/{storage.id}",method=RequestMethod.PUT)
	public String update(@PathVariable("storage.id") Long id,RedirectAttributes attributes,
						Storage storage,@RequestParam(value="incrementCount",required=false,defaultValue="0") int incrementCount){
		//int incrementCount1=0;
		//incrementCount1=incrementCount;
		storage.setId(id);
		storage.setStockCount(storage.getStockCount()+incrementCount);
		storageService.update(storage);
		attributes.addFlashAttribute("message", "修改成功");
		return "redirect:/storage/list";
	}
	
	@RequestMapping("/storage/edit/{storage.id}")
	public String edit(@PathVariable("storage.id") Integer id,Map<String,Object> map){
		map.put("storage",storageService.getStorageById(id));
		return "storage/input";
	}
	
	@RequestMapping(value="/storage/create",method=RequestMethod.POST)
	public String save(Storage storage,RedirectAttributes attributes){
		storageService.save(storage);
		attributes.addFlashAttribute("message", "保存成功");
		return "redirect:/storage/list";
	}
	
	@RequestMapping("/storage/create")
	public String create(Map<String,Object> map){
		List<Product> products=productService.getAllProduct();
		map.put("products", products);
		map.put("storage", new Storage());
		return "storage/input";
	}
	
	@RequestMapping("/storage/list")
	public String list(@RequestParam(value="pageNo",required=false) String pageNoStr,
						Map<String, Object> map,HttpServletRequest request){
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		String queryString = Utils.encodeParamsToQueryString(params);
		map.put("queryString", queryString);
		int pageNo=1;
		try {
			pageNo=Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
		Page<Storage> page=storageService.getPage(pageNo,params);
		map.put("page", page);
		return "storage/list";
	}
}
