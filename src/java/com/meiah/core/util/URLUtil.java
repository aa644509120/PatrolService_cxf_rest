/** * A级 */
package com.meiah.core.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class URLUtil {

	public static Map<String , String> splitURL(String url){
		String preUrl = "";
		String paramUrl = "";
		String wflag = "1";//判断URL是不是以问号存在
		Map<String , String> map = new HashMap<String, String>();
		//取出地址链接
		if(url.indexOf("?") != -1){
			preUrl = url.substring(0,url.lastIndexOf("?"));
			paramUrl = url.substring(url.lastIndexOf("?")+1);
		}else{
			preUrl = url.substring(0,url.lastIndexOf("/"));
			paramUrl = url.substring(url.lastIndexOf("/")+1);
			wflag = "0";
		}
		map.put("preUrl", preUrl);
		map.put("paramUrl", paramUrl);
		map.put("wflag", wflag);
		return map;
	}
	
	public static Map<String , String> splitParam(String paramUrl , String wflag){
		Map<String , String> map = new HashMap<String, String>();
		try {
			String[] params = paramUrl.split("&");
			if(wflag.equals("0")){
				map.put("keyName", URLDecoder.decode(params[0], "UTF-8"));
//				map.put("keyName", new String(params[0].getBytes("ISO-8859-1"),"UTF-8"));
			}else{
				String[] param = params[0].split("=");
				String value = param[1];
//				map.put(key,new String(value.getBytes("ISO-8859-1"),"UTF-8"));
				map.put("keyName", URLDecoder.decode(value, "UTF-8"));
			}
			
			if(params.length > 1){
				for (int i = 1; i < params.length; i++) {
					String[] param = params[i].split("=");
					String key = param[0];
					String value = param[1];
					map.put(key, value);
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return map;
	}
	
//	public static void main(String[] args) throws Exception{ 
//		String url = "http://s.weibo.com/wb/%E9%83%AD%E5%BE%B7%E7%BA%B2&xsort=time&scope=ori&haspic=1&timescope=custom:2013-11-17-15:2013-12-17-15&region=custom:11:1000&page=1&Refer=g";
//		Map<String , String> map = Test.splitURL(url);
//		String paramUrl = map.get("paramUrl");
//		String wflag = map.get("wflag");
//		
//		if(Validator.isNotNull(paramUrl)){
//			Map<String,String> paramMap = Test.splitParam(paramUrl, wflag);
//			
//			Iterator<Map.Entry<String, String>> it = paramMap.entrySet().iterator();
//			while(it.hasNext()){
//				Entry<String , String> entry = it.next();
//				System.out.print(entry.getKey() +"===========");
//				System.out.println(entry.getValue());
//			}
//		}
//	} 
}
