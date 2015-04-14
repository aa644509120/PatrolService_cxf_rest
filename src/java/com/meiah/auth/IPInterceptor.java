/** * A级 */
package com.meiah.auth;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.meiah.core.util.Config;
import com.meiah.core.util.IPUtil;
import com.meiah.core.util.Validator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 限制IP访问
 */
public class IPInterceptor extends AbstractPhaseInterceptor<Message> {
	private final static Logger logger = LogManager
			.getLogger(IPInterceptor.class);

	public IPInterceptor() {
		super(Phase.RECEIVE);
	}

	public void handleMessage(Message message) throws Fault {
		List<String> ipList = Config.ipList;
		if (Validator.isNotNull(ipList)) {
			// 指定CXF获取客户端的HttpServletRequest : http-request；
			HttpServletRequest request = (HttpServletRequest) message
					.get(AbstractHTTPDestination.HTTP_REQUEST);
			String ipAddress = "";
			boolean flag = false;
			if (null != request) {
				ipAddress = IPUtil.getUserIpAddr(request); // 取客户端IP地址
				logger.info("请求客户端的IP地址:" + ipAddress);
				for (String s : ipList) {
					if (s.equals(ipAddress)) {
						flag = true;
						break;
					}
				}
			}
			if (!flag) {
				throw new Fault(new IllegalAccessException("IP address "
						+ ipAddress + " is stint"));
			}
		}
	}

}
