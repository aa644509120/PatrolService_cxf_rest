/** * A级 */
package com.meiah.core.util;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 配置文件属性和值map生成工具
 * @author raolp
 *
 */
public class PropertiesFileUtil {
	private static final Logger logger = Logger.getLogger(PropertiesFileUtil.class);
	
	public static Map<Object,Object> getMapOfProperties() throws Exception{
		
		Map<Object,Object> map = new HashMap<Object, Object>();
		String configFile = PropertiesFileUtil.class.getResource("/").getPath().toString().replaceAll("file:/", "")+"config.properties";
		try {
			Properties p = new Properties();
			InputStreamReader inputStream =  new InputStreamReader(new FileInputStream(configFile), "utf-8");
			p.load(inputStream);
			Object[] keys = p.keySet().toArray();
			if(Validator.isNotNull(keys)){
				for (int i = 0; i < keys.length; i++) {
					map.put(keys[i], p.get(keys[i]));
				}
			}
		} catch (Exception e) {
			logger.error("获取配置文件的属性和值map出错",e);
		}
		return map;
	}
}
