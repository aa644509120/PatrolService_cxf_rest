/** * A级 */
package com.meiah.core.util;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

/**
 * <code>ProxySettingSample</code>
 * 
 * @author Jimmy(SZ)
 * 
 * @since <i>May 25, 2013</i>
 */
public class Test {
	public static final String PRXOY_HOST = "172.16.1.124";
	public static final String PROXY_PORT = "5631";
	public static final String TEST_ADDRESS = "http://www.google.com.tw";

	// /**
	// * @param args
	// */
	// public static void main(String[] args) {
	// // 用户验证
	// if (args.length == 2) {
	// Authenticator.setDefault(new LoginAuthenticator(args[0], args[1]));
	// }
	//
	// Callable<Boolean> oCallback = new Callable<Boolean>() {
	// @Override
	// public Boolean call() throws Exception {
	// HttpURLConnection oConn = null;
	// try {
	// Proxy oProxy = new Proxy(Proxy.Type.HTTP, new
	// InetSocketAddress(PRXOY_HOST, Integer.parseInt(PROXY_PORT)));
	// URL url = new URL("http://www.aastocks.com");
	// oConn = (HttpURLConnection) url.openConnection(oProxy);
	// oConn.setConnectTimeout(5000);
	// oConn.connect();
	// } catch (Exception e) {
	// e.printStackTrace();
	// // 如果报异常证明，代理设置不可用
	// return false;
	// } finally {
	// if (oConn != null) {
	// oConn.disconnect();
	// }
	// }
	// return true;
	// }
	// };
	//
	// FutureTask<Boolean> oTask = new FutureTask<Boolean>(oCallback) {
	// @Override
	// protected void done() {
	// try {
	// Boolean bCheckSuccess = (Boolean)this.get();
	// if (bCheckSuccess) {
	// // 代理测试成功
	// System.err.println("代理成功");
	// // 测试成功后再使用"全局网络代理"
	// System.setProperty("proxySet", "true");
	// System.setProperty("http.proxyHost", PRXOY_HOST);
	// System.setProperty("http.proxyPort", PROXY_PORT);
	// } else {
	// //代理设置失败
	// System.err.println("无法连接到网络，请检查代理设置!!");
	// }
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// } catch (ExecutionException e) {
	// e.printStackTrace();
	// }
	// }
	// };
	// EXECUTOR.submit(oTask);
	// EXECUTOR.shutdown();
	// }
	//
	// static class LoginAuthenticator extends Authenticator {
	// /** User name **/
	// private String m_sUser;
	// /** Password **/
	// private String m_sPsw;
	//
	// public LoginAuthenticator(String sUser, String sPsw) {
	// m_sUser = sUser;
	// m_sPsw = sPsw;
	// }
	//
	// public PasswordAuthentication getPasswordAuthentication() {
	// return (new PasswordAuthentication(m_sUser, m_sPsw.toCharArray()));
	// }
	// }

	public static void main(String[] args) throws MalformedURLException,
			IOException, URISyntaxException, AWTException {
		String parent = "X:\\apache-tomcat-6.0.35\\webapps\\ROOT\\ReportInfo\\temp\\5a2465a6-a97b-41ff-bfa3-de343531f393";
		System.out.println(parent.substring(parent.lastIndexOf(File.separator)+1) );
	}
	
	public static void test(String...test){
		if(Validator.isNotNull(test)){
			System.out.println(test[0]);
		}
		
	}

}
