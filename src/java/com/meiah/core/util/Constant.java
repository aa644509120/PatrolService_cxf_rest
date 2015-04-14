/** * A级 */
package com.meiah.core.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统常量
 */
public class Constant {
	public static final String ZD = "zd";//阵地巡查
	public static final String WEIBO = "weibo";//微博巡查
	public static final String PATROL = "patrol";// 巡查平台
	public static Map<String,String> ssMap = new HashMap<String, String>();
	static {
		 ssMap.put(Constant.ZD, "重点网站巡查");
		 ssMap.put(Constant.WEIBO,"微博巡查");
		 ssMap.put(Constant.PATROL, "巡查平台");
	 }
}

