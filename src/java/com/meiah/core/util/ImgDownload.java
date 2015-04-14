/** * A级 */
package com.meiah.core.util;

import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.log4j.Logger;

import com.meiah.entity.sys.UploadFile;
  
public class ImgDownload {  
	
	private static Logger logger = Logger.getLogger(ImgDownload.class);
    // 生成图片函数  
    public  static UploadFile makeImg(String imgUrl) {  
        try {  
        	logger.info("开始下载图片:"+imgUrl);
            // 创建流  
            BufferedInputStream in = new BufferedInputStream(new URL(imgUrl).openStream());  
            // 生成图片名  
            int index = imgUrl.lastIndexOf("/");  
            String sName = imgUrl.substring(index+1, imgUrl.length());
            
            if(sName.indexOf(".") == -1){
            	sName = DateUtil.getIncrementNo();
            }
            
            String[] fielURLs = imgPath(sName);
            System.out.println(sName);  
            // 存放地址  
            File img = new File(fielURLs[1]);  
            // 生成图片  
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(img));  
            byte[] buf = new byte[2048];  
            int length = in.read(buf);  
            while (length != -1) {  
                out.write(buf, 0, length);  
                length = in.read(buf);  
            }  
            in.close();  
            out.close(); 
            logger.info("结束下载图片:"+imgUrl);
            UploadFile uploadFile = new UploadFile();
            uploadFile.setExtend1(0);
            uploadFile.setFilename(sName);
            uploadFile.setFilepath(fielURLs[0]);
            return uploadFile;
        } catch (Exception e) {  
        	logger.error("下载图片出错",e);
            e.printStackTrace();  
        }
		return null;  
    } 
    public static String[] imgPath(String fname){
		String name = "";
		String extName = "";
		Date date = new Date();
		String savePath = Config.imgFilePath;
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/HH/");
		String filePathName = (new StringBuilder("upload")).append(sdf.format(date)).toString();
		savePath = (new StringBuilder(String.valueOf(savePath))).append(filePathName).toString();
		(new File(savePath)).mkdirs();
		if (fname.lastIndexOf(".") >= 0)
			extName = fname.substring(fname.lastIndexOf("."));
		sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_");
		name = sdf.format(date);
		Random r = new Random();
		name = (new StringBuilder(String.valueOf(name))).append(String.valueOf(r.nextInt(99999) * 999)).toString();
		String  filePath = (new StringBuilder(String.valueOf(savePath))).append(name).append(extName).toString();
	 
		return new String[]{filePathName+name+extName,filePath};
	}
    public static void main(String[] args) {
		makeImg("http://t1.qpic.cn/mblogpic/3ffe126d0e953bb85f18/80");
	}
}
