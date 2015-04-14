/** * A级 */
package com.meiah.core.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.CRC32;

import org.apache.log4j.Logger;

/**
 * 基本工具类
 * 
 * @author chenc@inetcop.com.cn
 * @date 2009-08-06
 */
public class JavaUtil {
	private static Logger logger = Logger.getLogger(JavaUtil.class);
	//域名后缀
	static String[] xCN = { ".com.cn", ".net.cn", ".gov.cn", ".edu.cn",
		".org.cn", ".mil.cn", ".ac.cn", ".bj.cn", ".sh.cn", ".tj.cn",
		".cq.cn", ".he.cn", ".sx.cn", ".nm.cn", ".ln.cn", ".jl.cn",
		".hl.cn", ".js.cn", ".zj.cn", ".ah.cn", ".fj.cn", ".jx.cn",
		".sd.cn", ".ha.cn", ".hb.cn", ".hn.cn", ".gd.cn", ".gx.cn",
		".hi.cn", ".sc.cn", ".gz.cn", ".yn.cn", ".xz.cn", ".sn.cn",
		".gs.cn", ".qh.cn", ".nx.cn", ".xj.cn", ".tw.cn", ".hk.cn",
		".mo.cn" };
	
	  public static List<String> matchList(String s, String pattern) {
	        Matcher m = Pattern.compile(pattern).matcher(s);
	        List<String> result = new ArrayList<String>();
	        while (m.find()) {
	            result.add(m.group());
	        }
	        return result;
	    }
	/**
	 * 读取流内容
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static String readStream(InputStream is) throws IOException {
		String cs = null;
		try {
			ByteArrayOutputStream buffer = null;
			BufferedInputStream in = new BufferedInputStream(is);
			buffer = new ByteArrayOutputStream();
			byte[] buff = new byte[1024];
			int len = -1;
			// 从socket连接中获取输出流，主要为请求的响应报头和HTML编码

			while ((len = in.read(buff)) != -1) {
				buffer.write(buff, 0, len);
			}// 由于使用BufferOutputStream会出现一个连接被分割在两行的情况，因此只能利用字节流将所有源代码取得，而后换成String

			logger.debug("get stream over");
			HTMLDecoder htmd = new HTMLDecoder();
			SinoDetect sd = new SinoDetect();

			if (buffer != null) {

				try {
					int i = sd.detectEncoding(buffer.toByteArray());
					cs = buffer.toString(Encoding.htmlname[i]);
				} catch (RuntimeException e) {
					cs = buffer.toString("GBK");
				}
				// try{
				// cs=buffer.toString(Encoding.htmlname[i]);
				// }catch(Exception e){
				// cs=buffer.toString("GBK");
				// }
				cs = cs.replace("&nbsp;", "");
				cs = htmd.ASCIIToGB(cs);
			}
			logger.debug("analyse stream over");

			is.close();
		} catch (IOException e) {
			throw e;
		}
		return cs;
	}
	
	
	
	/**
	 * 读取流内容
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static String[] getParseResultAndEncode(InputStream is) throws IOException {
		String cs = null;
		String encode="GBK";
		try {
			ByteArrayOutputStream buffer = null;
			BufferedInputStream in = new BufferedInputStream(is);
			buffer = new ByteArrayOutputStream();
			byte[] buff = new byte[1024];
			int len = -1;
			// 从socket连接中获取输出流，主要为请求的响应报头和HTML编码

			while ((len = in.read(buff)) != -1) {
				buffer.write(buff, 0, len);
			}// 由于使用BufferOutputStream会出现一个连接被分割在两行的情况，因此只能利用字节流将所有源代码取得，而后换成String

			logger.debug("get stream over");
			HTMLDecoder htmd = new HTMLDecoder();
			SinoDetect sd = new SinoDetect();

			if (buffer != null) {

				try {
					int i = sd.detectEncoding(buffer.toByteArray());
					encode=Encoding.htmlname[i];
					cs = buffer.toString(encode);
				} catch (RuntimeException e) {
					cs = buffer.toString("GBK");
				}
				cs = cs.replace("&nbsp;", "");
				cs = htmd.ASCIIToGB(cs);
			}
			logger.debug("analyse stream over");
			is.close();
		} catch (IOException e) {
			throw e;
		}
		return (new String[] {cs,encode});
	}
	
	/**
	 * 正则匹配，忽略大小写
	 * 
	 * @param s
	 * @param pattern
	 * @return
	 */
	public static String[] matchWeak(String s, String pattern) {
		Matcher m = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(
				s);

		while (m.find()) {
			int n = m.groupCount();
			String[] ss = new String[n + 1];
			for (int i = 0; i <= n; i++) {
				ss[i] = m.group(i);
			}
			return ss;
		}
		return null;
	}
	
	/**
	 * @param txt
	 * @return
	 * 根据title换算出title的CRC32title值
	 */
	public static long getCRC32(String txt) {
		long crc = 0;
		try {
			CRC32 c=new CRC32();
			c.reset();
			c.update(txt.getBytes());
			crc=c.getValue();
		} catch (Exception e) {}
		return crc;
	}
	
	/**
	 * 分值模型
	 * 总分小于等于100的，直接乘以0.8<br>
	 * 总分大于100的，则用80加上总分除以100之后的结果四舍五入之后的整数值，总分除以100之后的结果如果大于等于20则直接取值为19，然后再去与80相加。
	 * @param score
	 * @return
	 */
	public static int calculteTotalScore(int score){
		int result = 0;
		if(100>=score){
		    result=  new BigDecimal(score*0.8).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(); 
		}else{
		    result = new BigDecimal(score/100).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(); 
		    if(result>=20)result=19;
		    result=80+result;
		}
		return result;
	}
	
	/**
	 * 传入任意网址，获取网站域名
	 * @param host
	 * @return
	 */
	public static  String getHost(String host){
		if(Validator.isNull(host)) return "";
		String [] arr = JavaUtil.match(host,"http://([^/]*+\\.?)+?/?");
		if(Validator.isNotNull(arr)) return arr[1];
		else return "";
	}
	
	/**
	 * 正则匹配
	 * 
	 * @param s
	 * @param pattern
	 * @return
	 */
	public static String[] match(String s, String pattern) {
		Matcher m = Pattern.compile(pattern).matcher(s);

		while (m.find()) {
			int n = m.groupCount();
			String[] ss = new String[n + 1];
			for (int i = 0; i <= n; i++) {
				ss[i] = m.group(i);
			}
			return ss;
		}
		return null;
	}
	
	/**
     *  顶级域名
     * @param host
     * @return
     */
	public static String getHost1(String host) {
		host = host.trim().toLowerCase();//格式化
		String domain1 = "";
		if (host.endsWith(".cn")) {
			//判断cn分类域名以及区域域名
			for (int i = 0; i < xCN.length; i++) {
				if (host.endsWith(xCN[i])) {
					host = host.substring(0, host.length() - xCN[i].length());
					String[] _s = host.split("\\.");
					if (_s.length > 0) {
						domain1 = _s[_s.length - 1] + xCN[i];
					}
					return domain1;
				}
			}
			//else if(host.endsWith(".cn")){
			host = host.substring(0, host.length() - 3);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".cn";
			//}
		} else if (host.endsWith(".com")) {
			host = host.substring(0, host.length() - 4);
			String[] _s = host.split("\\.");
			domain1 = _s[_s.length - 1] + ".com";
		}

		else if (host.endsWith(".net")) {
			host = host.substring(0, host.length() - 4);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".net";
		}

		else if (host.endsWith(".org")) {
			host = host.substring(0, host.length() - 4);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".org";
		}

		else if (host.endsWith(".gov")) {
			host = host.substring(0, host.length() - 4);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".gov";
		}

		else if (host.endsWith(".edu")) {
			host = host.substring(0, host.length() - 4);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".edu";
		}

		else if (host.endsWith(".biz")) {
			host = host.substring(0, host.length() - 4);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".biz";
		}

		else if (host.endsWith(".tv")) {
			host = host.substring(0, host.length() - 3);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".tv";
		}

		else if (host.endsWith(".cc")) {
			host = host.substring(0, host.length() - 3);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".cc";
		}

		else if (host.endsWith(".be")) {
			host = host.substring(0, host.length() - 3);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".be";
		}

		else if (host.endsWith(".info")) {
			host = host.substring(0, host.length() - 5);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".info";
		}

		else if (host.endsWith(".name")) {
			host = host.substring(0, host.length() - 5);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".name";
		}

		else if (host.endsWith(".co.uk")) {
			host = host.substring(0, host.length() - 6);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".co.uk";
		}

		else if (host.endsWith(".me.uk")) {
			host = host.substring(0, host.length() - 6);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".me.uk";
		}

		else if (host.endsWith(".org.uk")) {
			host = host.substring(0, host.length() - 7);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".org.uk";
		}

		else if (host.endsWith(".ltd.uk")) {
			host = host.substring(0, host.length() - 7);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".ltd.uk";
		}

		else if (host.endsWith(".plc.uk")) {
			host = host.substring(0, host.length() - 7);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".plc.uk";
		}
		return domain1;
	}
	
	/**
	 * 正则虚拟人物网站地址
	 * @param url
	 * @return
	 */
	public static String getAfterFilterUrl(String url){
		if (url.endsWith("/")){
			url = url.substring(0, url.length() - 1);
		}
		url = url.replaceAll("http://|https://", "");
		return url;
	}
	
	
	/**
	 * 顶级域名
	 * 
	 * @param host
	 * @return
	 */
	public static String getTopdomain(String host) {
		host = host.trim().toLowerCase();// 格式化
		String domain1 = "";
		if (host.endsWith(".cn")) {
			// 判断cn分类域名以及区域域名
			for (int i = 0; i < xCN.length; i++) {
				if (host.endsWith(xCN[i])) {
					host = host.substring(0, host.length() - xCN[i].length());
					String[] _s = host.split("\\.");
					if (_s.length > 0) {
						domain1 = _s[_s.length - 1] + xCN[i];
					}
					return domain1;
				}
			}
			// else if(host.endsWith(".cn")){
			host = host.substring(0, host.length() - 3);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".cn";
			// }
		} else if (host.endsWith(".com")) {
			host = host.substring(0, host.length() - 4);
			String[] _s = host.split("\\.");
			domain1 = _s[_s.length - 1] + ".com";
		}

		else if (host.endsWith(".net")) {
			host = host.substring(0, host.length() - 4);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".net";
		}

		else if (host.endsWith(".org")) {
			host = host.substring(0, host.length() - 4);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".org";
		}

		else if (host.endsWith(".gov")) {
			host = host.substring(0, host.length() - 4);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".gov";
		}

		else if (host.endsWith(".edu")) {
			host = host.substring(0, host.length() - 4);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".edu";
		}

		else if (host.endsWith(".biz")) {
			host = host.substring(0, host.length() - 4);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".biz";
		}

		else if (host.endsWith(".tv")) {
			host = host.substring(0, host.length() - 3);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".tv";
		}

		else if (host.endsWith(".cc")) {
			host = host.substring(0, host.length() - 3);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".cc";
		}

		else if (host.endsWith(".be")) {
			host = host.substring(0, host.length() - 3);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".be";
		}

		else if (host.endsWith(".info")) {
			host = host.substring(0, host.length() - 5);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".info";
		}

		else if (host.endsWith(".name")) {
			host = host.substring(0, host.length() - 5);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".name";
		}

		else if (host.endsWith(".co.uk")) {
			host = host.substring(0, host.length() - 6);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".co.uk";
		}

		else if (host.endsWith(".me.uk")) {
			host = host.substring(0, host.length() - 6);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".me.uk";
		}

		else if (host.endsWith(".org.uk")) {
			host = host.substring(0, host.length() - 7);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".org.uk";
		}

		else if (host.endsWith(".ltd.uk")) {
			host = host.substring(0, host.length() - 7);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".ltd.uk";
		}

		else if (host.endsWith(".plc.uk")) {
			host = host.substring(0, host.length() - 7);
			String[] _s = host.split("\\.");
			if (_s.length > 0)
				domain1 = _s[_s.length - 1] + ".plc.uk";
		}
		return domain1;
	}
	
	/**
	    * 获取标准格式的guid
	    * @param guid
	    * @return
	    * @author raolp
	    */
	   public static String getFormatGUID(String guid){
		   String result = UUID.randomUUID().toString();
	    try {
			if(Validator.isNotNull(guid)){
				 String [] guidArr = JavaUtil.match(guid,"([0-9a-fA-F]{8})([0-9a-fA-F]{4})([0-9a-fA-F]{4})([0-9a-fA-F]{4})([0-9a-fA-F]{12})");
				 result = guidArr[1]+"-"+guidArr[2]+"-"+guidArr[3]+"-"+guidArr[4]+"-"+guidArr[5];
			}
		}catch (Exception e) {
			logger.error("转化标准GUID出错",e);
		}
		   return result;
	   }
	
	public static String subStr(String str,String pattern){
		return str.substring(0,str.lastIndexOf(pattern));
	}
	
	/**
	 * 获得UUID
	 * @return
	 * @author "zhangshaofeng"  
	 * @time Jun 17, 2012 2:23:22 PM
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");  
	}
	
	/**
	 * 任意格式的分隔符的字符串转化为list<Map<String,Object>>对象
	 * @param string
	 * @return
	 */
	public static List<Map<String,Object>> convertStringToList(String string,String pattern){
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		if(string.contains(pattern)){
			String[] strArr = string.split(pattern);
			for (int i = 0; i < strArr.length; i++) {
				Map<String,Object> map =new HashMap<String, Object>();
				map.put("key",strArr[i]);
				//   搜搜:10 百度:20 谷歌:30 必应:40 奇虎:50 搜狗：60
				String sename = "";
				if(strArr[i].equals("10")){
					sename="搜搜";
				}
				if(strArr[i].equals("20")){
					sename="百度";			
				}
				if(strArr[i].equals("30")){
					sename="谷歌";
				}
				if(strArr[i].equals("40")){
					sename="必应";
				}
				if(strArr[i].equals("50")){
					sename="奇虎";
				}
				if(strArr[i].equals("60")){
					sename="搜狗";
				}
				map.put("value",sename);				
				result.add(map);
			}
		}
		return result;
	}
	
//	/**
//	 * 根据数据源编号归类数据源
//	 * @param sourcelist
//	 * @return
//	 * @author raolp
//	 */
//	public static List<SysSource> getGroupSysSourceByCode(List<SysSource> sourcelist){
//		List<SysSource> result =new ArrayList<SysSource>(); 
//		String oversea = ","+Config.overseasourcestr+",";
//		try {
//			if (Validator.isNotNull(sourcelist)) {
//				String overseascode = "";
//				SysSource overseaSource = new SysSource();
//				for (SysSource sysSource : sourcelist) {
//					String code =","+sysSource.getCode()+",";
//					if(oversea.contains(code)){
//						if (overseascode.equals("")) {
//							overseascode += sysSource.getCode().toString(); 
//						}else{
//							overseascode += ","+sysSource.getCode().toString();
//						}
//						overseaSource.setOverseascode(overseascode);
//						overseaSource.setName("境外");
//					}
//					if(sysSource.getCode()!=61&&!oversea.contains(code)){
//						result.add(sysSource);
//					}
//				} 
//				if(Validator.isNotNull(overseaSource.getOverseascode())){
//					result.add(overseaSource);
//				}
//			}
//		} catch (Exception e) {
//			logger.error("归类数据来源出错",e);
//		}
//		return result;
//	}
//	
//	
//	/**
//	 * 字符串转化为list对象
//	 * @param string
//	 * @return
//	 */
//	public static List<Map<String,Object>> convertStringToList(String string){
//		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
//		if(Validator.isNull(string)) {
//			return result;
//		}
//		if(string.contains("|")){
//			String[] strArr = string.split("\\|");
//			for (int i = 0; i < strArr.length; i++) {
//				Map<String,Object> map =new HashMap<String, Object>();
//				map.put("key",strArr[i]);
//				map.put("value",strArr[i]);
//				result.add(map);
//			}
//		}
//		return result;
//	}
//	
//	   /**
//	 * 检查文件大小是否符合标准
//	 * @param fileSize
//	 * @return
//	 */
//	public static boolean checkFileSize(File file){
//		    boolean result =true;
//		try {
//			String size = FileUtils.FormetFileSize(file.length());
//			Long limit = Long.parseLong((null==OptionForharaminfo.sizeLimit)?"20":OptionForharaminfo.sizeLimit);
//			if(size.endsWith("M")){
//				String fileS = size.substring(0,(size.indexOf("M")));
//				double f = Double.parseDouble(fileS);
//				if(f>limit){
//					result = false;
//				}else{
//					result = true;
//				} 
//			}else if(size.endsWith("B")||size.endsWith("K")){
//					result = true;
//			}else if(size.endsWith("G")){
//					result = false;
//			}else{
//					result = false;
//			}
//		} catch (Exception e) {
//			logger.error("检查文件大小出错",e);
//		}
//		return result;
//	}
//	
//	 
//	public static String convertAreaCodeToString(String codes){
//		String result ="";
//		if(Validator.isNotNull(codes)){
//			if("1".equals(codes)||"-1".equals(codes)||"100000000".equals(codes)){result = "全国";}
//			else{
//					String[] coString = codes.split(",");
//					for (int i = 0; i < coString.length; i++) {
//						result +=(0==i?SysConstants.getAreaName(Integer.parseInt(coString[i])):" "+SysConstants.getAreaName(Integer.parseInt(coString[i])));
//					}
//			}
//		}
//		return result;
//	}
}
