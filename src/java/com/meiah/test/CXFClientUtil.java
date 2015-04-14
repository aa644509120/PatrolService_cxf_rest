/** * A级 */
package com.meiah.test;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.configuration.security.FiltersType;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import com.meiah.core.util.SSLSocketUtil;

public class CXFClientUtil {

    public static final int CXF_CLIENT_CONNECT_TIMEOUT = 30 * 1000;
    public static final int CXF_CLIENT_RECEIVE_TIMEOUT = 60 * 1000;

    public static void setTimeout(Object service) {
        Client proxy = ClientProxy.getClient(service);
        
//        WSS4JOutHandler wsOut = new WSS4JOutHandler(); 
//        proxy.addOutHandler(new DOMOutHandler());
//		Properties properties = new Properties();
//		properties.setProperty(WSHandlerConstants.ACTION,WSHandlerConstants.USERNAME_TOKEN);
//		properties.setProperty(WSHandlerConstants.PASSWORD_TYPE,WSConstants.PW_TEXT);
//		properties.setProperty(WSHandlerConstants.USER,"PATROL");
//		properties.setProperty(WSHandlerConstants.PW_CALLBACK_CLASS,ClientHandler.class.getName());
//		Client.addOutHandler(wsOut.setP(properties));
        
        HTTPConduit conduit = (HTTPConduit) proxy.getConduit();
        HTTPClientPolicy policy = new HTTPClientPolicy();
        policy.setConnectionTimeout(CXF_CLIENT_CONNECT_TIMEOUT);
        policy.setReceiveTimeout(CXF_CLIENT_RECEIVE_TIMEOUT);
        conduit.setClient(policy);
        configureSSLOnTheClient(service);
    }
    
    
	public static void configureSSLOnTheClient(Object obj) { 
	    File file = new File(CXFClientUtil.getRealPath("tomcat.jks")); 
	     
	    Client client = ClientProxy.getClient(obj); 
	    HTTPConduit httpConduit = (HTTPConduit) client.getConduit(); 
	
	    try { 
	        TLSClientParameters tlsParams = new TLSClientParameters(); 
	        tlsParams.setDisableCNCheck(true); 
	
	        KeyStore keyStore = KeyStore.getInstance("JKS"); 
	        String password = "pico2009server"; 
	        String storePassword = "pico2009server"; 
	         
	        keyStore.load(new FileInputStream(file), storePassword.toCharArray()); 
	        TrustManagerFactory trustFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm()); 
	        trustFactory.init(keyStore); 
	        TrustManager[] trustManagers = trustFactory.getTrustManagers(); 
	        tlsParams.setTrustManagers(trustManagers); 
	        keyStore.load(new FileInputStream(file), storePassword.toCharArray()); 
	        KeyManagerFactory keyFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm()); 
	        keyFactory.init(keyStore, password.toCharArray()); 
	        KeyManager[] keyManagers = keyFactory.getKeyManagers(); 
	        tlsParams.setKeyManagers(keyManagers); 
	        FiltersType filtersTypes = new FiltersType(); 
	        filtersTypes.getInclude().add(".*_EXPORT_.*"); 
	        filtersTypes.getInclude().add(".*_EXPORT1024_.*"); 
	        filtersTypes.getInclude().add(".*_WITH_DES_.*"); 
	        filtersTypes.getInclude().add(".*_WITH_NULL_.*"); 
	        filtersTypes.getExclude().add(".*_DH_anon_.*"); 
	        tlsParams.setCipherSuitesFilter(filtersTypes); 
	        httpConduit.setTlsClientParameters(tlsParams); 
	    } catch (Exception e) { 
	        e.printStackTrace(); 
	    } 
	} 
	public static String getRealPath(String path){
		String realpath = SSLSocketUtil.class.getResource("/").getPath().toString().replaceAll("file:/", "").replaceAll("%20"," ")+path;
		return realpath;
	}
	public static void main(String[] args) {
		 System.out.println(CXFClientUtil.getRealPath("tomcat.jks"));
	}
}

 
