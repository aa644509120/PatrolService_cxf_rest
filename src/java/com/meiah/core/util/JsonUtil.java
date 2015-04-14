/** * A级 */
package com.meiah.core.util;

/**
 * Copyright 2008
 * @author zengxb<br>
 * @email: zengxianbin@gmail.com<br>
 */
import java.beans.PropertyDescriptor;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
 

import ognl.Ognl;
import ognl.OgnlContext;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.meiah.core.orm.Page;

@SuppressWarnings("unchecked")
public class JsonUtil { 
	private transient static Log log = LogFactory.getLog(JsonUtil.class);

	/**
	 * 把json转换成Array
	 * 
	 * @param jsonStr
	 *            example:{swbsm:"111", orderType:"desc", order:"id"}
	 * @return String[][] String[x][2]
	 * @author fangw
	 */
	public static String[][] jsonToArray(String jsonStr) {
		if (jsonStr == null || jsonStr.equals("")
				|| jsonStr.equalsIgnoreCase("{}")
				|| jsonStr.equalsIgnoreCase("{ }")) {
			return new String[0][0];
		}
		jsonStr = jsonStr.substring(1);
		jsonStr = jsonStr.substring(0, jsonStr.length() - 1);
		jsonStr = jsonStr.replaceAll("\r\n", "");
		jsonStr = jsonStr.replaceAll("\"", "");
		jsonStr = jsonStr.replaceAll(", ", ",");
		log.debug(jsonStr);
		String[] jsonArr = jsonStr.split(",");
		String[][] jsonArr2 = new String[jsonArr.length][];
		for (int i = 0; i < jsonArr.length; i++) {
			jsonArr2[i] = jsonArr[i].trim().substring(2).split(":");
		}
		log.debug(jsonArr2.toString());
		return jsonArr2;
	}

	public static Map jsonToMap(String jsonStr) {
		if (jsonStr == null || jsonStr.equals("")
				|| jsonStr.equalsIgnoreCase("{}")
				|| jsonStr.equalsIgnoreCase("{ }")) {
			return new HashMap();
		}
		jsonStr = jsonStr.substring(1);
		jsonStr = jsonStr.substring(0, jsonStr.length() - 1);
		jsonStr = jsonStr.replaceAll("\"", "");
		jsonStr = jsonStr.replaceAll(", ", ",");
		String[] jsonArr = jsonStr.split(",");
		Map map = new HashMap();
		for (int i = 0; i < jsonArr.length; i++) {
			String[] tmpArr = jsonArr[i].split(":");
			map.put(tmpArr[0], tmpArr[1]);
		}
		return map;
	}

	public static JSONObject toJSONObject(Object bean, Class clazz,
			boolean nullable) {
		JSONObject jsonObject = new JSONObject();
		PropertyDescriptor[] destDesc = PropertyUtils
				.getPropertyDescriptors(clazz);
		try {
			for (int i = 0; i < destDesc.length; i++) {
				if (destDesc[i].getName().equals("class"))
					continue;
				Object proValue = PropertyUtils.getProperty(bean, destDesc[i]
						.getName());
				if (proValue == null) {
					proValue = "";
				} else if (proValue.toString().equals("") && !nullable)
					continue;
				else {
					jsonObject.put(destDesc[i].getName(), proValue);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	public static JSONObject toJSONObject(Map map) {
		JSONObject jsonObject = new JSONObject();
		Set keys = map.keySet();
		for (Iterator it = keys.iterator(); it.hasNext();) {
			String key = (String)it.next();
			if (map.get(key) instanceof  java.util.Date)
				jsonObject.put(key, DateUtil.getDate((Date)map.get(key)));
			else
				jsonObject.put(key, map.get(key));
		}
		return jsonObject;
	}
	
	public static String changeColumnName(String columnName){
		String[] names = columnName.split("_");
		String rs_name = names[0].toLowerCase();
		for(int i = 1; i<names.length; i++){
			if(names[i]!=""){
				String t_name = names[i].toLowerCase();
				String t_first = t_name.substring(0,1);
				rs_name = rs_name + t_first.toUpperCase()+t_name.substring(1);
			}
		}
		return rs_name;
	}
	
	public static JSONObject toJSONObject(Object obj) {
		if(obj instanceof Map)
			return toJSONObject((Map)obj);
//		return JSONObject.fromBean(obj);
		JSONObject jsonObject = new JSONObject();
		PropertyDescriptor[] destDesc = PropertyUtils
				.getPropertyDescriptors(obj);
		try {
			for (int i = 0; i < destDesc.length; i++) {
				if (destDesc[i].getName().equals("class")
						|| destDesc[i].getName().equals("handler")
						|| destDesc[i].getName().equals("json")
						|| destDesc[i].getName().equals(
								"hibernateLazyInitializer"))
					continue;
				if (PropertyUtils.getProperty(obj, destDesc[i].getName()) == null) {
					jsonObject.put(destDesc[i].getName(), "");
				} else {
					if (destDesc[i].getPropertyType() == java.util.Date.class)
						jsonObject
								.put(
										destDesc[i].getName(),
										DateUtil.getDate((java.util.Date) PropertyUtils
														.getProperty(
																obj,
																destDesc[i]
																		.getName())));
					else
						jsonObject.put(destDesc[i].getName(), PropertyUtils
								.getProperty(obj, destDesc[i].getName())
								.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	public static JSONArray toJSONArray(Object[] objs) {
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < objs.length; i++) {
			jsonArray.add(objs[i]);
		}
		return jsonArray;
	}

	public static JSONArray toJSONArray(Collection objs) {
		JSONArray jsonArray = new JSONArray();
		if (objs == null)
			return jsonArray;
		for (Iterator it = objs.iterator(); it.hasNext();) {
			jsonArray.add(JsonUtil.toJSONObject(it.next()));
		}
		return jsonArray;
	}

	public static JSONObject toJSONPage(Page page) {
		JSONObject jsonPage = new JSONObject();
		jsonPage.put("totalCount", page.getTotalCount());
		jsonPage.put("root", JsonUtil.toJSONArray(page.getResult()));
		return jsonPage;
	}

	public static JSONObject toFlexiJSONPage(Page page) {
		JSONObject jsonPage = new JSONObject();
		jsonPage.put("page", page.getPageNo());
		jsonPage.put("total", page.getTotalCount());
		jsonPage.put("rows", JsonUtil.toJSONArray(page.getResult()));
		return jsonPage;
	}
	
	public static Object toObjcet(String jsonStr, Class c) {
		return toObjcet(JSONObject.parseObject(jsonStr), c);
	}

	public static Object toObjcet(JSONObject ObjJSON, Class c) {
		try {
			Object vo = c.newInstance();
			OgnlContext context = new OgnlContext();
			context.put("vo", vo);
			PropertyDescriptor[] destDesc = PropertyUtils
					.getPropertyDescriptors(vo);
			for (int i = 0; i < destDesc.length; i++) {
				String expr = "vo." + destDesc[i].getName();
				try{
					String value = ObjJSON.getString(destDesc[i].getName());;
					if (value != null) {
						try {
							Ognl.setValue(expr, context,convertValue(value, destDesc[i].getPropertyType()));
						} catch (Exception e) {
							Ognl.setValue(expr, context, null);
						}
					}
				}catch (Exception e) {
				}
			}
			return vo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static List toList(String jsonStr, Class c) {
		if (Validator.isNull(jsonStr))
			return new ArrayList();
		return toList(JSONArray.parseArray(jsonStr), c);
	}

	public static List toList(JSONArray jsonArray, Class c) {
		try {
			List list = new ArrayList();
			for (int i = 0; i < jsonArray.size(); i++) {
				Object vo = JsonUtil.toObjcet(jsonArray.getJSONObject(i), c);
				list.add(vo);
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private static Object convertValue(String value, Class clazz) throws ParseException {
		if (clazz.getName().equals("boolean"))
			return new Boolean(value);
		if (clazz.getName().equals(Integer.class.getName())){
			if(Validator.isNull(value)) return null;
			return new Integer(value);
		}
		if (clazz.getName().equals(java.util.Date.class.getName())){
			if(Validator.isNull(value)) return null;
			return DateUtil.convertStringToDate(value);
		}
		return value;
	}
	
	public static void main(String[] args) {
		//
		// String data = "[{id:'1006',
		// data1:'1',data2:'2',data2:'2',data3:'3',data4:'4',data5:'5',data6:'6'},{id:'1007',
		// data1:'1',data2:'2',data2:'2',data3:'3',data4:'4',data5:'5',data6:'6'}]";
		// List list = JsonUtil.toList(data,Testdetail.class);
		// Testdetail testDetail = (Testdetail)list.get(1);
		// System.out.println(testDetail.getId());
	}
}
