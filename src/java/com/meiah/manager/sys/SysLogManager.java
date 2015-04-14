/** * A级 */
package com.meiah.manager.sys;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meiah.core.service.AbstractCrudManager;
import com.meiah.entity.sys.SysLog;

@Service
@Transactional
public class SysLogManager extends AbstractCrudManager<SysLog> {
	
	/**
	 * remark:操作类型，例如新增成功
	 * content：操作的具体内容
	 */
	public void recordLog(Long userid , String remark , String content,WebServiceContext context) throws Exception{
		SysLog log = new SysLog();
		try{
			log.setTime(new Date());//日期
			log.setRemark(remark);
			log.setComeFrom("巡查接口");
			log.setContent(content);
			log.setUserid(userid);//操作用户ID
			log.setIp(getIp(context));//操作用户IP
			this.add(log);
		}catch(Exception e){}
	}
	
	private String getIp(WebServiceContext context) {
		try {
			MessageContext ctx =  (MessageContext) context.getMessageContext();
			HttpServletRequest request = (HttpServletRequest) ctx.get(AbstractHTTPDestination.HTTP_REQUEST);
			String ip = request.getRemoteAddr();
			return ip;
		} catch (Exception e) {
			return "";
		}
	}
}
