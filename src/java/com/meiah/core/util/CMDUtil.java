/** * A级 */
/**  
* @Title CMDUtil.java  
* @Package com.meiah.core.util  
* @Description  
* @author "zhangshaofeng"  
* @time Sep 21, 2011 11:03:29 AM  
*/ 


package com.meiah.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;


/**
 * @author "zhangshaofeng"
 *
 */
public class CMDUtil {
	
	private static Logger logger = Logger.getLogger(CMDUtil.class);
	
	public static void test() {
//		   String name1 = "C:/WINDOWS/system32/winmine.exe";
//		   String name2 = "C:/test.cmd";//我CMD里面的内容是 netstat -ano 其他的也可以
//		   String name3 = "java -version";
		   String name4="D:\\IECapt --url=http://172.16.1.199:88/default.asp --out=D:\\out.jpg";
		   try {
		   // System.out.println(execCMD(name1, 3));
		    //System.out.println(execCMD(name2, 2));
		    //System.out.println(execCMD(name3, 1));
			// System.out.println(execCMD(name4, 1));
			   execBat(name4);
		   } catch (Exception e) {
		    e.printStackTrace();
		   }
		}
	
	/**
	* 
	* @param path 要执行的文件路径或命令
	* @param type 执行类型 1 shell命令 2 .CMD文件 3 .EXE文件
	* @return 返回的值
	* @throws IOException
	*/
	public static String execCMD(String path, int type) throws IOException {
	   final String COM_TITLE = "CMD /c ";
	   if (type == 1) {
	    path = COM_TITLE + path;
	   }
	   Process pro = Runtime.getRuntime().exec(path);
	   BufferedInputStream br = new BufferedInputStream(pro.getInputStream());
	   BufferedInputStream br1 = new BufferedInputStream(pro.getErrorStream());
	   int ch;
	   StringBuffer text = new StringBuffer("获得的信息是: \n");
	   while ((ch = br.read()) != -1) {
	    text.append((char) ch);
	   }

	   StringBuffer text1 = new StringBuffer("获得的错误信息是: \n");
	   while ((ch = br1.read()) != -1) {
	    text1.append((char) ch);
	   }
	   return text.length() > 9 ? text.toString() : text1.toString();
	}
	
	public static void execCMDFLUSH(String path){
		final String COM_TITLE = "CMD /c ";
		path = COM_TITLE + path;
		Process p = null ;
		try {
		  p = Runtime.getRuntime().exec(path);
		 //获取进程的标准输入流  
		 final InputStream is1 = p.getInputStream();   
		 //获取进城的错误流  
		 final InputStream is2 = p.getErrorStream();  
		 //启动两个线程，一个线程负责读标准输出流，另一个负责读标准错误流  
		 new Thread() {
		    public void run() {  
		       BufferedReader br1 = new BufferedReader(new InputStreamReader(is1));  
		        try {  
		            String line1 = null;  
		            while ((line1 = br1.readLine()) != null) {  
		                  if (line1 != null){}  
		              }  
		        } catch (IOException e) {  
		             e.printStackTrace();  
		        }  
		        finally{  
		             try {  
		               is1.close();  
		             } catch (IOException e) {  
		                e.printStackTrace();  
		            }  
		          }  
		        }  
		     }.start();  
		                                
		   new Thread() {
		      public void  run() {   
		       BufferedReader br2 = new  BufferedReader(new  InputStreamReader(is2));   
		          try {   
		             String line2 = null ;   
		             while ((line2 = br2.readLine()) !=  null ) {   
		                  if (line2 != null){}  
		             }   
		           } catch (IOException e) {   
		                 e.printStackTrace();  
		           }   
		          finally{  
		             try {  
		                 is2.close();  
		             } catch (IOException e) {  
		                 e.printStackTrace();  
		             }  
		           }  
		        }   
		      }.start();    
		                                
		      p.waitFor();  
		      p.destroy();   
		    } catch (Exception e) {
		            try{  
		                p.getErrorStream().close();  
		                p.getInputStream().close();  
		                p.getOutputStream().close();  
		                }  
		             catch(Exception ee){}  
		          }  
		   }
	public static void  execCMD(String path) throws Exception {
	   final String COM_TITLE = "CMD /c ";
		 path = COM_TITLE + path;
		 Process pro = Runtime.getRuntime().exec(path);
		 pro.waitFor();
	}
	//生成bat执行截图
	public static void execBat(String content) {
		logger.info("生成bat执行截图开始。。。");
		try {
			String batPath = getProjectPath()+"bat\\" + System.currentTimeMillis() +".bat";
			//创建一个bat文件对象
			File datFile = new File(batPath);
			logger.info("bat路径为："+batPath);
//			File datFile = new File("d:/" + System.currentTimeMillis() +".bat");
			//若该bat文件不存在，则进行如下操作
			if (!datFile.exists()) {
				//新建bat文件
				datFile.createNewFile();
				//新建一个字符串，用来存储bat文件内容
				StringBuffer sb = new StringBuffer();
				//>>>>以下组装相关命令行操作语句
				sb.append(content);
				//将bat内容读入bat文件输出流
				FileOutputStream out = new FileOutputStream(datFile);
				//将输出流中的内容写入到文件中去
				out.write(sb.toString().getBytes());
				//刷新
				out.flush();
				//关闭文件输出流
				out.close();
			}
//			Thread.sleep(1000);
			//创建一个系统运行时对象
			Runtime run = Runtime.getRuntime();
			//>>>>以下组装dos命令行来运行bat文件
			String command = ""; //"cmd /c ";
			command += datFile.getAbsolutePath();
			//启动命令行语句
			run.exec(command);
			Thread.sleep(20*1000);
			//删除bat文件
			datFile.delete();
		} catch (Exception e) {
			 logger.info("bat执行截图失败",e);
		}
		logger.info("生成bat执行截图结束。。。");
	}
	
	public static void main(String[] args) {
		CMDUtil.test();
	}
	
	public static String getProjectPath(){
		File classPath = new File(CutImageUtil.class.getResource("/").toString().substring(6));
		return  classPath.getParentFile().getParent()+File.separator;
	}
	
}
