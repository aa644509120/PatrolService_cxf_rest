/** * A级 */
package com.meiah.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Date;

import javax.net.ssl.SSLSocket;

import org.apache.log4j.Logger;

public class CookieUtil {
	private static Logger logger=Logger.getLogger(CookieUtil.class);
	private static SSLSocket socket ;
 
	public static MyCookie getCookie(String btype){
		logger.info("开始getCookie:"+btype);
		MyCookie cookie = null;
	    try {
	    	logger.info("Config.server_ip："+Config.server_ip+"");
	    	socket = SSLSocketUtil.getSSLSocket(Config.server_ip, Config.socket_port);
	    	logger.info("getSSLSocket结束");
            BufferedReader in = getReader(socket);
            PrintWriter out = getWriter(socket);
            
            String preCMD = "[0]["+new Date().getTime()+"][0][PRECMD]";
            
            logger.info("preCMD:"+preCMD);
            
            out.println(preCMD);
            out.flush();
//          logger.info("发送命令："+preCMD);
            String ready = in.readLine();
//          logger.info("接受命令："+ready);
            if(ready.toLowerCase().equals("ready")){
            	String getCMD = "[0]["+new Date().getTime()+"][0][0,"+btype+"]";
            	out.println(getCMD);
                out.flush();
                logger.info("发送命令："+getCMD);
                String cookieRET = in.readLine();
                logger.info("接受命令："+cookieRET);
                out.println("end");
                out.flush();
                logger.info("发送命令：end");
                cookie = getCookieStr(cookieRET);
            }
		}
	    catch (Exception e) {
	    	logger.debug("请求cookie时出错",e);
		}finally{
			if(socket!=null){
				try {
					logger.info("关闭连接");
					socket.close();
				} catch (IOException e) {
				}
			}
		}
		return cookie;
	}
	  
    private static MyCookie getCookieStr(String cookieRET) {
    	String[] t=JavaUtil.match(cookieRET,"\\[([0|1])\\]\\[(\\d+)\\]\\[(\\d+)\\]\\[([^\\]]*)\\]");
    	String content = t[4];
    	if(content.trim().equals("")){
    		logger.info("服务器返回空的cookie");
    		return null;
    	}
    	else{
    		String[] cs = content.split(",");
    		String btype = cs[1];
        	Long aid = Long.valueOf(cs[2]);
        	Long cookieid = Long.valueOf(cs[3]);
        	String cookiestr = cs[4];
        	try {
    			cookiestr = Base64.decode(cookiestr);
    		} catch (UnsupportedEncodingException e) {
    		}
    		MyCookie cookie = new MyCookie();
    		cookie.setAid(aid);
    		cookie.setBtype(btype);
    		cookie.setCookieid(cookieid);
    		cookie.setCookiestr(cookiestr);
    		cookie.setIssueddate(new Date());
    		return cookie;
    	}
	}
    
	public static MyCookie reportCookie(String btype,Long aid,Long cookieid){
		MyCookie cookie = null;
	    try {
	    	
	    	socket = SSLSocketUtil.getSSLSocket(Config.server_ip, Config.socket_port);
            BufferedReader in = getReader(socket);
            PrintWriter out = getWriter(socket);
            
            String preCMD = "[0]["+new Date().getTime()+"][0][PRECMD]";
            out.println(preCMD);
            out.flush();
            logger.debug("发送命令："+preCMD);
            
            String ready = in.readLine();
            logger.debug("接受命令："+ready);
            if(ready.toLowerCase().equals("ready")){
            	String getCMD = "[0]["+new Date().getTime()+"][0][1,"+btype+","+aid+","+cookieid+"]";
            	out.println(getCMD);
                out.flush();
                logger.debug("发送命令："+getCMD);
                
                String cookieRET = in.readLine();
                logger.debug("接受命令："+cookieRET);
                out.println("end");
                out.flush();
                logger.debug("发送命令：end");
                cookie = getCookieStr(cookieRET);
            }
		}
	    catch (Exception e) {
	    	logger.debug("上报cookie时出错",e);
		}finally{
			if(socket!=null){
				try {
					logger.info("关闭连接");
					socket.close();
				} catch (IOException e) {
				}
			}
		}
		return cookie;
	}
    
	private static PrintWriter getWriter(Socket socket) throws IOException{
        OutputStream socketOut=socket.getOutputStream();
        return new PrintWriter(socketOut,true);
    }
    
    private static BufferedReader getReader(Socket socket) throws IOException{
        InputStream socketIn=socket.getInputStream();
        return new BufferedReader(new InputStreamReader(socketIn));
    }
    
    public static void main(String[] args) {
		 CookieUtil.getCookie("qq");
	}
}
