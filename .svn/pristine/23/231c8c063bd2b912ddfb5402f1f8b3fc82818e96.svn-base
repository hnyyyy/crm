package com.atguigu.crm.orm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;

import com.atguigu.crm.orm.PropertyFilter.MatchType;

public class Utils {
	/**
	 * 把由 handler 传入的 params 转为 PropertyFilter 的集合. 
	 * @param params
	 * @return
	 */
	public static List<PropertyFilter> parseHandlerParamsToPropertyFilters(
			Map<String, Object> params) {
		List<PropertyFilter> filters = new ArrayList<>();
		
		for(Map.Entry<String, Object> entry: params.entrySet()){
			String fieldName = entry.getKey();
			Object fieldVal = entry.getValue();
			
			PropertyFilter filter = new PropertyFilter(fieldName, fieldVal);
			filters.add(filter);
		}
		
		return filters;
	}
	
	/**
	 * 把 由 handler 传入的params 转为 PropertyFilter 的集合转为 mybatis 可用的 params.
	 * @param filters
	 * @return
	 */
	public static Map<String, Object> parsePropertyFiltersToMyBatisParmas(
			List<PropertyFilter> filters) {
		Map<String, Object> params = new HashMap<String, Object>();
		for(PropertyFilter filter: filters){
			String propertyName = filter.getPropertyName();
			
			Object propertyVal = filter.getPropertyVal();
			Class propertyType = filter.getPropertyType();
			propertyVal = ConvertUtils.convert(propertyVal, propertyType);
			
			MatchType matchType = filter.getMatchType();
			if(matchType == MatchType.LIKE){
				propertyVal = "%" + propertyVal + "%";
			}
			
			params.put(propertyName, propertyVal);
		}
		
		return params;
	}
	
	/**
	 * 点击 "翻页链接" 时, 如何能携带查询条件 ? 
	 * 1). 把查询条件放在 HttpSession 对象中. 最好是不这么做. 原则为: 能放在较小域对象中的 key-value, 就不放在较大
	 * 的域对象中. 
	 * 2). 把 1 得到的请求参数的 Map, 在序列化为一个查询字符串:
	 * {"LIKES_custName":"a","LIKES_title":"b","LIKES_contact":"c"} -->
	 * &search_LIKES_custName=a&search_LIKES_title=b&search_LIKES_contact=c
	 * 再把改查询字符串传回到页面上, 在点击 "翻页链接" 时, 携带该查询字符串, 则既可实现携带查询条件. 
	 * @param params
	 * @return
	 */
	public static String encodeParamsToQueryString(Map<String, Object> params) {
		StringBuilder result = new StringBuilder();
		
		for(Map.Entry<String, Object> entry: params.entrySet()){
			String key = entry.getKey();
			Object val = entry.getValue();
			
			if(val == null || val.toString().trim().equals("")){
				continue;
			}
			
			result.append("&")
			      .append("search_")
			      .append(key)
			      .append("=")
			      .append(val);
		}
		
		return result.toString();
	}
}
