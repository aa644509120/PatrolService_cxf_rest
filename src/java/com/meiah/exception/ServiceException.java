/** * A级 */
package com.meiah.exception;

/**
 * 自定义异常类 
 * @author empty
 */
public class ServiceException extends RuntimeException {
 
	private static final long serialVersionUID = 1L;
	
	public ServiceException() {
		super();
	}

	public ServiceException(String msg) {
		super(msg);
	}
	public ServiceException(Throwable t){
		super(t);
	}

}
