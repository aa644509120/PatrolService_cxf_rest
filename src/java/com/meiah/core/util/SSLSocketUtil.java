/** * A级 */
package com.meiah.core.util;

import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;

import org.apache.log4j.Logger;


public class SSLSocketUtil {
	
	private static Logger logger=Logger.getLogger(SSLSocketUtil.class);
	
	public static final String PRXOY_HOST = "172.16.1.124";
	public static final String PROXY_PORT = "5631";
	
    private static final String SERVER_KEY_STORE_PASSWORD = "pico2012server";
    private static final String SERVER_TRUST_KEY_STORE_PASSWORD = "pico2012server";
    

    private static final String CLIENT_KEY_STORE_PASSWORD       = "pico2012server";
    private static final String CLIENT_TRUST_KEY_STORE_PASSWORD = "pico2012server";
    

    private static final String SERVER_KEY_STORE_PATH = "kserver.keystore";
    private static final String SERVER_TRUST_KEY_STORE_PATH = "tserver.keystore";
    

    private static final String CLIENT_KEY_STORE_PATH       = "kclient.keystore";
    private static final String CLIENT_TRUST_KEY_STORE_PATH = "tclient.keystore";

    private static SSLServerSocket serverSocket;

	public static SSLServerSocket getSSLServerSocket(Integer port){
		try {
			if(serverSocket == null){
				SSLContext ctx = SSLContext.getInstance("SSL");
	            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
	            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
	            KeyStore ks = KeyStore.getInstance("JKS");
	            KeyStore tks = KeyStore.getInstance("JKS");
	            ks.load(new FileInputStream(getRealPath(SERVER_KEY_STORE_PATH)), SERVER_KEY_STORE_PASSWORD.toCharArray());
	            tks.load(new FileInputStream(getRealPath(SERVER_TRUST_KEY_STORE_PATH)), SERVER_TRUST_KEY_STORE_PASSWORD.toCharArray());
	            kmf.init(ks, SERVER_KEY_STORE_PASSWORD.toCharArray());
	            tmf.init(tks);

	            ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
	            serverSocket = (SSLServerSocket) ctx.getServerSocketFactory().createServerSocket(port);
	            serverSocket.setNeedClientAuth(true); 
			}
        } catch (Exception e) {
        	logger.debug("初始化SSLServerSocket时异常",e);
        }
		return serverSocket;
	}
	
	public static SSLSocket getSSLSocket(String host,Integer port){
		logger.info("getSSLSocket,host:"+host+",port:"+port);
//		System.setProperty("proxySet", "true");
//		System.setProperty("http.proxyHost", PRXOY_HOST);
//		System.setProperty("http.proxyPort", PROXY_PORT);

		SSLSocket sslSocket = null;
		try {
						
            SSLContext ctx = SSLContext.getInstance("SSL");
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            KeyStore ks = KeyStore.getInstance("JKS");
            KeyStore tks = KeyStore.getInstance("JKS");

            ks.load(new FileInputStream(getRealPath(CLIENT_KEY_STORE_PATH)), CLIENT_KEY_STORE_PASSWORD.toCharArray());
            tks.load(new FileInputStream(getRealPath(CLIENT_TRUST_KEY_STORE_PATH)), CLIENT_TRUST_KEY_STORE_PASSWORD.toCharArray());
 
            kmf.init(ks, CLIENT_KEY_STORE_PASSWORD.toCharArray());

            tmf.init(tks);
            ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
            
            sslSocket = (SSLSocket) ctx.getSocketFactory().createSocket(host, port);
            logger.info("getSSLSocket结束0");
        } catch (Exception e) {
        	logger.debug("初始化SSLServerSocket时异常",e);
        	logger.info("初始化SSLServerSocket时异常",e);
        }
		return sslSocket;
	}
	public static String getRealPath(String path){
		logger.info("path:"+path);
		String realpath = SSLSocketUtil.class.getResource("/").getPath().toString().replaceAll("file:/", "").replaceAll("%20"," ")+path;
//		String realpath = ClassLoader.getSystemResource("").getPath();
		logger.info("realpath:"+realpath);
		return realpath;
	}
	public static void main(String[] args) {
//		String s = getRealPath("config/config.ini");
//		System.out.println(s);
//		File f =new File(s);
		String path = getRealPath(CLIENT_KEY_STORE_PATH);
		System.out.println(path);;
	}
}
