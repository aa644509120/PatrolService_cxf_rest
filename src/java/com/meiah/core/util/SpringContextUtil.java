/** * A级 */
package com.meiah.core.util;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 普通类在任意位置得到spring 中的bean
 * @author huanglb
 * @since add by huanglb, 20120919
 *
 */
public final class SpringContextUtil implements ApplicationContextAware {
	private static ApplicationContext context;
	

	@SuppressWarnings("static-access")
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}

	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}

	public static String getMessage(String key) {
		return context.getMessage(key, null, Locale.getDefault());
	}
	
	public static void cleanContext() {
		context = null;
    }
}
