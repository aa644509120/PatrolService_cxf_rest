/** * A级 */
package com.meiah.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 生成17位字符串ID
 * @author huanglb
 *
 */
public class StringIDUtil {
	
	public String getStringID() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
		int tmp = 10 + new Random().nextInt(90);
		return sdf.format(now) + tmp;
	}
	
	
	/**
	 * 返回随机19位字符串
	 * @param now
	 * @return
	 * @author "zhangshaofeng"  
	 * @time Sep 18, 2012 7:41:54 PM
	 */
	public static String getRandomId(Date now) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		int tmp = 10 + new Random().nextInt(90);
		return sdf.format(now) + tmp;
	}
	
	public static void main(String[] args) {
		String s = new StringIDUtil().getStringID();
		System.out.println(s);
		System.out.println(s.length());
	}
	
}
