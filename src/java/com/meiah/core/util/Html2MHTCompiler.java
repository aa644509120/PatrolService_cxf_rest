/** * A级 */
package com.meiah.core.util;	

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPInputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.MimetypesFileTypeMap;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.lexer.Lexer;
import org.htmlparser.lexer.Page;
import org.htmlparser.util.DefaultParserFeedback;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * 爬取互联网上的网页，并保存为mht格式
 * @author liuhm
 * 
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class Html2MHTCompiler extends Thread {
	private static Logger logger = Logger.getLogger(Html2MHTCompiler.class);
    private URL strWeb = null; //网页地址
    private String strText = null; //网页文本内容
    private String strFileName = null; //本地文件名
    private String strEncoding = null; //网页编码
    private String[] fileArr = null;//保存附件信息的数组
    //mht格式附加信息
    private String from = "meiya";
    private String to;
    private String subject = "mht compile";
    private String cc;
    private String bcc;
    private String smtp = "localhost";
    private String USER_AGENT = "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.648; .NET CLR 3.5.21022; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)";
    
    public static void main(String[] args) {
//        String strUrl = "http://www.mtime.com/my/tropicofcancer/blog/843555";
    	long _time = new Date().getTime();
    	Properties props = new Properties();
		try {
			InputStream istream = Html2MHTCompiler.class.getResourceAsStream("/log4j.properties");
			props.load(istream);
			istream.close();
			props.setProperty("log4j.appender.htmlScratcher.File", "log/html2mht.log");

			// 重新配置后，日志会打到新的文件去。
			PropertyConfigurator.configure(props);// 装入log4j配置信息
		} catch (Exception e) {
			logger.error("装入属性文件异常 Exception ", e);
		}
    	List<String> urlsList = new ArrayList<String>();
//		urlsList.add("http://weibo.com/2400764673/z7EMLBAAG");
//		urlsList.add("http://weibo.com/2075134903/z7RQRCpyR");
//		urlsList.add("http://t.163.com/3972600732/status/7076989724005058315");
//		urlsList.add("http://t.163.com/8922853067/status/2942750600979327203");
//		urlsList.add("http://t.163.com/3402490231/status/-1171960561359920098");
//		urlsList.add("http://blog.tianya.cn/blogger/post_show.asp?BlogID=4572706&PostID=48592940");
//		urlsList.add("http://club.china.com/data/thread/1013/2751/01/82/7_1.html###/1013/2751/01/82/7_1-1_1");
//		urlsList.add("http://club.china.com/data/thread/12171906/2750/86/40/8_1.html###/12171906/2750/86/40/8_1-1_27");
//		urlsList.add("http://www.ytbbs.com/thread-4769716-1-1.html###4769716-1_0");
//		urlsList.add("http://www.xc.zj.cn/read-htm-tid-853912.html###853912-1_7");
//		urlsList.add("http://www.xc.zj.cn/read-htm-tid-853912.html###97###_7");
//		urlsList.add("http://tieba.baidu.com/p/2025763431###2025763431-1_15");
//		urlsList.add("http://www.tianya.cn/publicforum/content/free/1/2924934.shtml###2924934###2924934_1_0");
//		urlsList.add("http://www.tianya.cn/publicforum/content/free/1/2924934.shtml###2924934_00");
//		urlsList.add("http://bbs.lygbst.cn/thread-2600681-1-4.html###2600681-1_4");
//		urlsList.add("http://bbs.cqnews.net/thread-2750614-1-1.html###2750614-1_0");
//		urlsList.add("http://tieba.baidu.com/p/2025564035###2025564035-1_0");
//		urlsList.add("http://www.xc.zj.cn/read-htm-tid-853868.html###97###_1");
//		urlsList.add("http://www.xc.zj.cn/read-htm-tid-853868.html###853868-1_1");
//		urlsList.add("http://t.qq.com//p/t/171059069756645");
    		
    	ExecutorService service = Executors.newFixedThreadPool(10);
    	for (int i = 0; i < urlsList.size(); i++) {
    		String strUrl = urlsList.get(i);
    		String strEncoding = "gbk";
    		String fileName="d:\\1test\\"+i+".mht";
    		File file= new File(fileName);
    		//若文件存在，删除文件，并删除数据库信息
    		if (file.exists()) {
    			file.delete();
    		}
    		service.execute(new Html2MHTCompiler(strUrl, fileName));
//	        Html2MHTCompiler h2t = new Html2MHTCompiler(strUrl, strEncoding, "d:\\lhm\\mht\\test"+(i+1)+".mht");
//	        h2t.compile();
		}
    	service.shutdown();
		while (!service.isTerminated()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				logger.error("线程休眠出错");
			}
		}
    	logger.info("总耗时：" + (new Date().getTime() - _time));
    }
   
    public Html2MHTCompiler(String strUrl, String strFileName) {
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
    
    @Override
    public void run() {
		compile();
	}
    
    /**
     * 主程序，执行下载，生成mht文件的操作
     */
	public boolean compile() {
        if (Validator.isNull(strWeb) || Validator.isNull(strFileName))
            return false;
        try {
        	logger.info("生成MHT文件，URL:"+this.strWeb.toString());
        	strText = getHtmlContent(strWeb.toString());
		} catch (Exception e1) {
			logger.error("抓取网页内容出错", e1);
		}
		if(Validator.isNull(strText)) return false;
        HashMap urlMap = new HashMap();
        NodeList nodes = new NodeList();
        try {
            Parser parser = createParser(strText);
            parser.setEncoding(strEncoding);
            nodes = parser.parse(null);
        } catch (ParserException e) {
            e.printStackTrace();
        }
        extractAllScriptNodes(nodes);
        ArrayList urlScriptList = extractAllScriptNodes(nodes, urlMap);
        ArrayList urlImageList = extractAllImageNodes(nodes, urlMap);
        for (Iterator iter = urlMap.entrySet().iterator(); iter.hasNext();) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String)entry.getKey();
            String val = (String)entry.getValue();
            strText = strText.replaceAll(val, key);
        }
        try {
            createMhtArchive(strText, urlScriptList, urlImageList);
        } catch (Exception e) {
        	logger.error("生成mht文件出错", e);
            return false;
        }
        return true;
    }
	
    /**
     * 建立HTML parser
     * @param inputHTML 网页文本内容
     * @return HTML parser
     */
    private Parser createParser(String inputHTML) {
        Lexer mLexer = new Lexer(new Page(inputHTML));
        return new Parser(mLexer, new DefaultParserFeedback(DefaultParserFeedback.QUIET));
    }
    
    /**
     * 抽取基础URL地址
     * @param nodes 网页标签集合
     */
    private void extractAllScriptNodes(NodeList nodes) {
        NodeList filtered = nodes.extractAllNodesThatMatch(new TagNameFilter(
                "BASE"), true);
        if (filtered != null && filtered.size() > 0) {
            Tag tag = (Tag) filtered.elementAt(0);
            String href = tag.getAttribute("href");
            if (href != null && href.length() > 0) {
                try {
                    strWeb = new URL(href);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * 抽取网页包含的css,js链接
     * @param nodes 网页标签集合
     * @param urlMap 已存在的url集合
     * @return css,js链接的集合
     */
    private ArrayList extractAllScriptNodes(NodeList nodes, HashMap urlMap) {
        ArrayList urlList = new ArrayList();
        //开始处理js的url
        NodeList filtered = nodes.extractAllNodesThatMatch(new TagNameFilter("script"), true);//js标签过滤器
        for (int i = 0; i < filtered.size(); i++) {
            Tag tag = (Tag) filtered.elementAt(i);
            String src = tag.getAttribute("src");
            // 处理js的url
            if (src != null && src.length() > 0) {
                String innerURL = src;
                String absoluteURL = makeAbsoluteURL(strWeb, innerURL);
                if (absoluteURL != null && !urlMap.containsKey(absoluteURL)) {
                    urlMap.put(absoluteURL, innerURL);
                    ArrayList urlInfo = new ArrayList();
                    urlInfo.add(innerURL);
                    urlInfo.add(absoluteURL);
                    urlList.add(urlInfo);
                }
                tag.setAttribute("src", absoluteURL);
            }
        }
        //开始处理css的url
        filtered = nodes.extractAllNodesThatMatch(new TagNameFilter("link"), true);//css标签过滤器
        for (int i = 0; i < filtered.size(); i++) {
            Tag tag = (Tag) filtered.elementAt(i);
            String type = (tag.getAttribute("type"));
            String rel = (tag.getAttribute("rel"));
            String href = tag.getAttribute("href");
            boolean isCssFile = false;
            if (rel != null) {
                isCssFile = rel.indexOf("stylesheet") != -1;
            } else if (type != null) {
                isCssFile |= type.indexOf("text/css") != -1;
            }
            // 处理css的url
            if (isCssFile && href != null && href.length() > 0) {
                String innerURL = href;
                String absoluteURL = makeAbsoluteURL(strWeb, innerURL);
                if (absoluteURL != null && !urlMap.containsKey(absoluteURL)) {
                    urlMap.put(absoluteURL, innerURL);
                    ArrayList urlInfo = new ArrayList();
                    urlInfo.add(innerURL);
                    urlInfo.add(absoluteURL);
                    urlList.add(urlInfo);
                }
                tag.setAttribute("href", absoluteURL);
            }
        }
        return urlList;
    }
    /**
     * 抽取网页包含的图像链接
     * @param nodes 网页标签集合
     * @param urlMap 已存在的url集合
     * @return 图像链接集合
     */
    private ArrayList extractAllImageNodes(NodeList nodes, HashMap urlMap) {
        ArrayList urlList = new ArrayList();
        NodeList filtered = nodes.extractAllNodesThatMatch(new TagNameFilter("IMG"), true);//图像标签过滤器
        for (int i = 0; i < filtered.size(); i++) {
            Tag tag = (Tag) filtered.elementAt(i);
            String src = tag.getAttribute("src");
            // 处理图像url
            if (src != null && src.length() > 0) {
                String innerURL = src;
                String absoluteURL = makeAbsoluteURL(strWeb, innerURL);
                if (absoluteURL != null && !urlMap.containsKey(absoluteURL)) {
                    urlMap.put(absoluteURL, innerURL);
                    ArrayList urlInfo = new ArrayList();
                    urlInfo.add(innerURL);
                    urlInfo.add(absoluteURL);
                    urlList.add(urlInfo);
                }
                tag.setAttribute("src", absoluteURL);
            }
        }
        return urlList;
    }
    
    /**
     * 相对路径转绝对路径
     * @param strWeb  输入参数
     * @param innerURL 相对路径链接
     * @return 绝对路径链接
     */
    public static String makeAbsoluteURL(URL strWeb, String innerURL) {
        //去除后缀
        int pos = innerURL.indexOf("?");
        if (pos != -1) {
            innerURL = innerURL.substring(0, pos);
        }
        if (innerURL != null&& innerURL.toLowerCase().indexOf("http") == 0) {
            return innerURL;
        }
        URL linkUri = null;
        try {
            linkUri = new URL(strWeb, innerURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        String absURL = linkUri.toString();
        absURL = absURL.replaceAll("\\.\\./", "");
        absURL = absURL.replaceAll("\\./", "");
        return absURL;
    }
    /**
     * 创建mht文件，和发送邮件是一样的，要设置主题，发送人，接收人，抄送人，标题，正文和附件等
     * @param content 网页文本内容
     * @param urlScriptList 脚本链接集合
     * @param urlImageList 图片链接集合
     * @author liuhm
     */
    private void createMhtArchive(String content, ArrayList urlScriptList, ArrayList urlImageList) throws Exception {
        //Instantiate a Multipart object
        MimeMultipart mp = new MimeMultipart("related");
        Properties props = new Properties();
        props.put("mail.smtp.host", smtp);
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage msg = new MimeMessage(session);
        msg.setHeader("X-Mailer", "Code Manager .SWT");
        if (from != null) {//发送人
            msg.setFrom(new InternetAddress(from));
        }
        if (subject != null) {//主题
            msg.setSubject(subject);
        }
        if (to != null) {//接收人
            InternetAddress[] toAddresses = getInetAddresses(to);
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
        }
        if (cc != null) {//抄送
            InternetAddress[] ccAddresses = getInetAddresses(cc);
            msg.setRecipients(Message.RecipientType.CC, ccAddresses);
        }
        if (bcc != null) {
            InternetAddress[] bccAddresses = getInetAddresses(bcc);
            msg.setRecipients(Message.RecipientType.BCC, bccAddresses);
        }
        //设置网页正文
        MimeBodyPart bp = new MimeBodyPart();
        bp.setText(content, strEncoding);
        bp.addHeader("Content-Type", "text/html;charset=" + strEncoding);
        bp.addHeader("Content-Location", strWeb.toString());
        mp.addBodyPart(bp);
        int urlCount = urlScriptList.size();
        for (int i = 0; i < urlCount; i++) {//设置js,css文件附件
        	bp = new MimeBodyPart();
        	ArrayList urlInfo = (ArrayList) urlScriptList.get(i);
        	String absoluteURL = urlInfo.get(1).toString();
        	bp.addHeader("Content-Location",javax.mail.internet.MimeUtility.encodeWord(java.net.URLDecoder.decode(absoluteURL, strEncoding)));
        	DataSource source = new AttachmentDataSource(absoluteURL, "text");
        	bp.setDataHandler(new DataHandler(source));
        	mp.addBodyPart(bp);
        }

        urlCount = urlImageList.size();
        for (int i = 0; i < urlCount; i++) {//设置图片附件
        	bp = new MimeBodyPart();
        	ArrayList urlInfo = (ArrayList) urlImageList.get(i);
        	String absoluteURL = urlInfo.get(1).toString();
        	bp.addHeader("Content-Location",javax.mail.internet.MimeUtility.encodeWord(java.net.URLDecoder.decode(absoluteURL, strEncoding)));
        	DataSource source = new AttachmentDataSource(absoluteURL, "image");
        	bp.setDataHandler(new DataHandler(source));
			mp.addBodyPart(bp);
        }
        msg.setContent(mp);
        // 将mht字符串写入文件
        msg.writeTo(new FileOutputStream(strFileName));
    }
    
    /**
     * 获取邮件地址
     * @param emails
     * @return
     * @throws Exception
     */
	private InternetAddress[] getInetAddresses(String emails) throws Exception {
    	ArrayList list = new ArrayList();
    	StringTokenizer tok = new StringTokenizer(emails, ",");
    	while (tok.hasMoreTokens()) {
    		list.add(tok.nextToken());
    	}
    	int count = list.size();
    	InternetAddress[] addresses = new InternetAddress[count];
    	for (int i = 0; i < count; i++) {
    		addresses[i] = new InternetAddress(list.get(i).toString());
    	}
    	return addresses;
    }
    
    /**
     * 根据url以及编码方式获取网页内容
     * @param url 
     * @param pageCoding
     * @return 
     * @throws Exception 
     * @author liuhm
     */
    public String getSource(String url,String pageCoding) throws Exception {
    	HttpClient client = new HttpClient();
    	try{
			GetMethod method = new GetMethod(url);
			client.executeMethod(method);
			byte[] content = method.getResponseBody();
			String page = new String(content, pageCoding);
			method.releaseConnection();
			return page;
    	}finally{
    		client.getHttpConnectionManager().closeIdleConnections(10);
    	}
	 }
    
    /**
     * 获取网页内容，和上面的方法不一样的地方在于此方法下载二进制流的方式，试图解决乱码问题
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
		//模拟浏览器
		httpClient.getParams().setParameter(HttpMethodParams.USER_AGENT, USER_AGENT);
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
//		    	strEncoding="GBK";//默认值
//		    	if(method.getResponseHeader("Content-Type") != null) {
//		    		String sCharset = method.getResponseHeader("Content-Type").getValue();
//		    		if(sCharset.indexOf("charset=") != -1) {
//		    			strEncoding = sCharset.substring(sCharset.indexOf("charset=")+8);
//		    		}
//		    	}
//		    	logger.info("url:" + htmlurl + "的页面编码为" + strEncoding);
//		        if (method.getResponseHeader("Content-Encoding") != null)
//		            acceptEncoding = method.getResponseHeader("Content-Encoding").getValue();
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
		    e1.printStackTrace();
		} catch (IOException e1) {
		    e1.printStackTrace();
		}
		method.abort();
		method.releaseConnection();
		return sb.toString();
	}					
    
    
    /**
	  *  获得违法信息mht文件路径和文件名称
	  * @return  返回文件全路径、相对路径、文件名称
	  * @author "zhangshaofeng"  
	  * @time Sep 20, 2012 10:52:33 AM
	  */
	public  static String[] getMhtPathArr(){
		//文件路径
		final String imageDir="upload";//文件目录路径
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/HH/");
		//相对路径
		String relativePath=imageDir+sdf.format(date);
		String saveDirPath=(new StringBuilder(String.valueOf(getProjectPath()))).append(relativePath).toString();
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
		File classPath = new File(Html2MHTCompiler.class.getResource("/").toString().substring(6));
		return  classPath.getParentFile().getParent()+File.separator;
	}
    
    /**
     * 附件类，下载网页中附加的css,js文件以及图片的二进制流，并将之作为mht的附件保存下来
     * @author liuhm
     * 
     */
    class AttachmentDataSource implements DataSource {
    	private MimetypesFileTypeMap map = new MimetypesFileTypeMap();
    	private String strUrl;
    	private String strType;
    	private byte[] dataSize = null;

    	/**
    	 * 内容的类型
    	 */
    	private Map normalMap = new HashMap();
    	{
    		normalMap.put("image", "image/jpeg");//图片
    		normalMap.put("text", "text/plain");//文本
    	}
    	public AttachmentDataSource(String strUrl, String strType) {
    		this.strType = strType;
    		this.strUrl = strUrl;

    		strUrl = strUrl.trim();
    		strUrl = strUrl.replaceAll(" ", "%20");
    		try {
    			dataSize = downFileByte(strUrl);
			} catch (Exception e) {
				logger.error("下载网页二进制流出错", e);
			}
    	}

    	/**
    	 * 获取内容类型
    	 */
    	public String getContentType() {
    		return getMimeType(getName());
    	}
    	
    	/**
    	 * 获取附件名称
    	 */
    	public String getName() {
    		char separator = File.separatorChar;
    		if( strUrl.lastIndexOf(separator) >= 0 )
    			return strUrl.substring(strUrl.lastIndexOf(separator) + 1);
    		return strUrl;
    	}
    	
    	/**
    	 * 获取附件类型
    	 * @param fileName
    	 * @return
    	 */
    	private String getMimeType(String fileName) {
    		String type = (String)normalMap.get(strType);
    		if (type == null) {
    			try {
    				type = map.getContentType(fileName);
    			} catch (Exception e) {
    			}
    			if (type == null) {
    				type = "application/octet-stream";
    			}
    		}
    		return type;
    	}

		@Override
		public InputStream getInputStream() throws IOException {
			if (dataSize == null)
				dataSize = new byte[0];
			return new ByteArrayInputStream(dataSize);
		}

		@Override
		public OutputStream getOutputStream() throws IOException {
			return new java.io.ByteArrayOutputStream();
		}
		
		/**
		 * 下载网页中的css,js文件以及图片的二进制流
		 * @param strUrl2
		 * @return
		 * @throws Exception
		 */
//		private byte[] downBinaryFile(String strUrl2) throws Exception {
////			logger.info(strUrl2);
////			Properties prop = System.getProperties();   
////			prop.put("proxySet", "true");
////	        prop.put("http.proxyHost", "172.16.2.185");   
////	        prop.put("http.proxyPort", "808"); 
//			URL cUrl = new URL(strUrl2);
//			URLConnection uc = cUrl.openConnection();
//			int contentLength = uc.getContentLength();
//			if (contentLength<0) {
//				System.out.println("*******************无法下载URL:"+strUrl2);
//				return null;
//			}
//			InputStream raw = uc.getInputStream();
//			InputStream in = new BufferedInputStream(raw);
//			byte[] data = new byte[contentLength];
//			int bytesRead = 0;
//			int offset = 0;
//			while (offset < contentLength) {
//				bytesRead = in.read(data, offset, data.length - offset);
//				if (bytesRead == -1) {
//					break;
//				}
//				offset += bytesRead;
//			}
//			in.close();
//			return data;
//		}
		
		 private  byte[] downFileByte(String strUrl)
		  {
		    InputStream textStream = null;
		    BufferedInputStream buff = null;

		    byte[] buf = (byte[])null;
		    try
		    {
		      ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
		      URL url = new URL(strUrl);
		      textStream = url.openStream();
		      buff = new BufferedInputStream(textStream);

		      int ch = 0;
		      while ((ch = buff.read()) != -1)
		      {
		        byteArray.write(ch);
		      }
		      buf = byteArray.toByteArray();
		      buff.close();
		      textStream.close();
		    }
		    catch (Exception e)
		    {
		      e.printStackTrace();
		    }
		    finally
		    {
		      try
		      {
		        if (buff != null)
		        {
		          buff.close();
		        }
		        if (textStream != null)
		        {
		          textStream.close();
		        }
		      }
		      catch (Exception e)
		      {
		        System.out.println("解析文件失败");
		      }
		    }
		    return buf;
		  }
    }
}
