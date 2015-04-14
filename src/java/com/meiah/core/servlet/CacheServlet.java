/** * A级 */
package com.meiah.core.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class CacheServlet extends HttpServlet {
	private static final long serialVersionUID = 8534551651088365735L;
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	public void loadData() {
		 
	}
	
	public WebApplicationContext getContext(){
		WebApplicationContext context = (WebApplicationContext)WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		return context;
	}
	
	public void init() throws ServletException { 
		loadData();
	}
}
