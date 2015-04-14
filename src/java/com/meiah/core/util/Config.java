/** * A级 */
package com.meiah.core.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import edu.emory.mathcs.backport.java.util.Arrays;



public class Config {
	public static Logger logger = Logger.getLogger(Config.class);
	public static String imgFilePath="";
	public static String IPLimit="";
	public static String server_ip="218.85.138.142";
	public static int socket_port=21555;
	public static MyCookie qqCookie;
	public static MyCookie sinaCookie;
	public static String patrolAddress;
	public static List<String> ipList;
	public static String CUTIMAGETIME="5000";
	
	public static Map<String,String> thirdParSysMap = new HashMap<String, String>(); 
	static{
		load();
	}
	
	private static void load(){
		String configFile = Config.class.getResource("/").getPath().toString().replaceAll("file:/", "").replaceAll("%20"," ")+"config.properties";
		String thirdFile = Config.class.getResource("/").getPath().toString().replaceAll("file:/", "").replaceAll("%20"," ")+"thirdPartSystems.properties";
		Properties p = new Properties();
		try {
			InputStreamReader inputStream =  new InputStreamReader(new FileInputStream(configFile), "utf-8");
			p.load(inputStream);
			imgFilePath = p.getProperty("imgFilePath","");
			IPLimit = p.getProperty("IPLimit","");
			patrolAddress = p.getProperty("patrolAddress");
			server_ip = p.getProperty("server_ip");
			socket_port =Integer.parseInt( p.getProperty("socket_port").trim());
			String IPList = p.getProperty("IPList","");
			new Thread(new Runnable() {
				public void run() {
					//获取新浪和QQ微博的cookie
					qqCookie = CookieUtil.getCookie("qq");
					sinaCookie = CookieUtil.getCookie("sina");
				}
			}).start();
			if(Validator.isNotNull(IPList)){
				String[] ips = IPList.split(";");
				ipList = Arrays.asList(ips);
			}
			CUTIMAGETIME = p.getProperty("CUTIMAGETIME","5000");
			
			/*PatrolServiceImpl类中syncKeys方法获取文件参数*/
			p = new Properties();
			InputStreamReader inputStream_t =  new InputStreamReader(new FileInputStream(thirdFile), "utf-8");
			p.load(inputStream_t);
			Set<Object> keySet=p.keySet();
			Iterator<Object> iterator=keySet.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				String systempName = Constant.ssMap.get(key);
				if(Validator.isNotNull(systempName)){
					thirdParSysMap.put(systempName,p.getProperty(key));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
