/** * A级 */
package com.meiah.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.junit.Before;
import org.junit.Test;

import com.meiah.core.orm.Page;
import com.meiah.core.util.Base64;
import com.meiah.core.util.DateUtil;
import com.meiah.core.util.MD5Utils;
import com.meiah.entity.DataRecordOfString;
import com.meiah.entity.favorite.Favorite;
import com.meiah.entity.pre.PreReport;
import com.meiah.entity.reportinfo.Hwinfo;
import com.meiah.entity.reportinfo.Winfo;
import com.meiah.entity.sys.Log;
import com.meiah.entity.sys.LoginMap;
import com.meiah.entity.sys.SysCode;
import com.meiah.entity.sys.SysNotice;
import com.meiah.entity.sys.SysResource;
import com.meiah.entity.sys.SysRole;
import com.meiah.entity.sys.SysUser;
import com.meiah.entity.task.TaskNotice;
import com.meiah.entity.task.WbPatrolLog;
import com.meiah.services.PatrolService;
public class TestServices {
	private JaxWsProxyFactoryBean factory; 
	private PatrolService client ;
	String comeFrom ="巡查平台";
	Long userid = 1L;
	Long ouserid = 1L;
	 @Before  
    public void setUp() throws Exception {  
        factory = new JaxWsProxyFactoryBean(); 
        factory.setServiceClass(PatrolService.class);  
//      factory.setAddress("https://172.16.19.28:9090/patrol/ws/patrol");
//        factory.setAddress("http://172.16.0.26:8007/patrol/ws/patrol");
        
        factory.setAddress("http://localhost:8090/patrol/ws/patrol");
        
        client = (PatrolService)factory.create();
        
    
        
//        CXFClientUtil.setTimeout(factory);
        
        
    }  
	 @Test
	 public void testAddTaskNotice() throws Exception{
		 TaskNotice taskNotice = new TaskNotice();
		 taskNotice.setContent("empty");
		 taskNotice.setFlag(0);
		 taskNotice.setTuserid(1L);
		 taskNotice.setUserid(1L);
		 String result = client.addTaskNotice(taskNotice,"巡查平台");
		 System.out.println(result);
	 }
	 @Test
	 public void testFavoriteLabel() throws Exception{
		 DataRecordOfString result = client.favoriteLabel(userid, ouserid, comeFrom);
		 System.out.println("I_total:"+result.getI_total());
		 for(int i = 0 ;i < result.getI_total(); i++){
			 System.out.println(result.getM_item().get(i));
		 }
	 }
	 
	 @Test
	 public void testAddFavorite() throws Exception{
		 Favorite favorite = new Favorite();
		 favorite.setAddress("厦门市美亚柏科信息股份有限公司");//可选
		 favorite.setAuthor("empty");//可选，最好是填写
		 favorite.setAuthorurl("http://devsuite.xm-my.com.cn:8001/images/ico02.gif");//可选
		 favorite.setCategory("论坛");//必填
		 favorite.setComeFrom(comeFrom);//必填
		 favorite.setContent("test service");//必填
		 favorite.setFavortime(DateUtil.convertDateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));//可选，但是要按照时间格式
		 favorite.setInfoFrom("崇阳论坛-崇阳热线");//必填
		 favorite.setIp("127.0.0.1");//可选
		 favorite.setLable("lable");//可选
		 favorite.setPublishtime(DateUtil.convertDateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));//可选，最好是填写
		 favorite.setRemark("备注");//可选
		 favorite.setTitle("标题test");//可选
		 favorite.setTopdomain("baidu.com");//可选
		 favorite.setUrl("http://172.16.1.199:88/default.asp");//必填
		 favorite.setUserid(1L);//必填
		 favorite.setUsername("admin");//必填
		 List<String> urlList = new ArrayList<String>();//可选
		 urlList.add("http://oa.300188.cn/wui/theme/ecology7/page/images/login/graypoint.png");
		 urlList.add("http://oa.300188.cn/LoginTemplateFile/img4-1144267030");
		 urlList.add("http://oa.300188.cn/LoginTemplateFile/img4-400073196");
		 urlList.add("http://oa.300188.cn/LoginTemplateFile/img4-743980923");
		 favorite.setUrlList(urlList);
		 client.addFavorite(favorite, ouserid, comeFrom);
	 }
	 
	 @Test
	 public void addLog(){
	 }
	 
	 
	 @Test
	 public void testGetUserByLoginName() throws Exception{
		SysUser sysUser = client.getUserByLoginName("admin", ouserid, comeFrom);
		 System.out.println(sysUser);
	 }
	 
	 @Test
	 public void testGetLoginMapOnLine() throws Exception{
		SysUser sysUser = client.getLoginMapOnLine("abc", ouserid, comeFrom);
		 System.out.println(sysUser.getName());
	 }
	 @Test
	 public void testGetLoginMapByObjid() throws Exception{
		 LoginMap loginMap = client.getLoginMapByObjid("abc", comeFrom);
		 System.out.println(loginMap);
	 }
	 @Test
	 public void testGetUserNP() throws Exception{
		 String username = "admin";
		 String psd = "e10adc3949ba59abbe56e057f20f883e";
		 String number="1171e7260001000024f8";
		 
//		 String np = Base64.encode(username+"!!!"+psd);
		 String npn = Base64.encode( username+"!!!"+psd+"!!!"+number);
		 
//		 SysUser user = client.getUserNP(np, comeFrom);
		 SysUser user = client.getUserNP(npn, comeFrom);
		 System.out.println(user.getLoginname());
		 
	 }
	 
	 
	 
	 
//	 
	 @Test
	 public void testGetChildUsers() throws Exception{
		List<SysUser> users = client.getChildUsers(ouserid, comeFrom);
		 System.out.println(users.size());
	 }
	 
	 @Test
//	 截图时间会较长
	 public void cutImg() throws Exception{
		 String url = "http://172.16.1.124:88/index.asp";
		 String[] str = client.cutImg(url, ouserid, comeFrom);
		 System.out.println(str[0]+":"+str[1]);
	 }
	 
	 @Test
	 public void cutHtm() throws Exception{
		 String url = "http://172.16.1.124:88/index.asp";
		 String[] str = client.htm(url, ouserid, comeFrom);
		 System.out.println(str[0]+":"+str[1]);
	 }
	 
	 @Test
	 public void cutmht() throws Exception{
		 String url = "http://172.16.1.124:88/index.asp";
		 String[] str = client.mht(url, ouserid, comeFrom);
		 System.out.println(str[0]+":"+str[1]);
	 }
	 
	 @Test
//	 codeType为空，则取全部
	 public void testGetSysCode() throws Exception{
		 String codeType="";
		  List<SysCode> list = client.getSysCode(codeType, ouserid, comeFrom);
		  for(SysCode sysCode : list){
			  System.out.println(sysCode.getCodeValue());
		  }
	 }
//	 
	 @Test
	 public void testGetResources() throws Exception{
		 SysResource resources = new SysResource();
		 resources.setComeFrom("微博巡查");
		List<SysResource> list = client.getResources(resources, 3L, comeFrom, "1");
		for (SysResource sysResource : list) {
			System.out.println(sysResource.getName());
		}
	 }
	 @Test
	 public void testCheckURL() throws Exception{
		 String url = "http://www.baidu.com";
		 String result = client.checkURL(url, ouserid, comeFrom);
		 System.out.println(result);
	 }
	 
	 /***************报送字段已实际情况为准**************************************/
	 
	 @Test
	 public void testReportHwinfo() throws Exception{
		 Hwinfo hwinfo = new Hwinfo();
		 hwinfo.setSiteurls("www.baidu.com");
		 hwinfo.setSitenames("百度");
		 hwinfo.setYuanbiaoti("百度一下");
		 hwinfo.setXxly(11L);
		 String username = "admin";
		 Integer cutImgFlag = 0	;
		 Integer attfileFlag = 0;
		 String[] attfileIds = null;
		 String result = client.reportHwinfo(hwinfo, username, ouserid, cutImgFlag, attfileFlag, attfileIds, comeFrom);
		 System.out.println(result);
	 }
	 
	 @Test
	 public void testReportWinfo() throws Exception{
		 Winfo winfo = new Winfo();
		 winfo.setSiteurl("www.baidu.com");
		 winfo.setSitename("百度");
		 winfo.setYuanbiaoti("百度一下");
		 String username = "admin";
		 Integer attfileFlag = 0;
		 String[] attfileIds = null;
		 String result = client.reportWinfo(winfo, username, ouserid, attfileFlag, attfileIds, comeFrom);
		 System.out.println(result);
	 }
	 
	 @Test
	 public void testUploadFile(){
		 	String targetURL = null; 
			File targetFile = null; 
			targetFile = new File("d://Chrysanthemum_s.jpg");
			targetURL = "http://172.16.19.28:9090/patrol/upload.file?extend1=1"; // servleturl
			PostMethod filePost = new PostMethod(targetURL); 
			try {
				Part[] parts = { new FilePart(targetFile.getName(), targetFile)};
				filePost.setRequestEntity(new MultipartRequestEntity(parts,filePost.getParams()));
				HttpClient client = new HttpClient();
				client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
				int status = client.executeMethod(filePost);
				if (status == HttpStatus.SC_OK) {
					System.out.println("成功！");
				} else {
					System.out.println("失败 ！");
				}
				System.out.println(filePost.getResponseBodyAsString());
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				filePost.releaseConnection();
			}
	 }
	 
	 @Test
	 public void testGetAllSysUser() throws Exception{
		 List<SysUser> list = client.getAllSysUser("巡查平台");
		 System.out.println(list.size());
	 }
	 
	 @Test
	 public void testGetSysNotice() throws Exception{
		 SysNotice sysNotice = new SysNotice();
		 Page<SysNotice> page = new Page<SysNotice>();
		 page.setPageSize(1);
		 page = client.getSysNotice(sysNotice, page,ouserid, comeFrom);
		 System.out.println(page.getResult().get(0).getContent());
	 }
	 
	 @Test
	 public void testReportPre() throws Exception{
		 PreReport preReport = new PreReport();
		 preReport.setUrl("http://baidu.com");
		 preReport.setTitle("百度");
		 preReport.setUserId(ouserid);
		 String url = "http://172.16.1.124:88/index.asp";
		 List<String> list = new ArrayList<String>();
		 list.add(url);
		 preReport.setUrlList(list);
		 String[] attfileIds = null;
		 String result = client.reportPre(preReport,ouserid,   attfileIds, comeFrom);
		 System.out.println(result);
	 }
	 
	 @Test
	 public void testReportPreWeChat() throws Exception{
		 PreReport preReport = new PreReport();
		 preReport.setUrl("http://baidu.com");
		 preReport.setTitle("百度");
		 preReport.setUserId(ouserid);
		 preReport.setViews(100L);
		 preReport.setReplies(10L);
		 String url = "http://172.16.1.124:88/index.asp";
		 String wechat="13255985021";
		 List<String> list = new ArrayList<String>();
		 list.add(url);
		 preReport.setUrlList(list);
		 String[] attfileIds = null;
		 String result = client.reportPreWeChat(preReport,ouserid,   attfileIds, comeFrom,wechat);
		 System.out.println(result);
	 }
	 
	 @Test
	 public void testAddWbPatrolLog() throws Exception{
		 WbPatrolLog wbPatrolLog = new WbPatrolLog();
		 
		 wbPatrolLog.setNickname("张三");
		 wbPatrolLog.setTime(DateUtil.convertDateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		 wbPatrolLog.setUrl("www.baidu.com");
		 wbPatrolLog.setUserid("471520384");
		 wbPatrolLog.setOpUserid(ouserid);
		 wbPatrolLog.setBtype("sina");
		 wbPatrolLog.setKeyword("test");
		 
		 
		 String result = client.addWbPatrolLog(wbPatrolLog, ouserid, comeFrom);
		 System.out.println(result);
	 }
	 
	 @Test
	 public void testAddLog() throws Exception{
		 Log log = new Log();
		 log.setComeFrom(comeFrom);
		 log.setContent("警察");
		 log.setIp("127.0.0.1");
		 log.setModule("信息查看-全部信息");
		 log.setRemark("");
		 log.setStatus("成功");
		 log.setTime("2014-06-19 13:08:00");
		 log.setType("置顶");
		 log.setUserid(ouserid);
		 String result = client.addLog(log, ouserid, comeFrom);
		 System.out.println(result);
	 }
	 @Test
	 public void testGetRoleList() throws Exception{
		 String ids = null;
		 List<SysRole> list = client.getRoleList(ids, ouserid, comeFrom);
		 System.out.println(list);
	 }
	 @Test
	 public void testAlterpw() throws Exception{
		 String pwd = "123456x";
		 Long userid = 3L;
		 pwd =MD5Utils.getMD5(pwd.getBytes());
		 String result = client.alterpw(userid, pwd, ouserid, comeFrom);
		 System.out.println(result);
	 }
	 
	 @Test
	 public void testBindWeChat() throws Exception{
		 String wechat = "123456x12";
		 String result = client.bindWeChat("admin", wechat, comeFrom);
	 }
}
