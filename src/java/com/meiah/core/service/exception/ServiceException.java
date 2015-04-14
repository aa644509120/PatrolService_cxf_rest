/** * A级 */
package com.meiah.core.service.exception;

/**
 * Service层公用的Exception.
 * 继承自RuntimeException，会触发Spring的事务管理引起事务回滚.
 * 
 * @author zengxb
 */
public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = -2219802866682601167L;
}
