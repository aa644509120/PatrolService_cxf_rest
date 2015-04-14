/** * A级 */
package com.meiah.core.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;

import com.meiah.entity.sys.UploadFile;
 
public class CutImageUtil {

	public static Logger logger = Logger.getLogger(CutImageUtil.class);
	
	 /**
	  *  获得违法信息图片路径和文件名称
	  * @return  返回文件全路径、相对路径、文件名称
	  * @author "zhangshaofeng"  
	  * @time Sep 20, 2012 10:52:33 AM
	  */
	public  static String[] getImgPathArr(){
		//文件路径
		final String imageDir="upload";//文件目录路径
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/HH/");
		//相对路径
		String relativePath=imageDir+sdf.format(date);
		String saveDirPath=new StringBuilder(Config.imgFilePath+File.separator).append(relativePath).toString();
		File pathF = new File(saveDirPath);
		if (!pathF.exists()) {
			pathF.mkdirs();
		}
		//文件名
		sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_");
		String fileFullName = sdf.format(date);
		Random r = new Random();
		String fileName=String.valueOf(r.nextInt(99999) * 999)+".jpg";
		fileFullName = (new StringBuilder(String.valueOf(fileFullName))).append(fileName).toString();
		String savePath=saveDirPath+fileFullName;
		File file = new File(savePath);
		//若文件存在，删除文件，并删除数据库信息
		if (file.exists()) {
			file.delete();
		}
		return (new String[] {savePath,relativePath+fileFullName, fileName});
	}
	
	
	public static String getProjectPath(){
		File classPath = new File(CutImageUtil.class.getResource("/").toString().substring(6));
		return  classPath.getParentFile().getParent()+File.separator;
	}
	
	
	/**
	 * 通过URL，返回UploadFile
	 * @param url
	 * @return
	 * @throws Exception 
	 */
	public static UploadFile cutImageByUrl(String url) throws Exception{
		url = url.trim();
		String[] imgPathArr=CutImageUtil.getImgPathArr();
		String fileFullName= imgPathArr[0];
		if (Validator.isNotNull(url)) {
			if (url.indexOf("http://")==-1) {
				url="http://"+url;
			}
			String cookieStr="";
			logger.info("url:"+url);
			if(url.indexOf("t.qq.com") != -1){
				logger.info("Config.qqCookie"+Validator.isNull(Config.qqCookie));
				if(Validator.isNull(Config.qqCookie)){
					Config.qqCookie = CookieUtil.getCookie("qq");
				}
				if(Validator.isNotNull(Config.qqCookie)){
					cookieStr = Config.qqCookie.getCookiestr();
				}
			}
			if(url.indexOf("weibo.com") != -1){
			logger.info("Config.sinaCookie"+Validator.isNull(Config.sinaCookie));
				if(Validator.isNull(Config.sinaCookie)){
					Config.sinaCookie = CookieUtil.getCookie("sina");
				}
				if(Validator.isNotNull(Config.sinaCookie)){
					cookieStr = Config.sinaCookie.getCookiestr();
				}
			}
//			String urlParam = "";//构造"--cookie_name=username" "--cookie_value=admin" "--cookie_name=host" "--cookie_value=127.0.0.1"
			logger.info("cookieStr:"+cookieStr  );
			String iniParam="";
			String configFile =getProjectPath()+"ini\\"+System.currentTimeMillis()+Math.random()+".ini";
			//分割cookie
			if(Validator.isNotNull(cookieStr)){
				iniParam = " --cookie_ini=\"";
				Map<String, Map<String, Object>> iniFile = new HashMap<String, Map<String, Object>>();  
				Map<String, Object> cookieMap = new HashMap<String, Object>();
				String[] cookies = cookieStr.split(";");
				int i = 0;
				for (String cookie : cookies) {
					String[] cks = cookie.split("=");
					if(cks.length == 2){
						cookieMap.put("name"+i, cks[0]);
						cookieMap.put("valute"+i, cks[1]);
						i++;
					} 
				}
				if(i == 0){
					cookieMap.put("Count", 0+"");
				}else{
					cookieMap.put("Count", i+"");
				}
				iniFile.put("Setup", cookieMap);
				StringBuilder sb = new StringBuilder("");  
		        for (String section : iniFile.keySet()) {  
		            sb.append("[").append(section).append("]").append("\r\n");  
		            Map<String, Object> map = iniFile.get(section);  
		            Set<String> keySet = map.keySet();  
		            for (String key : keySet) {  
		                sb.append(key).append("=").append(map.get(key)).append("\r\n");  
		            }  
		        }  
		        File file = new File(configFile);
		        File parent = file.getParentFile();
	            if (!parent.exists()) {
	                parent.mkdirs();
	            }
		        if (!file.exists()) {  
		            file.createNewFile();  
		        }  
		        try {  
		            OutputStream os = new FileOutputStream(file);  
		            os.write(sb.toString().getBytes());  
		            os.flush();  
		            os.close();  
		        } catch (IOException e) {  
		            e.printStackTrace();  
		        }  
				iniParam += configFile+"\"";
			}
			File iniFile = new File(configFile);
			String cmdString  = "";
			if(!iniFile.exists()){
				cmdString = getProjectPath()+ "IECapt" + File.separator + "IECapt" + " --url=\"" + url + "\" --out=\"" + fileFullName
				+ "\" --min-width=1024 --max-wait=20000 --delay="+Config.CUTIMAGETIME+" --silent";
			}else{
				cmdString = getProjectPath()+ "IECapt" + File.separator + "IECapt" + " --url=\"" + url + "\" --out=\"" + fileFullName+"\""
				+iniParam +" --min-width=1024 --max-wait=20000 --delay="+Config.CUTIMAGETIME+" --silent";
			}
			
			logger.info(cmdString);
			CMDUtil.execCMDFLUSH(cmdString);
			logger.info("fileFullName:"+fileFullName);
			File file = new File(fileFullName);
			logger.info("file exists:"+file.exists());
			if (file.exists()) {
				UploadFile uploadFile=new UploadFile();
				logger.info("1、Filename"+imgPathArr[2]);
				uploadFile.setFilename(imgPathArr[2]);
				uploadFile.setExtend1(1);//设置为图片类型
				logger.info("2、Filepath"+imgPathArr[2]);
				uploadFile.setFilepath(imgPathArr[1]);//保存相对路径
				uploadFile.setFilesize(file.length());
				return uploadFile;
			}
			
		}
		return null;
	}
}
