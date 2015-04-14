/** * A级 */
package com.meiah.core.util;	

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

import com.meiah.entity.sys.UploadFile;

/**
 * 爬取互联网上的网页，并保存为htm格式
 * @author zhsf
 * 
 */
public class UrlToHtmUtil {
	private static Logger logger = Logger.getLogger(UrlToHtmUtil.class);
    private URL strWeb = null; //网页地址
    private String strText = null; //网页文本内容
    private String strFileName = null; //本地文件名
    private String strEncoding = null; //网页编码
    
    public static void main(String[] args) {
    	
    }
   
    public UrlToHtmUtil(String strUrl, String strFileName) {
        try {
        	strUrl = strUrl.trim();
            strWeb = new URL(strUrl);
        } catch (MalformedURLException e) {
            logger.error(e);
            strUrl = "http://"+strUrl;
            try {
				strWeb = new URL(strUrl);
			} catch (MalformedURLException e1) {
				 return;
			}
           
        }
        this.strFileName = strFileName;
    }
    
    
	
    /**
     * 主程序，执行下载，生成htm文件的操作
     */
	public boolean createHtm() {
        if (Validator.isNull(strWeb) || Validator.isNull(strFileName))
            return false;
        try {
        	logger.info("生成HTM文件，URL:"+this.strWeb.toString());
        	strText = getHtmlContent(strWeb.toString());
		} catch (Exception e1) {
			logger.error("抓取网页内容出错", e1);
		}
		if(Validator.isNull(strText)) return false;
		OutputStreamWriter out = null;
        try {
        	File htmlFile = new File(strFileName);;
    		if (!htmlFile.exists())
				htmlFile.createNewFile();
			out = new OutputStreamWriter(new FileOutputStream(htmlFile),strEncoding);
			out.write(strText);
			out.flush();
			out.close();
        } catch (Exception e) {
        	logger.error("生成htm文件出错", e);
            return false;
        }finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
        return true;
    }
	
    
    /**
     * 获取网页内
     * 有些网站会使用gzip压缩网页，例如搜狐，此方法会先判断网页是否用了GZIP压缩，若是用了GZIP压缩，则先进行解压，否则会出现乱码
     * @param htmlurl
     * @return
     * @throws IOException
     * @author liuhm
     */
    public String getHtmlContent(String htmlurl)
		    throws IOException {
    	
    	URI uri = new URI(htmlurl,false,"UTF-8");  
    	
		StringBuffer sb = new StringBuffer();
		String acceptEncoding = "";
		/* 1.生成 HttpClinet 对象并设置参数 */
		HttpClient httpClient = new HttpClient();
		// 设置 Http 连接超时 5s
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(1000*60);
		GetMethod method = new GetMethod(uri.toString());
		// 设置 get 请求超时 5s
		method.getParams().getDoubleParameter(HttpMethodParams.SO_TIMEOUT, 1000*60);
		// 设置请求重试处理
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
		        new DefaultHttpMethodRetryHandler());
		
		String cookieStr = "";
		if(htmlurl.indexOf("t.qq.com") != -1){
			logger.info("Config.qqCookie"+Validator.isNull(Config.qqCookie));
			if(Validator.isNull(Config.qqCookie)){
				Config.qqCookie = CookieUtil.getCookie("qq");
			}
			if(Validator.isNotNull(Config.qqCookie)){
				cookieStr = Config.qqCookie.getCookiestr();
			}
		}
		if(htmlurl.indexOf("weibo.com") != -1){
			logger.info("Config.sinaCookie"+Validator.isNull(Config.sinaCookie));
			if(Validator.isNull(Config.sinaCookie)){
				Config.sinaCookie = CookieUtil.getCookie("sina");
			}
			if(Validator.isNotNull(Config.sinaCookie)){
				cookieStr = Config.sinaCookie.getCookiestr();
			}
		}
		if(Validator.isNotNull(cookieStr))
			method.setRequestHeader("Cookie",cookieStr);
		
		int statusCode;
		try {
		    statusCode = httpClient.executeMethod(method);
		    // 判断访问的状态码
		    if (statusCode != HttpStatus.SC_OK) {
		        return sb.toString();
		    } else {
		    	String[] parseResult;
		    	InputStream is = method.getResponseBodyAsStream();
		        if (acceptEncoding.toLowerCase().indexOf("gzip") > -1) {
		            // 建立gzip解压工作流
		        	is = new GZIPInputStream(is);
		        } 
		        parseResult=JavaUtil.getParseResultAndEncode(is);
	            strEncoding=parseResult[1];
	            sb.append(parseResult[0]);
	            is.close();
		    }
		} catch (HttpException e1) {
		    logger.error(e1);
		} catch (IOException e1) {
		    logger.error(e1);
		}
		method.abort();
		method.releaseConnection();
		return sb.toString();
	}					
    
    
    /**
	  *  获得违法信息htm文件路径和文件名称
	  * @return  返回文件全路径、相对路径、文件名称
	  * @author "zhangshaofeng"  
	  * @time Sep 20, 2012 10:52:33 AM
	  */
	public  static String[] getPathArr(){
		//文件路径
		final String imageDir="upload";//文件目录路径
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/HH/");
		//相对路径
		String relativePath=imageDir+sdf.format(date);
		String saveDirPath= new StringBuilder(Config.imgFilePath+File.separator).append(relativePath).toString();
		File pathF = new File(saveDirPath);
		if (!pathF.exists()) {
			pathF.mkdirs();
		}
		//文件名
		sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_");
		String fileFullName = sdf.format(date);
		Random r = new Random();
		String fileName=String.valueOf(r.nextInt(99999) * 999)+".htm";
		fileFullName = (new StringBuilder(String.valueOf(fileFullName))).append(fileName).toString();
		String savePath=saveDirPath+fileFullName;
		File file = new File(savePath);
		//若文件存在，删除文件，并删除数据库信息
		if (file.exists()) {
			file.delete();
		}
		return (new String[] {savePath,relativePath+fileFullName, fileName});
	}
	  /**
	 * 通过URL，返回UploadFile
	 * @param url
	 * @return
	 * @throws Exception 
	 */
	public static UploadFile getHtmByUrl(String url) throws Exception{
		String[] pathArr=UrlToHtmUtil.getPathArr();
		String fileFullName= pathArr[0];
		if (Validator.isNotNull(url)) {
			UrlToHtmUtil urlToHtmUtil=new UrlToHtmUtil(url, fileFullName);
			urlToHtmUtil.createHtm();
			File file = new File(fileFullName);
			if (file.exists()&&file.length()>0) {
				UploadFile uploadFile=new UploadFile();
				uploadFile.setFilename(pathArr[2]);
				uploadFile.setExtend1(3);//设置为htm类型
				uploadFile.setFilepath(pathArr[1]);//保存相对路径
				uploadFile.setFilesize(file.length());
				return uploadFile;
			}
		}
		return null;
	}
}
