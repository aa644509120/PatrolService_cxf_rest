/** * A级 */
/**
 * 
 */
package com.meiah.core.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.log4j.Logger;

import com.meiah.entity.sys.UploadFile;


/**访问URL，将网页存为mht格式的文件
 * @author "zhangshaofeng"
 *
 */
public class UrlToMhtUtil {

	public static Logger logger = Logger.getLogger(UrlToMhtUtil.class);
	
	
	
	
	 /**
	  *  获得违法信息图片路径和文件名称
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
		String fileName=String.valueOf(r.nextInt(99999) * 999)+".mht";
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
		File classPath = new File(UrlToMhtUtil.class.getResource("/").toString().substring(6));
		return  classPath.getParentFile().getParent()+File.separator;
	}
	
	/**
	 * 通过URL，返回UploadFile
	 * @param url
	 * @return
	 * @throws Exception 
	 */
	public static UploadFile getMhtByUrl(String url) throws Exception{
		String[] pathArr=UrlToMhtUtil.getPathArr();
		String fileFullName= pathArr[0];
		if (Validator.isNotNull(url)) {
			Html2MHTCompiler h2t = new Html2MHTCompiler(url, fileFullName);
			h2t.compile();
			File file = new File(fileFullName);
			if (file.exists()&&file.length()>0) {
				UploadFile uploadFile=new UploadFile();
				uploadFile.setFilename(pathArr[2]);
				uploadFile.setExtend1(2);//设置为mht类型
				uploadFile.setFilepath(pathArr[1]);//保存相对路径
				uploadFile.setFilesize(file.length());
				return uploadFile;
			}
		}
		return null;
	}
}
