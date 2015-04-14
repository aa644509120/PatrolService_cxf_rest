/** * A级 */
/**
 * 
 */
package com.meiah.core.util;

import java.util.StringTokenizer;

/**
 * 用于URL地址进行域名解析
 * 
 * @author xiegh
 * 
 */
public class HttpURLUtil {

	/**
	 * 获取URL中的主机域名字段
	 * 
	 * @param url
	 */
	public static String findDomain(String url) {
		if (url == null || url.trim().length() <= 0) {
			throw new IllegalArgumentException("url参数不可为空!");
		}

		// 无论ftp、http、https均含有'://'字符串
		final String protocalSuffix = "://";
		int startPos = url.indexOf(protocalSuffix);
		if (startPos <= 0) {
			throw new IllegalArgumentException("url协议属性不可为空!");
		}
		startPos += protocalSuffix.length();
		int endPos = url.indexOf("/", startPos);// 获取URL地址中/的开始位置
		if (endPos <= 0) {
			endPos = url.length();
		}

		return url.substring(startPos, endPos);
	}

	/**
	 * 根据当前域名，获取其上一级域名。如果已经无上级域名，则直接返回null
	 * 
	 * @param domain
	 * @return
	 */
	public static String upperDomain(String domain) {
		if (domain == null || domain.trim().length() <= 0) {
			return null;
		}

		StringTokenizer st = new StringTokenizer(domain, ".");
		// 剩下最后一个了，如baidu.com，再往上一级只剩下com了，因此不用再计算
		if (st.countTokens() <= 2) {
			return null;
		}

		st.nextElement();
		StringBuilder sb = new StringBuilder();
		while (st.hasMoreElements()) {
			if (sb.length() > 0) {
				sb.append(".");
			}
			sb.append(st.nextElement());
		}

		// 只剩下com.cn或者net.cn之类的了
		if (sb.indexOf("com.") == 0 || sb.indexOf("net.") == 0) {
			return null;
		}

		return sb.toString();
	}

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println(findDomain("http://www.baidu.com"));
		System.out.println(findDomain("http://www.baidu.com/q=2332"));
		System.out.println(upperDomain("www.simplecd.me"));
		System.out.println(upperDomain("simplecd.me"));
		System.out.println(upperDomain("baike.baidu.net.cn"));
		System.out.println(upperDomain("baidu.net.cn"));
	}
}
