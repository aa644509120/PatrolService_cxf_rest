/** * A级 */
package com.meiah.core.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;

public class SinaUtil {
//	public static final String cookie=  "UUG=usr4023; SSOLoginState=1395796859; ALF=1398388859; SUBP=002A2c-gVlwEm1uAWxfgXELuuu1xVxBxA79cqihRCTCM9UCEwbsniSyuHY-u_1%3D; SUB=AdtICoFiXnZB8d6xAzlFz3MJdvhLhD%2Fysrl6%2FFou6seSKGwkza8zOvLyZL79ZvrXtZzWlJWpI4Z0k9NnXvt05MvVsWKM7onRh1ksaeV1vYJfzNJI4dizKg%2BuZBMuYZ%2BiwzTiu2G%2BcvQrMP8%2FjxAsyrw%3D; SUP=cv%3D1%26bt%3D1395796859%26et%3D1395883259%26d%3Dc909%26i%3D9e53%26us%3D1%26vf%3D0%26vt%3D0%26ac%3D2%26st%3D0%26uid%3D5079772492%26name%3Dpatrol_2%2540sina.com%26nick%3D%25E7%2594%25A8%25E6%2588%25B75079772492%26fmp%3D%26lcp%3D; SUE=es%3Dc3346fe3a8aeb5b0a40626ca4fe5c3be%26ev%3Dv1%26es2%3De78c8726216f893b8284dabd26deba4d%26rs0%3DeiGjgWS%252BJGz0bhubcJdA5mXZkgPLArItjb2zhVvB8J7BUB%252BKllFy%252BjEf09o%252F5CmPYBjECnOJzVWVGZ5G6Z1o%252FETAgBAl6iPD3NzZh9exgnYmrf564vfgECUI8eL58NkhxWd%252FniRmtsQFVouleJY5ZcxC%252FSo6d0j53z9F5UXu%252F1Q%253D%26rv%3D0; SUS=SID-5079772492-1395796859-JA-3tc0k-b5244265fe8309f6714da001184b80c6; SUS=SID-5079772492-1395796859-JA-3tc0k-b5244265fe8309f6714da001184b80c6; ALC=ac%3D2%26bt%3D1395796859%26cv%3D5.0%26et%3D1398388859%26uid%3D5079772492%26vf%3D0%26vt%3D0%26es%3D84677915deb19c343742e4e562354831; un=patrol_2@sina.com; myuid=5079772492";
//	public static final String cookie="SINAGLOBAL=4558713026344.776.1392615235604; SSOLoginState=1395799335; _s_tentry=login.sina.com.cn; UOR=bbs.xmfish.com,widget.weibo.com,qinxuye.me; Apache=1030020012985.9149.1395799294842; ULV=1395799295830:6:2:1:1030020012985.9149.1395799294842:1394005630681; SUS=SID-2648648951-1395799338-XD-to0w2-e91fdccb99756ec13f32c96ec562bac4; SUE=es%3Dde5a43a19f1336b2521334f8b982fb8f%26ev%3Dv1%26es2%3D55cfe8a305739b65b6c70be9c0430554%26rs0%3DzFLFFJPMjMVFlTyXG337yDAwRC7oqIa1uS6tJca2VVtQS3HTIcfyRewcPL83CH%252BD713lsv05%252FsrEzhPXFLCc4G5H%252FWIgOk8SGSEsQZIApyObkLwrjx%252BbY4OGuqgcgxAvanN9TLRO6oBgObdac%252FPzUX1GIwFqrdnv5eVO04WConU%253D%26rv%3D0; SUP=cv%3D1%26bt%3D1395799338%26et%3D1395885738%26d%3Dc909%26i%3Dd8fe%26us%3D1%26vf%3D0%26vt%3D0%26ac%3D0%26st%3D0%26uid%3D2648648951%26name%3D471520384%2540qq.com%26nick%3Dempty_my%26fmp%3D%26lcp%3D2013-04-19%252017%253A30%253A58; SUB=AQ%2F%2B0qWHlerIvnvzHIO5XOBd7Qttr8cHwRJKSpnIi0%2BEyWFLF9Hhk%2BBiyaPjoGkkj2w0OJhZj1uIdNBnmsMzZZRPOcc%2FnnaQ0W6saLRKXlba07ts7E0yeaE2wWHSLyeqyVOvOpDGGgf3csFcb9Rh5RQ%3D; SUBP=002A2c-gVlwEm1uAWxfgXELuuu1xVxBxAAk8dQmyweFA6ZOkYHQSZSMuHY-u_E%3D; ALF=1398391329; UUG=usr4025; wvr=5; UV5=usrmdins340_123; UV5PAGE=usr540_143";
	public static boolean validatorCookie() {
		HttpClient httpClient = new HttpClient();
		String url = "http://weibo.com/u/2648648951/home?wvr=5";

		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setContentCharset("UTF-8");  
		getMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		
		getMethod.getParams().setParameter("http.protocol.cookie-policy",CookiePolicy.BROWSER_COMPATIBILITY);
	 
		getMethod.setRequestHeader("Accept-Language","zh-cn");
		getMethod.setRequestHeader("Connection","Keep-Alive");
		getMethod.setRequestHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0)");
		if(Validator.isNotNull(Config.sinaCookie)){
			getMethod.setRequestHeader("Cookie",Config.sinaCookie.getCookiestr());
		}

		try {
			int statusCode = httpClient.executeMethod(getMethod);
			System.out.println(statusCode);
			// 301或者302
//			if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY
//					|| statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
//				Header locationHeader = getMethod.getResponseHeader("location");
//				String location = null;
//				if (locationHeader != null) {
//					location = locationHeader.getValue();
//					System.out.println("The page was redirected to:" + location);
//				} else {
//					System.err.println("Location field value is null.");
//				}
//				return ;
//			}
			if (statusCode != HttpStatus.SC_OK) {
				return false;
			}			
//			byte[] responseBody = getMethod.getResponseBodyAsStream();
			//String response = method.getResponseBodyAsString().trim();
			InputStream resStream = getMethod.getResponseBodyAsStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(resStream));
			StringBuffer resBuffer = new StringBuffer();
			String resTemp = "";
			while((resTemp = br.readLine()) != null){
				resBuffer.append(resTemp);
			}
			String body = new String(resBuffer.toString().getBytes(),"utf-8");
			if(body.indexOf("新浪微博注册") != -1){
				return false;
			}
			
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
		return false;
	}
}

