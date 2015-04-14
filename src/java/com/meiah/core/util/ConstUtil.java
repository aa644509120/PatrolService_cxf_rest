/** * A级 */
package com.meiah.core.util;

/**
 * 常量工具类
 * @author huanglb
 *
 */
public class ConstUtil {
	public static String timeout = "8000";	//定义超时时间
	
	/*
	 * sendstatus
	 * 0-失败
	 * 1-成功
	 * 2-地区发送失败，请检查该地区的webservice服务是否启动及所配置的服务地址是否正确
	 * 3-地区发送失败，该地区下面没有用户
	 * 4-地区发送失败，webservice服务地址为空
	 * 5-地区发送失败，请检查webservice所配置的用户和密码是否正确
	 * 6-用户发送失败，请检查该用户所在地区的webservice服务是否启动及所配置的服务地址是否正确
	 * 7-用户发送失败，请检查webservice所配置的用户和密码是否正确
	 */
	public static String SEND_STATUS_FAIL = 				"失败";
	public static String SEND_STATUS_SUCCESS = 				"成功";
	public static String SEND_STATUS_AREA_CONFIG = 			"地区发送失败，请检查该地区的webservice服务是否启动及所配置的服务地址是否正确";
	public static String SEND_STATUS_AREA_EMPTY_USER = 		"地区发送失败，该地区下面没有用户";
	public static String SEND_STATUS_EMPTY_URL = 			"地区发送失败，webservice服务地址为空";
	public static String SEND_STATUS_AREA_ERROR_VALIDATE = 	"地区发送失败，请检查webservice所配置的用户和密码是否正确";
	public static String SEND_STATUS_USER_CONFIG = 			"用户发送失败，请检查该用户所在地区的webservice服务是否启动及所配置的服务地址是否正确";
	public static String SEND_STATUS_USER_ERROR_VALIDATE = 	"用户发送失败，请检查webservice所配置的用户和密码是否正确";
}
