/** * A级 */
package com.meiah.services.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.meiah.core.orm.Page;
import com.meiah.core.util.Base64;
import com.meiah.core.util.CXFClientUtil;
import com.meiah.core.util.Config;
import com.meiah.core.util.CutImageUtil;
import com.meiah.core.util.DateUtil;
import com.meiah.core.util.UrlToHtmUtil;
import com.meiah.core.util.UrlToMhtUtil;
import com.meiah.core.util.Validator;
import com.meiah.entity.DataRecordOfString;
import com.meiah.entity.favorite.Favorite;
import com.meiah.entity.pre.PreReport;
import com.meiah.entity.reportinfo.Finfo;
import com.meiah.entity.reportinfo.Hwinfo;
import com.meiah.entity.reportinfo.Winfo;
import com.meiah.entity.reportinfo.Yinfo;
import com.meiah.entity.sys.Log;
import com.meiah.entity.sys.LoginMap;
import com.meiah.entity.sys.SysCode;
import com.meiah.entity.sys.SysGroup;
import com.meiah.entity.sys.SysNotice;
import com.meiah.entity.sys.SysResource;
import com.meiah.entity.sys.SysRole;
import com.meiah.entity.sys.SysSyncFail;
import com.meiah.entity.sys.SysUser;
import com.meiah.entity.sys.UploadFile;
import com.meiah.entity.task.TaskNotice;
import com.meiah.entity.task.WbPatrolLog;
import com.meiah.exception.ServiceException;
import com.meiah.manager.favorite.FavoriteManager;
import com.meiah.manager.pre.PreReportManager;
import com.meiah.manager.reportinfo.FinfoManager;
import com.meiah.manager.reportinfo.HwinfoManager;
import com.meiah.manager.reportinfo.WinfoManager;
import com.meiah.manager.reportinfo.YinfoManager;
import com.meiah.manager.sys.LogManager;
import com.meiah.manager.sys.LoginMapManager;
import com.meiah.manager.sys.SysCodeManager;
import com.meiah.manager.sys.SysFailLogManager;
import com.meiah.manager.sys.SysGroupManager;
import com.meiah.manager.sys.SysNoticeManager;
import com.meiah.manager.sys.SysResourceManager;
import com.meiah.manager.sys.SysRoleManager;
import com.meiah.manager.sys.SysUserManager;
import com.meiah.manager.sys.UploadFileManager;
import com.meiah.manager.task.PolicyManager;
import com.meiah.manager.task.TaskNoticeManager;
import com.meiah.manager.task.WbPatrolLogManager;
import com.meiah.services.PatrolService;
@WebService(targetNamespace="http://tempuri.org/")
public class PatrolServiceImpl implements PatrolService{
	private  static Logger logger = Logger.getLogger(PatrolServiceImpl.class);
	@Resource
	private TaskNoticeManager taskNoticeManager;
	@Resource
	private LogManager logManager;
	@Resource
	private FavoriteManager favoriteManager;
	@Resource
	private SysUserManager sysUserManager;
	@Resource
	private SysGroupManager sysGroupManager;
	@Resource
	private LoginMapManager loginMapManager;
	@Resource
	private SysCodeManager sysCodeManager;
	@Resource
	private UploadFileManager uploadFileManager;
	@Resource
	private HwinfoManager hwinfoManager;
	@Resource
	private WinfoManager winfoManager;
	@Resource
	private SysResourceManager sysResourceManager;
	@Resource
	private SysRoleManager sysRoleManager;
	@Resource
	private PreReportManager preReportManager;
	@Resource
	private SysNoticeManager sysNoticeManager;
	@Resource
	private WbPatrolLogManager wbPatrolLogManager;
	@Resource
	private SysFailLogManager sysFailLogMangaer;
	
	@Resource
	private PolicyManager policyMangaer;
	
	@Resource
	private FinfoManager finfoManager;
	
	@Resource
	private YinfoManager yinfoManager;
	
	public String testServer(String content) {
		return "From server : " +content;
	}
	/**
	 * 添加任务提醒,userid、tuserid、content都不能为空
	 * @param taskNotice
	 */
	public String addTaskNotice(TaskNotice taskNotice,String comeFrom) throws Exception{
		try{
			if(Validator.isNull(taskNotice)||Validator.isNull(comeFrom)){
				throw new ServiceException("任务对象不能为空");
			}else{
				if(Validator.isNull(taskNotice.getTime())){
					taskNotice.setTime(DateUtil.convertDateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
				}
				Long fuserid = taskNotice.getUserid();
				Long tuserid = taskNotice.getTuserid();
				String content = taskNotice.getContent();
				if(Validator.isNull(fuserid) || Validator.isNull(tuserid) || Validator.isNull(content)){
					throw new ServiceException("任务提醒参数不正确");
				}else{
					taskNoticeManager.save(taskNotice);
					return "success";
				}
			}
		}catch (Exception e) {
			logger.error("添加任务提醒失败", e);
			throw new ServiceException("保存任务提醒失败");
		}
	}
	/**
	 * 根据用户ID获取用户收藏的标签(自己看自己的,去除为空,为''的标签)
	 * @param userid 用户标签
	 * @param suserid 哪个用户查询
	 * @return 标签的JSON串
	 */
	public DataRecordOfString favoriteLabel(Long userid,Long suserid,String comeFrom) throws Exception {
		if(Validator.isNull(userid)||Validator.isNull(comeFrom)){
			throw new ServiceException("查询参数有误");
		}else{
			try{
				DataRecordOfString data = favoriteManager.getLabelByUseridData(userid);
				return data;
			}catch (Exception e) {
				logger.error("获取收藏标签失败", e);
				throw new ServiceException("获取收藏标签失败");
			}
		}
	}
	/**
	 * 添加收藏
	 * @param favorite 要添加的收藏
	 * @param ouserid 收藏的用户
	 * @return 是否收藏成功
	 */
	public String addFavorite(Favorite favorite, Long ouserid,String comeFrom) {
		if(Validator.isNull(ouserid) || Validator.isNull(favorite)||Validator.isNull(comeFrom)){
			throw new ServiceException("查询参数有误");
		}else{
			try{
				if(Validator.isNull(favorite.getFavortime())){
					favorite.setFavortime(DateUtil.convertDateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
				}
				if(Validator.isNull(favorite.getComeFrom())){
					favorite.setComeFrom(comeFrom);
				}
				if(Validator.isNull(favorite.getLable())){
					favorite.setLable("");
				}
				favoriteManager.save(favorite);
				final List<String> urlList = favorite.getUrlList();
				final Long fid = favorite.getId();
				if(Validator.isNotNull(urlList)){
					new Thread(new Runnable() {
						public void run() {
							 favoriteManager.urlFileDownload(urlList,fid,555);
						}
					}).start();
				}
				return "success";
			}catch (Exception e) {
				logger.error("添加收藏失败", e);
				throw new ServiceException("添加收藏失败");
			}
		}
	}
	
	/**
	 * 添加收藏
	 * @param favorite 要添加的收藏
	 * @param ouserid 收藏的用户
	 * @return 成功则返回收藏的Id
	 */
	public Long addFavoriteReturn(Favorite favorite, Long ouserid,String comeFrom) {
		if(Validator.isNull(ouserid) || Validator.isNull(favorite)||Validator.isNull(comeFrom)){
			throw new ServiceException("查询参数有误");
		}else{
			try{
				if(Validator.isNull(favorite.getFavortime())){
					favorite.setFavortime(DateUtil.convertDateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
				}
				if(Validator.isNull(favorite.getComeFrom())){
					favorite.setComeFrom(comeFrom);
				}
				if(Validator.isNull(favorite.getLable())){
					favorite.setLable("");
				}
				favoriteManager.save(favorite);
				final List<String> urlList = favorite.getUrlList();
				final Long fid = favorite.getId();
				if(Validator.isNotNull(urlList)){
					new Thread(new Runnable() {
						public void run() {
							 favoriteManager.urlFileDownload(urlList,fid,555);
						}
					}).start();
				}
				return fid ;
			}catch (Exception e) {
				logger.error("添加收藏失败", e);
				throw new ServiceException("添加收藏失败");
			}
		}
	}
	
	/**
	 * 取消收藏
	 * @param fid 要取消的收藏id
	 * @param ouserid 收藏的用户
	 * @return 成功则返回收藏的Id
	 */
	public String cancleFavorite(Long fid, Long ouserid,String comeFrom) {
		if(Validator.isNull(ouserid) || Validator.isNull(fid)||Validator.isNull(comeFrom)){
			throw new ServiceException("查询参数有误");
		}else{
			try{
				favoriteManager.delete(fid);
				return "success" ;
			}catch (Exception e) {
				logger.error("添加收藏失败", e);
				throw new ServiceException("添加收藏失败");
			}
		}
	}
	
	/**
	 * 根据浏览器序列号获取在线用户，不做任何逻辑处理，只取符合条件的用户信息
	 * @param ouserid 操作的用户ID
	 * @param comeFrom 系统来源
	 * @return 用户
	 */
	public SysUser getLoginMapOnLine(String objid, Long ouserid,String comeFrom) throws Exception {
		try{
			if(Validator.isNull(objid) ||Validator.isNull(comeFrom)){
				throw new ServiceException("查询参数有误");
			}else{
				String username = "";
				//根据objid判断是否为登陆状态
				LoginMap loginMap = loginMapManager.findByPropertySingle("objid", objid);
				if(Validator.isNotNull(loginMap)){
					String status = loginMap.getStatus();
					if(status.equals("online")){
						 username = loginMap.getUsername();
						 SysUser user = sysUserManager.getSysUserByLoginname(username);
//						 if(Validator.isNotNull(user)){
//							 sysUserManager.evict(user);
//							 user.setPassword("");//屏蔽密码
//						 }
						 return user;
					} 
				}
			}
		}catch (Exception e) {
			logger.error("查询浏览器在线用户",e);
			throw new ServiceException("查询浏览器在线用户错误");
		}
		return null;
	}
	/**
	 * 根据用户名获取用户，不做任何逻辑处理，只取符合条件的用户信息
	 * @param loginName 用户的登录名
	 * @param ouserid 操作的用户ID
	 * @return 用户
	 */
	public SysUser getUserByLoginName(String loginName, Long ouserid,String comeFrom) throws Exception {
		try{
			if(Validator.isNull(loginName) || Validator.isNull(comeFrom)){
				throw new ServiceException("查询参数有误");
			}else{
				SysUser user = sysUserManager.getSysUserByLoginname(loginName);
//				if(Validator.isNotNull(user)){
//					sysUserManager.evict(user);
//					user.setPassword("");
//				}
				return user;
			}
		}catch (Exception e) {
			throw new ServiceException("服务器内部错误");
		}
	}
	
	/**
	 * 根据用户名获取用户，不做任何逻辑处理，只取符合条件的用户信息
	 * @param loginName 用户的登录名
	 * @param ouserid 操作的用户ID
	 * @return 用户
	 */
	public List<SysUser> getUserByName(String name,String flag, Long ouserid,String comeFrom) throws Exception {
		try{
			if(Validator.isNull(name) || Validator.isNull(comeFrom)){
				throw new ServiceException("查询参数有误");
			}else{
				List<SysUser> userList = sysUserManager.getName(name,flag);
				for(int i = 0 ; i < userList.size();i++){
					SysUser user = userList.get(i);
					if(Validator.isNotNull(user)){
						sysUserManager.evict(user);
						user.setPassword("");
					}
				}
				return userList;
			}
		}catch (Exception e) {
			System.out.println(e);
			throw new ServiceException("服务器内部错误");
		}
	}
	
	/**
	 * 根据浏览器序列号获取LoginMap，不做任何逻辑处理，只取符合条件的用户信息
	 * @param objid 浏览器序列号
	 * @param ouserid 操作的用户ID
	 * @param comeFrom 系统来源
	 * @return 用户
	 */
	public LoginMap getLoginMapByObjid(String objid, String comeFrom)
			throws Exception {
		try{
			if(Validator.isNull(objid) ||Validator.isNull(comeFrom)){
				throw new ServiceException("查询参数有误");
			}else{
//				String username = "";
				//根据objid判断是否为登陆状态
				LoginMap loginMap = loginMapManager.findByPropertySingle("objid", objid);
				return loginMap;
			}
		}catch (Exception e) {
			logger.error("查询浏览器在线用户",e);
			throw new ServiceException("查询浏览器在线用户错误");
		}
	}
	
	/**
	 * 根据用户ID获取用户，不做任何逻辑处理，只取符合条件的用户信息
	 * @param uid 用户id
	 * @param ouserid 操作的用户ID
	 * @return 用户
	 */
	public SysUser getUserByLoginUserId(Long uid, Long ouserid,String comeFrom) throws Exception {
		try{
			if(Validator.isNull(uid) || Validator.isNull(comeFrom)){
				throw new ServiceException("查询参数有误");
			}else{
				SysUser user = sysUserManager.get(uid);
//				if (Validator.isNotNull(user)) {
//					sysUserManager.evict(user);
//					user.setPassword("");
//				}
				return  user;
			}
		}catch (Exception e) {
			logger.error("查询用户出错",e);
			throw new ServiceException("服务器内部错误");
		}
	}
	/**
	 * 获取该用户的子用户
	 * @param userid
	 * @param comeFrom
	 * @return
	 * @throws Exception
	 */
	public List<SysUser> getChildUsers(Long userid,String comeFrom) throws Exception{
		try{
			if(Validator.isNull(userid) ||Validator.isNull(comeFrom)){
				throw new ServiceException("查询参数有误");
			}else{
				SysUser user = sysUserManager.get(userid);
				if(Validator.isNotNull(user)){
					Long groupid = user.getGroupid();
					SysGroup group = sysGroupManager.get(groupid);
					List<SysUser> userList = null;
					if(Validator.isNotNull(group)){
						userList = sysUserManager.getChildUsers(group.getCode());
					}
					return  userList;
				}
			}
		}catch (Exception e) {
			logger.error("查询用户出错",e);
			throw new ServiceException("服务器内部错误");
		}
		return null;
	}
	
	
	/**
	 * 根据URL截图，返回字符串数组
	 * @param url
	 * @param ouserid 操作的用户ID
	 * @return 如果截图成功，返回字符串数组，["图片名称","图片查看地址","图片入库的ID"]
	 * @throws Exception
	 */
	public String[] cutImg(String url, Long ouserid,String comeFrom) throws Exception {
		try{
			if(Validator.isNull(url)||Validator.isNull(ouserid)||Validator.isNull(comeFrom)){
				throw new ServiceException("查询接口出错");
			}
			UploadFile uploadFile = CutImageUtil.cutImageByUrl(url);
			logger.info("uploadFile save....");
			if (Validator.isNotNull(uploadFile)) {
				uploadFileManager.save(uploadFile);
				logger.info("uploadFile save end....");
				logger.info("return:["+uploadFile.getFilename()+";"+Config.patrolAddress+""+uploadFile.getFilepath()+";"+uploadFile.getId());
				return new String[]{uploadFile.getFilename(),Config.patrolAddress+""+uploadFile.getFilepath(),uploadFile.getId()+""};
			}
			logger.info("uploadFile is null");
			return null;
		}catch (Exception e) {
			logger.error("URL截图出错",e);
			logger.info("URL截图出错",e);
			throw new ServiceException("服务器内部错误");
		}
	}
	/**
	 * 通过codeType获取状态为1的编码
	 * @param codeType 编码类别,如果编码为空,则返回所有的syscode
	 * @param ouserid 操作的用户ID
	 * @return syscode集合
	 * @throws Exception
	 */
	public List<SysCode> getSysCode(String codeType, Long ouserid,String comeFrom) throws Exception {
		try{
			if(Validator.isNull(comeFrom)){
				throw new ServiceException("查询参数有误");
			}
			List<SysCode> resultList = new ArrayList<SysCode>();
			List<Criterion> list = new ArrayList<Criterion>();
			list.add(Restrictions.eq("status", 1));
			list.add(Restrictions.eq("ex1", 0l));//ex1为零 非删除
			if(Validator.isNotNull(codeType)){
				list.add(Restrictions.eq("codeType", codeType));
			} 
			Criterion[] cri = new Criterion[list.size()];
			list.toArray(cri);
			resultList = sysCodeManager.findByCriteria(cri);
			return resultList;
		}catch (Exception e) {
			logger.error("获取编码出错",e);
			throw new ServiceException("服务器内部错误");
		}
	}
	/**
	 * 根据URL获取htm，返回字符串数组
	 * @param url
	 * @param ouserid 操作的用户ID
	 * @return 如果htm成功，返回字符串数组，["htm名称","htm查看地址","htm入库的ID"]
	 * @throws Exception
	 */
	public String[] htm(String url, Long ouserid,String comeFrom) throws Exception {
		try{
			if(Validator.isNull(url)||Validator.isNull(ouserid)||Validator.isNull(comeFrom)){
				throw new ServiceException("查询接口出错");
			}
			UploadFile uploadFile = UrlToHtmUtil.getHtmByUrl(url);
			if (Validator.isNotNull(uploadFile)) {
				uploadFileManager.save(uploadFile);
				return new String[]{uploadFile.getFilename(),Config.patrolAddress+""+uploadFile.getFilepath(),uploadFile.getId()+""};
			} 
			return null;
		}catch (Exception e) {
			logger.error("URL获取htm",e);
			throw new ServiceException("服务器内部错误");
		}
	}
	/**
	 * 根据URL获取mht，返回字符串数组
	 * @param url
	 * @param ouserid 操作的用户ID
	 * @return 如果mht成功，返回字符串数组，["mht名称","mht查看地址","mht入库的ID"]
	 * @throws Exception
	 */
	public String[] mht(String url, Long ouserid,String comeFrom) throws Exception {
		try{
			if(Validator.isNull(url)||Validator.isNull(ouserid)||Validator.isNull(comeFrom)){
				throw new ServiceException("查询接口出错");
			}
			UploadFile uploadFile = UrlToMhtUtil.getMhtByUrl(url);
			if (Validator.isNotNull(uploadFile)) {
				uploadFileManager.save(uploadFile);
				return new String[]{uploadFile.getFilename(),Config.patrolAddress+""+uploadFile.getFilepath(),uploadFile.getId()+""};
			} 
			return null;
		}catch (Exception e) {
			logger.error("URL获取mht",e);
			throw new ServiceException("服务器内部错误");
		}
	}
	/**
	 * 专项信息报送
	 * @param hwinfo 专项信息
	 * @param username 操作用户的登录名
	 * @param ouserid 操作的用户ID
	 * @param cutImgFlag 是否截图，如果为空或者为零，表示没有截图，如果为1表示截图成功，如果为2表示正在截图
	 * @param attfileFlag 是否存在附件，如果为空或者为零，表示没有附件，如果为1表示存在附件
	 * @param attfileIds 该报送附件的id数组
	 * @return 是否成功
	 * @throws Exception
	 */
	public String reportHwinfo(Hwinfo hwinfo,String username ,Long ouserid,Integer cutImgFlag,Integer attfileFlag,String[] attfileIds ,String comeFrom) throws Exception {
		try{
			if(Validator.isNull(username)||Validator.isNull(ouserid)||Validator.isNull(comeFrom)){
				throw new ServiceException("查询接口参数有误");
			}
			if(Validator.isNotNull(attfileIds)){
				String ids = "";
				for (String id : attfileIds) {
					if(Validator.isNotNull(id)){
						if(Validator.isNotNull(id))ids += id + ",";
					}
				}
				if(Validator.isNotNull(ids)) {
					ids = ids.substring(0, ids.length()-1);
				}
				 List<Criterion> list = new ArrayList<Criterion>();
				if(Validator.isNotNull(cutImgFlag)){	
					hwinfo.setCutImgFlag(cutImgFlag);
				}else{//如果为空，查询数据库
					 list.add(Restrictions.sqlRestriction("id in ("+ids+")"));
					 list.add(Restrictions.eq("extend1", 1));
					 Criterion[] cri = new Criterion[list.size()];
					 list.toArray(cri);
					 List<UploadFile> reList = uploadFileManager.findByCriteria(cri);
					 if(Validator.isNotNull(reList)){
						 hwinfo.setCutImgFlag(1);
					 }
				}
				if(Validator.isNotNull(attfileFlag)){	
					hwinfo.setAttfileFlag(attfileFlag);
				}else{//如果为空，查询数据库
					 list = new ArrayList<Criterion>();
					 list.add(Restrictions.sqlRestriction("id in ("+ids+")"));
					 list.add(Restrictions.or(Restrictions.eq("extend1", 0),Restrictions.eq("extend1", 4)));
					 Criterion[] cri = new Criterion[list.size()];
					 list.toArray(cri);
					 List<UploadFile> reList = uploadFileManager.findByCriteria(cri);
					 if(Validator.isNotNull(reList)){
						 hwinfo.setAttfileFlag(1);
					 }
				}
			}
			 
			hwinfoManager.save(hwinfo,username , ouserid);
			uploadFileManager.updateFileInfo(hwinfo.getId(),2,attfileIds,null);
			
			final List<String> urlList = hwinfo.getUrlList();
			final Long fid = hwinfo.getId();
			if(Validator.isNotNull(urlList)){
				new Thread(new Runnable() {
					public void run() {
						 favoriteManager.urlFileDownload(urlList,fid,2);
					}
				}).start();
			}
			return "success";
		}catch (Exception e) {
			logger.error("专项信息报送出错",e);
			throw new ServiceException("服务器内部错误");
		}
	}
	
	/**
	 * 专项信息报送
	 * @param hwinfo 专项信息
	 * @param username 操作用户的登录名
	 * @param ouserid 操作的用户ID
	 * @param cutImgFlag 是否截图，如果为空或者为零，表示没有截图，如果为1表示截图成功，如果为2表示正在截图
	 * @param attfileFlag 是否存在附件，如果为空或者为零，表示没有附件，如果为1表示存在附件
	 * @param attfileIds 该报送附件的id数组
	 * @return 是否成功
	 * @throws Exception
	 */
	public Long reportHwinfoReturn(Hwinfo hwinfo,String username ,Long ouserid,Integer cutImgFlag,Integer attfileFlag,String[] attfileIds ,String comeFrom) throws Exception {
		try{
			if(Validator.isNull(username)||Validator.isNull(ouserid)||Validator.isNull(comeFrom)){
				throw new ServiceException("查询接口参数有误");
			}
			if(Validator.isNotNull(attfileIds)){
				String ids = "";
				for (String id : attfileIds) {
					if(Validator.isNotNull(id)){
						if(Validator.isNotNull(id))ids += id + ",";
					}
				}
				if(Validator.isNotNull(ids)) {
					ids = ids.substring(0, ids.length()-1);
				}
				 List<Criterion> list = new ArrayList<Criterion>();
				if(Validator.isNotNull(cutImgFlag)){	
					hwinfo.setCutImgFlag(cutImgFlag);
				}else{//如果为空，查询数据库
					 list.add(Restrictions.sqlRestriction("id in ("+ids+")"));
					 list.add(Restrictions.eq("extend1", 1));
					 Criterion[] cri = new Criterion[list.size()];
					 list.toArray(cri);
					 List<UploadFile> reList = uploadFileManager.findByCriteria(cri);
					 if(Validator.isNotNull(reList)){
						 hwinfo.setCutImgFlag(1);
					 }
				}
				if(Validator.isNotNull(attfileFlag)){	
					hwinfo.setAttfileFlag(attfileFlag);
				}else{//如果为空，查询数据库
					 list = new ArrayList<Criterion>();
					 list.add(Restrictions.sqlRestriction("id in ("+ids+")"));
					 list.add(Restrictions.or(Restrictions.eq("extend1", 0),Restrictions.eq("extend1", 4)));
					 Criterion[] cri = new Criterion[list.size()];
					 list.toArray(cri);
					 List<UploadFile> reList = uploadFileManager.findByCriteria(cri);
					 if(Validator.isNotNull(reList)){
						 hwinfo.setAttfileFlag(1);
					 }
				}
			}
			 
			hwinfoManager.save(hwinfo,username , ouserid);
			uploadFileManager.updateFileInfo(hwinfo.getId(),2,attfileIds,null);
			
			final List<String> urlList = hwinfo.getUrlList();
			final Long fid = hwinfo.getId();
			if(Validator.isNotNull(urlList)){
				new Thread(new Runnable() {
					public void run() {
						 favoriteManager.urlFileDownload(urlList,fid,2);
					}
				}).start();
			}
			return fid;
		}catch (Exception e) {
			logger.error("专项信息报送出错",e);
			throw new ServiceException("服务器内部错误");
		}
	}
	
	/**
	 * 互联网信息报送
	 * @param hwinfo 专项信息
	 * @param username 操作用户的登录名
	 * @param ouserid 操作的用户ID
	 * @return 是否成功
	 * @param attfileFlag 是否存在附件，如果为空或者为零，表示没有附件，如果为1表示存在附件
	 * @param attfileIds 该报送附件的id数组
	 * @throws Exception
	 */
	public String reportWinfo(Winfo winfo,String username ,Long ouserid,Integer attfileFlag,String[] attfileIds,String comeFrom) throws Exception {
		try{
			if(Validator.isNull(username)||Validator.isNull(ouserid)||Validator.isNull(comeFrom)){
				throw new ServiceException("查询接口参数有误");
			}
			winfo.setStatus(1);
			if(Validator.isNotNull(attfileIds)){
				String ids = "";
				for (String id : attfileIds) {
					if(Validator.isNotNull(id)){
						if(Validator.isNotNull(id))ids += id + ",";
					}
				}
				if(Validator.isNotNull(ids)) {
					ids = ids.substring(0, ids.length()-1);
				}
				List<Criterion> list = new ArrayList<Criterion>();
				if(Validator.isNotNull(attfileFlag)){	
					winfo.setAttfileFlag(attfileFlag);
				}else{//如果为空，查询数据库
					 list = new ArrayList<Criterion>();
					 list.add(Restrictions.sqlRestriction("id in ("+ids+")"));
					 list.add(Restrictions.or(Restrictions.eq("extend1", 0),Restrictions.eq("extend1", 4)));
					 Criterion[] cri = new Criterion[list.size()];
					 list.toArray(cri);
					 List<UploadFile> reList = uploadFileManager.findByCriteria(cri);
					 if(Validator.isNotNull(reList)){
						 winfo.setAttfileFlag(1);
					 }
				}
			}
			winfoManager.save(winfo,username , ouserid);
			uploadFileManager.updateFileInfo(winfo.getId(),1,attfileIds,null);
			
			final List<String> urlList = winfo.getUrlList();
			final Long fid = winfo.getId();
			if(Validator.isNotNull(urlList)){
				new Thread(new Runnable() {
					public void run() {
						 favoriteManager.urlFileDownload(urlList,fid,1);
					}
				}).start();
			}
			
			return "success";
		}catch (Exception e) {
			logger.error("URL获取mht",e);
			throw new ServiceException("服务器内部错误");
		}
	}
	
	/**
	 * 互联网信息报送
	 * @param hwinfo 专项信息
	 * @param username 操作用户的登录名
	 * @param ouserid 操作的用户ID
	 * @return 是否成功
	 * @param attfileFlag 是否存在附件，如果为空或者为零，表示没有附件，如果为1表示存在附件
	 * @param attfileIds 该报送附件的id数组
	 * @throws Exception
	 */
	public Long reportWinfoReturn(Winfo winfo,String username ,Long ouserid,Integer attfileFlag,String[] attfileIds,String comeFrom) throws Exception {
		try{
			if(Validator.isNull(username)||Validator.isNull(ouserid)||Validator.isNull(comeFrom)){
				throw new ServiceException("查询接口参数有误");
			}
			if(Validator.isNotNull(attfileIds)){
				String ids = "";
				for (String id : attfileIds) {
					if(Validator.isNotNull(id)){
						if(Validator.isNotNull(id))ids += id + ",";
					}
				}
				if(Validator.isNotNull(ids)) {
					ids = ids.substring(0, ids.length()-1);
				}
				List<Criterion> list = new ArrayList<Criterion>();
				if(Validator.isNotNull(attfileFlag)){	
					winfo.setAttfileFlag(attfileFlag);
				}else{//如果为空，查询数据库
					 list = new ArrayList<Criterion>();
					 list.add(Restrictions.sqlRestriction("id in ("+ids+")"));
					 list.add(Restrictions.or(Restrictions.eq("extend1", 0),Restrictions.eq("extend1", 4)));
					 Criterion[] cri = new Criterion[list.size()];
					 list.toArray(cri);
					 List<UploadFile> reList = uploadFileManager.findByCriteria(cri);
					 if(Validator.isNotNull(reList)){
						 winfo.setAttfileFlag(1);
					 }
				}
			}
			winfo.setStatus(1);
			winfoManager.save(winfo,username , ouserid);
			uploadFileManager.updateFileInfo(winfo.getId(),1,attfileIds,null);
			
			final List<String> urlList = winfo.getUrlList();
			final Long fid = winfo.getId();
			if(Validator.isNotNull(urlList)){
				new Thread(new Runnable() {
					public void run() {
						 favoriteManager.urlFileDownload(urlList,fid,1);
					}
				}).start();
			}
			
			return fid;
		}catch (Exception e) {
			logger.error("URL获取mht",e);
			throw new ServiceException("服务器内部错误");
		}
	}
	
	/**
	 * 获取资源列表
	 * @param resources  资源类,支持查询字段有:comFrom、enable、level、name（前后%name%）、typeId
	 * @param ouserid 系统用户ID
	 * @param comeFrom 系统来源
	 * @param flag flag为1，代表根据权限查询，为空或者其他，不按权限
	 * @return
	 * @throws Exception
	 */
	public List<SysResource> getResources(SysResource resources, Long ouserid,String comeFrom,String flag) throws Exception {
		logger.info("ouserid:"+ouserid+";comeFrom:"+comeFrom+";flag:"+flag);
		try{
			if(Validator.isNull(comeFrom)){
				throw new ServiceException("查询接口参数有误");
			}
			List<Criterion> list = new ArrayList<Criterion>();
			if(Validator.isNotNull(resources)){
				if (Validator.isNotNull(resources.getEnable())) {
					list.add(Restrictions.eq("enable", resources.getEnable()));
				}
				if (Validator.isNotNull(resources.getLevel())) {
					list.add(Restrictions.eq("level", resources.getLevel()));
				}
				if (Validator.isNotNull(resources.getTypeId())) {
					list.add(Restrictions.eq("typeId", resources.getTypeId()));
				}
				if(Validator.isNotNull(resources.getComeFrom())){
					list.add(Restrictions.eq("comeFrom", resources.getComeFrom()));
				} 
				if(Validator.isNotNull(resources.getName())){
					list.add(Restrictions.like("name", resources.getName(),MatchMode.ANYWHERE));
				} 
			}
			if(Validator.isNotNull(flag) && flag.equals("1")){
				//获取用户的资源id
				SysUser user = sysUserManager.load(ouserid);
				if(Validator.isNull(user)){
					throw new ServiceException("用户不存在");
				}
				//计算资源总和
				List<SysRole>  roleList = sysRoleManager.getRoleByRids(user.getRids());
				String sresources = "";
				
				if(Validator.isNotNull(roleList)){
					int len = roleList.size();
					ArrayList<String> rList = new ArrayList<String>();
					for(int i = 0 ; i < len ; i++){
						SysRole sysRole = roleList.get(i);
						String resource = sysRole.getResources();
						if(Validator.isNotNull(resource)){
							String[] res = resource.split(",");
							int rlen = res.length;
							for(int n = 0 ; n < rlen ; n++){
								String rid = res[n];
								if(Validator.isNotNull(rid)){
									if(!rList.contains(rid)){
										rList.add(rid);
										sresources +=rid+",";
									}
								}
							}
						}
					}
					rList = null;
				}
				if(Validator.isNotNull(sresources)){
					sresources = sresources.replaceAll(",,", ",");
					while(sresources.endsWith(",")){
						sresources = sresources.substring(0,sresources.length()-1);
					}
					
					list.add(Restrictions.sqlRestriction("id in ("+sresources+")"));
				}else{
					return null;
				}
			}
			Criterion[] cri = new Criterion[list.size()];
			list.toArray(cri);
			logger.info("cri:"+list.toString());
			List<SysResource> resourcesList = sysResourceManager.findByCriteria(cri);
			return resourcesList;
		}catch (Exception e) {
			logger.error("获取菜单资源失败",e);
			throw new ServiceException("服务器内部错误");
		}
	}
	public SysUser getUserNP(String bnpn, String comeFrom) throws Exception {
		if(Validator.isNull(bnpn)||Validator.isNull(comeFrom)){
			throw new ServiceException("查询接口参数有误");
		}
		String npn = Base64.decode(bnpn);
		String[] npnArray = npn.split("!!!");
		int len = npnArray.length;
		if(len < 2){
			throw new ServiceException("查询接口参数有误");
		}
		// 单点登录
		String username = npnArray[0];
		String password = npnArray[1];
		String number = "";
		if(len == 3){
			number = npnArray[2];
		}
		SysUser user = sysUserManager.getSysUserByLoginname(username);
		
		if (Validator.isNull(user)) {
			throw new ServiceException("用户名或密码错误");
		}
		Integer pflag = user.getPflag();
		//如果用户被锁定，不让登陆
		if(Validator.isNotNull(pflag) && pflag.equals(1)){
			throw new ServiceException("用户已被锁定,无法登录系统,请联系管理员");
		}
		
		String[] rolecodes = user.getRolecode().split(",");
		boolean padFlag = true;
		for(int i = 0 ; i < rolecodes.length ; i++){
			String code = rolecodes[i];
			if(!code.equals("PAD")){
				padFlag = false;
				break;
			}
		}
		//如果是pad用户，不让登录
		if(padFlag){
			throw new ServiceException("PAD用户无法登录系统");
		}
		
		if (!user.getPassword().equals(password)) {
			throw new ServiceException("用户名或密码错误");
		}

		if (user.getStatus().equals(0)) {
			throw new ServiceException("该帐户已经停用");
		}
		Date expired = user.getExpiringdate();
		if (Validator.isNotNull(expired) && expired.getTime() < (new Date()).getTime()) {
			throw new ServiceException("该帐户已经过期,请联系管理人员");
		}
		
			if(Validator.isNotNull(number)){
				if ("1171e7260001000024f8".equalsIgnoreCase(number)) {
					logger.info("拥有管理员key,直接通过验证,登录名:" + username);
				} else if (!number.equalsIgnoreCase(user.getUkeyserialno())) {
					throw new ServiceException("你所登陆的用户与所用Ukey不一致，请使用该用户的Ukey");
				}
				
			}

		try {
			
			if(Validator.isNull(user.getRids())){
				throw new ServiceException("该帐户无任何用户角色");
			}
			//计算资源总和,菜单先不判断
//			List<SysRole>  roleList = sysRoleManager.getRoleByRids(user.getRids());
//			String resources = "";
//			if(Validator.isNotNull(roleList)){
//				len = roleList.size();
//				ArrayList<String> rList = new ArrayList<String>();
//				for(int i = 0 ; i < len ; i++){
//					SysRole sysRole = roleList.get(i);
//					String resource = sysRole.getResources();
//					if(Validator.isNotNull(resource)){
//						String[] res = resource.split(",");
//						int rlen = res.length;
//						for(int n = 0 ; n < rlen ; n++){
//							String rid = res[n];
//							 
//							if(!rList.contains(rid)){
//								rList.add(rid);
//								resources +=rid+",";
//							}
//						}
//					}
//				}
//				rList = null;
//			}
//			 
//			sysUserManager.evict(user);
//			user.setPassword("");
			return user;
			// 缓存用户资源
		} catch (Exception e) {
			logger.error("缓存用户资源出错:" + e.getMessage());
			throw new ServiceException("服务器内部错误");
		}
	}
	/**
	 * 判断URL是否在专项中已经存在
	 * @param url url
	 * @param ouserid 操作用户ID
	 * @param comeFrom 系统来源
	 * @return 返回结果，1存在，0不存在
	 * @throws Exception
	 */
	public String checkURL(String url, Long ouserid, String comeFrom) throws Exception {
		try{
			if(Validator.isNull(url)||Validator.isNull(ouserid)||Validator.isNull(comeFrom)){
				throw new ServiceException("查询接口参数有误");
			}
			List<Criterion> criterionList = new ArrayList<Criterion>();
			if (Validator.isNotNull(url)) {
				criterionList.add(Restrictions.eq("siteurls", url.trim()));
			}
			Criterion[] criterions = new Criterion[criterionList.size()];
			criterionList.toArray(criterions);
			List<Hwinfo> list = hwinfoManager.findByCriteria(criterions);
			// list为空，则无重复
			if (Validator.isNull(list)) {
				return "0";
			} else{
				return "1";
			}
		}catch (Exception e) {
			logger.error("判断URL重复出错:" + e.getMessage());
			throw new ServiceException("服务器内部错误");
		}
	}
	

	/**
	 * 获取所有用户
	 * @param comeFrom 系统来源
	 * @return
	 * @throws Exception
	 */
	public List<SysUser> getAllSysUser(String comeFrom) throws Exception {
		try{
			if(Validator.isNull(comeFrom)){
				throw new ServiceException("查询接口参数有误");
			}
			return sysUserManager.getAll();
		}catch (Exception e) {
			logger.error("判断URL重复出错:" + e.getMessage());
			throw new ServiceException("服务器内部错误");
		}
	}
	
	
	
	/**
	 * 上报信息
	 * @param hwinfo 上报信息
	 * @param username 操作用户的登录名
	 * @param ouserid 操作的用户ID
	 * @return 是否成功
	 * @param attfileIds 该报送附件的id数组
	 * @param comeFrom 系统来源
	 * @throws Exception
	 */
	public String reportPre(PreReport preReport, Long ouserid, String[] attfileIds, String comeFrom) throws Exception {
		try{
			if(Validator.isNull(preReport)||Validator.isNull(ouserid)||Validator.isNull(comeFrom)){
				throw new ServiceException("查询接口参数有误");
			}
			
			
			if(Validator.isNull(preReport.getComeFrom())){
				preReport.setComeFrom("巡查平台");
			}
			
			if(Validator.isNull(preReport.getTime())){
				preReport.setTime(new Date());
			}
			
			if(Validator.isNull(preReport.getTopFlag())){
				preReport.setTopFlag(0);
			}
			if(Validator.isNull(preReport.getIsread()))preReport.setIsread(0);
			if(Validator.isNull(preReport.getIspush()))preReport.setIspush(0);
			if(Validator.isNull(preReport.getIsreport()))preReport.setIsreport(0);
			if(Validator.isNull(preReport.getAttachmentId()))preReport.setAttachmentId(0L);
			
			if(Validator.isNull(preReport.getIsView())) preReport.setIsView(0);
			
//			if(Validator.isNull(preReport.getAttfileFlag())){
//				if(Validator.isNotNull(attfileIds)){
//					preReport.setAttfileFlag(1);
//				}
//			}
			
			if(Validator.isNull(preReport.getIsSend()))preReport.setIsSend(0);
			preReportManager.save(preReport);
			uploadFileManager.updateFileInfo(preReport.getId(),333,attfileIds,null);
			
			//更新截图和附件标志信息
			preReportManager.updateAttfileFlag(preReport.getId());
			preReportManager.updateCutImgFlag(preReport.getId());
			final List<String> urlList = preReport.getUrlList();
			final Long fid = preReport.getId();
			if(Validator.isNotNull(urlList)){
				new Thread(new Runnable() {
					public void run() {
						 favoriteManager.urlFileDownload(urlList,fid,11);
					}
				}).start();
			}
			
			return "success";
		}catch (Exception e) {
			logger.error("上报信息出错",e);
			throw new ServiceException("服务器内部错误");
		}
	}
	
	
	public Long reportPreReturn(PreReport preReport, Long ouserid, String[] attfileIds, String comeFrom) throws Exception {
		try{
			if(Validator.isNull(preReport)||Validator.isNull(ouserid)||Validator.isNull(comeFrom)){
				throw new ServiceException("查询接口参数有误");
			}
			if(Validator.isNull(preReport.getComeFrom())){
				preReport.setComeFrom("巡查平台");
			}
			if(Validator.isNull(preReport.getTime())){
				preReport.setTime(new Date());
			}
			if(Validator.isNull(preReport.getTopFlag())){
				preReport.setTopFlag(0);
			}
			if(Validator.isNull(preReport.getIsread()))preReport.setIsread(0);
			if(Validator.isNull(preReport.getIspush()))preReport.setIspush(0);
			if(Validator.isNull(preReport.getIsreport()))preReport.setIsreport(0);
			if(Validator.isNull(preReport.getAttachmentId()))preReport.setAttachmentId(0L);
			
			if(Validator.isNull(preReport.getIsView())) preReport.setIsView(0);
			
//			if(Validator.isNull(preReport.getAttfileFlag())){
//				if(Validator.isNotNull(attfileIds)){
//					preReport.setAttfileFlag(1);
//				}
//			}
			
			if(Validator.isNull(preReport.getIsSend()))preReport.setIsSend(0);
			
			preReportManager.save(preReport);
			uploadFileManager.updateFileInfo(preReport.getId(),333,attfileIds,null);
			
			//更新截图和附件标志信息
			preReportManager.updateAttfileFlag(preReport.getId());
			preReportManager.updateCutImgFlag(preReport.getId());
			
			final List<String> urlList = preReport.getUrlList();
			final Long fid = preReport.getId();
			if(Validator.isNotNull(urlList)){
				new Thread(new Runnable() {
					public void run() {
						 favoriteManager.urlFileDownload(urlList,fid,11);
					}
				}).start();
			}
			
			return fid;
		}catch (Exception e) {
			logger.error("上报信息出错",e);
			throw new ServiceException("服务器内部错误");
		}
	}
	
	
	/**
	 * 上报信息
	 * @param hwinfo 上报信息
	 * @param username 操作用户的登录名
	 * @param ouserid 操作的用户ID
	 * @return 是否成功
	 * @param attfileIds 该报送附件的id数组
	 * @param comeFrom 系统来源
	 * @param wechat 微信号码
	 * @throws Exception
	 */
	public String reportPreWeChat(PreReport preReport, Long ouserid, String[] attfileIds, String comeFrom,String wechat) throws Exception {
		try{
			if(Validator.isNull(preReport)||Validator.isNull(ouserid)||Validator.isNull(comeFrom)||Validator.isNull(wechat)){
				throw new ServiceException("查询接口参数有误");
			}
			
			//根据微信号码查找相应的用户
			SysUser sysUser = sysUserManager.getSysUserByWeChat(wechat);
			if(Validator.isNotNull(sysUser)){
				preReport.setUserId(sysUser.getId());
				preReport.setUsername(sysUser.getName());
			}else{
				throw new ServiceException("无效用户");
			}
			if(Validator.isNull(preReport.getTime())){
				preReport.setTime(new Date());
			}
			if(Validator.isNull(preReport.getTopFlag())){
				preReport.setTopFlag(0);
			}
			if(Validator.isNull(preReport.getIsread()))preReport.setIsread(0);
			if(Validator.isNull(preReport.getIspush()))preReport.setIspush(0);
			if(Validator.isNull(preReport.getIsreport()))preReport.setIsreport(0);
			if(Validator.isNull(preReport.getAttachmentId()))preReport.setAttachmentId(0L);
			if(Validator.isNull(preReport.getIsSend()))preReport.setIsSend(0);
			preReportManager.save(preReport);
			uploadFileManager.updateFileInfo(preReport.getId(),333,attfileIds,null);
			
			//更新截图和附件标志信息
			preReportManager.updateAttfileFlag(preReport.getId());
			preReportManager.updateCutImgFlag(preReport.getId());
			
			final List<String> urlList = preReport.getUrlList();
			
			if(Validator.isNotNull(urlList)){
				logger.info("上报图片有:"+urlList.size()+"张！");
			}else{
				logger.info("上报信息不带图片！");
			}
			
			final Long fid = preReport.getId();
			if(Validator.isNotNull(urlList)){
				new Thread(new Runnable() {
					public void run() {
						 favoriteManager.urlFileDownload(urlList,fid,11);
					}
				}).start();
			}
			
			return "success";
		}catch (Exception e) {
			logger.error("上报信息出错",e);
			throw new ServiceException("服务器内部错误");
		}
	}
	
	
	public Long reportPreReturnWeChat(PreReport preReport, Long ouserid, String[] attfileIds, String comeFrom,String wechat) throws Exception {
		try{
			if(Validator.isNull(preReport)||Validator.isNull(ouserid)||Validator.isNull(comeFrom)){
				throw new ServiceException("查询接口参数有误");
			}
			//根据微信号码查找相应的用户
			SysUser sysUser = sysUserManager.getSysUserByWeChat(wechat);
			if(Validator.isNotNull(sysUser)){
				preReport.setUserId(sysUser.getId());
				preReport.setUsername(sysUser.getName());
			}else{
				throw new ServiceException("无效用户");
			}
			if(Validator.isNull(preReport.getTime())){
				preReport.setTime(new Date());
			}
			if(Validator.isNull(preReport.getTopFlag())){
				preReport.setTopFlag(0);
			}
			if(Validator.isNull(preReport.getIsread()))preReport.setIsread(0);
			if(Validator.isNull(preReport.getIspush()))preReport.setIspush(0);
			if(Validator.isNull(preReport.getIsreport()))preReport.setIsreport(0);
			if(Validator.isNull(preReport.getAttachmentId()))preReport.setAttachmentId(0L);
			if(Validator.isNull(preReport.getIsSend()))preReport.setIsSend(0);
			preReportManager.save(preReport);
			uploadFileManager.updateFileInfo(preReport.getId(),333,attfileIds,null);
			
			//更新截图和附件标志信息
			preReportManager.updateAttfileFlag(preReport.getId());
			preReportManager.updateCutImgFlag(preReport.getId());
			
			final List<String> urlList = preReport.getUrlList();
			final Long fid = preReport.getId();
			if(Validator.isNotNull(urlList)){
				new Thread(new Runnable() {
					public void run() {
						 favoriteManager.urlFileDownload(urlList,fid,11);
					}
				}).start();
			}
			
			return fid;
		}catch (Exception e) {
			logger.error("上报信息出错",e);
			throw new ServiceException("服务器内部错误");
		}
	}
	
	/**
	 * 获取平台公告，只取通知对象有ouserid的
	 * @param comeFrom 系统来源
	 * @return
	 */
	public Page<SysNotice> getSysNotice(SysNotice sysNotice, Page<SysNotice> page,Long ouserid, String comeFrom) {
		try{
			if(Validator.isNull(sysNotice)||Validator.isNull(ouserid)||Validator.isNull(comeFrom)){
				throw new ServiceException("查询接口参数有误");
			}
			List<Criterion> list = new ArrayList<Criterion>();
			Criterion[] criterions = new Criterion[list.size()];
			String content = sysNotice.getContent();
			String name = sysNotice.getOpName();
			if(Validator.isNotNull(content)) {
				list.add(Restrictions.like("content", content.trim(), MatchMode.ANYWHERE)); 
			}
			if(Validator.isNotNull(name)) {
				list.add(Restrictions.like("opName", name.trim(), MatchMode.ANYWHERE)); 
			}
			
			list.add(Restrictions.sqlRestriction("','+to_userids+',' LIKE '%,"+ouserid+",%'"));
			
//			SysUser user = sysUserManager.get(ouserid);
//			//获取权限用户
//			String code = user.getDecode();
//			String rolecode = user.getRolecode();
//			
//			Object[] ouser = null;
//			//如果用户是管理员
//			if(rolecode.indexOf("ADMIN") != -1 || rolecode.indexOf("MANAGER") != -1){
//				List<SysUser> userList = sysUserManager.getUserByCode(code);
//				if(Validator.isNotNull(userList)){
//					ouser = new Object[userList.size()];
//					for(int i = 0 ; i < userList.size() ; i++){
//						 ouser[i] = userList.get(i).getId();
//					}
//				}
//			}
//			else{
//				ouser = new Object[1];
//				ouser[0] = user.getId();
//			}
//		   if(Validator.isNotNull(ouser))
//			   list.add(Restrictions.in("opUserid", ouser));
		   
			
			criterions = new Criterion[list.size()];
			list.toArray(criterions);
			if(page.getTotalCount() == 0){
				page.setAutoCount(true);
			}
			page = sysNoticeManager.getAll(page, criterions, Order.asc("id"));
			return page;
		}catch (Exception e) {
			logger.error("获取平台公告失败",e);
			throw new ServiceException("服务器内部错误");
		}
	}
	
	/**
	 * 微博巡查日志
	 * @param wbPatrolLog 微博巡查日志
	 * @param ouserid 操作用户ID
	 * @param comeFrom 系统来源
	 * @return
	 */
	public String addWbPatrolLog(WbPatrolLog wbPatrolLog, Long ouserid,String comeFrom) {
		try{
			if(Validator.isNull(wbPatrolLog)||Validator.isNull(ouserid)||Validator.isNull(comeFrom)){
				throw new ServiceException("查询接口参数有误");
			}
			wbPatrolLog.setAddtime(DateUtil.convertDateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
			wbPatrolLogManager.save(wbPatrolLog);
			return "success";
		}catch (Exception e) {
			logger.error("添加微博巡查日志出错",e);
			throw new ServiceException("服务器内部错误");
		}
	}
	/**
	 * 系统日志保存
	 * @param sysLog 系统日志
	 * @param ouserid 日志操作用户
	 * @param comeFrom 系统来源
	 * @return 是否添加成功
	 */
	public String addLog(Log log, Long ouserid, String comeFrom) throws Exception {
		if(Validator.isNull(ouserid)||Validator.isNull(comeFrom)){
			throw new ServiceException("查询参数有误");
		}else{
			try{
				logManager.save(log);
			}catch (Exception e) {
				logger.error("添加日志失败",e);
				throw new ServiceException("添加日志成功失败");
			}
		}
		return "success";
	}
	
	/**
	 * @param ids 角色ids串,可为空,如果为空,返回所有角色
	 * @param ouserid 操作用户ID,可为空
	 * @param  comeFrom 系统来源 
	 * @return 返回角色集合,如果不存在,角色集合为空
	 */
	public List<SysRole> getRoleList(String ids, Long ouserid, String comeFrom) throws Exception {
		List<SysRole> roleList = null;
		if(Validator.isNull(comeFrom)){
			throw new ServiceException("查询参数有误");
		}else{
			try{
				if(Validator.isNotNull(ids)){
					if(ids.endsWith(",")){
						ids = ids.substring(0,ids.length() - 1);
					}
					roleList = sysRoleManager.getRoleByRids(ids);
				}else{
					roleList = sysRoleManager.getAll();
				}
			}catch (Exception e) {
				logger.error("获取角色集合失败",e);
				throw new ServiceException("获取角色集合失败");
			}
		}
		return roleList;
	}
	/**
	 * 修改用户密码
	 * @param uid 用户id
	 * @param pwd 用户新密码（md5）
	 * @param ouserid 操作用户id
	 * @param comeFrom 系统来源
	 * @return 返回是否更改成功标志,success是成功
	 */
	public String alterpw(Long uid, String pwd, Long ouserid, String comeFrom) throws Exception {
		if(Validator.isNull(comeFrom) || Validator.isNull(uid) || Validator.isNull(pwd) || Validator.isNull(ouserid)){
			throw new ServiceException("查询参数有误");
		}else{
			try{
				 SysUser sysUser = sysUserManager.get(uid);
				 if (Validator.isNotNull(sysUser)) {
					sysUser.setPassword(pwd);
					sysUserManager.save(sysUser);
				}
				return "success";
			}catch (Exception e) {
				throw new ServiceException("更改密码失败失败");
			}
		}
	}
	/**
	 * 绑定微信号
	 * @param loginname 账号
	 * @param wechat 微信uin
	 * @param comeFrom 系统来源
	 * @return 返回是否更改成功标志,success是成功
	 */
	public String bindWeChat(String loginname, String wechat, String comeFrom) throws Exception {
		try{
			
			if(Validator.isNull(loginname)||Validator.isNull(wechat)||Validator.isNull(comeFrom)){
				throw new ServiceException("查询参数不正确");
			}
			SysUser sysUser = sysUserManager.getSysUserByLoginname(loginname);
			
			if(Validator.isNull(sysUser)){
				throw new ServiceException("无此账号存在");
			}
			
			logger.debug("获取参数为,loginname:"+loginname+";wechat:"+wechat);
			
//			if(Validator.isNotNull(sysUser.getWechat())){
//				throw new ServiceException("该账号已绑定微信号");
//			}
			
			
//			List<SysUser> sysUserList = null;
			
//			List<Criterion> userCriterion = new ArrayList<Criterion>();
//			userCriterion.add(Restrictions.eq("wechat",wechat));
//			Criterion[] cri = new Criterion[userCriterion.size()];
//			userCriterion.toArray(cri);
//			sysUserList = sysUserManager.getAll(cri);
//			if(Validator.isNotNull(sysUserList) && sysUserList.size()>0){
//				throw new ServiceException("该微信号已绑定账号");
//			}
			
			String hql = "update SysUser set wechat='' where wechat='"+wechat+"'";
			sysUserManager.execute(hql);
			sysUser.setWechat(wechat);
			sysUserManager.save(sysUser);
			
			return "success";
		}catch (Exception e) {
			logger.debug("获取参数为,loginname:"+loginname+";wechat:"+wechat);
			logger.error("服务器内部错误",e);
			throw new ServiceException("服务器内部错误");
		}
	}
	public String checkPreURL(String url, Long ouserid, String comeFrom,Long preId)throws Exception {
		try{
			if(Validator.isNull(url)||Validator.isNull(ouserid)||Validator.isNull(comeFrom)){
				throw new ServiceException("查询接口参数有误");
			}
			SysUser user = sysUserManager.get(ouserid);
			
			SysGroup sysGroup = sysGroupManager.get(user.getGroupid());
			
			String code = sysGroup.getCode();
			boolean flag = false;
			
			List<PreReport> preReportList = preReportManager.hasRepeatUrl(code, url, flag);
			
			if(Validator.isNull(preReportList)){
				return "0";
			}
			
			if(Validator.isNotNull(preId) && Validator.isNotNull(preReportList)){
				int preSize = preReportList.size();
				if(preSize == 1 && preId.equals(preReportList.get(0).getId().toString())){
					return "0";
				}
			}
			return "1";
		}catch (Exception e) {
			logger.error("判断URL重复出错:" + e.getMessage());
			throw new ServiceException("服务器内部错误");
		}
	}
	
	/**
	 * @author fumx
	 */
	public String syncKeys(String keysJSON, Long ouserid, String comeFrom)throws Exception {
		try {
			if (Validator.isNull(keysJSON) || Validator.isNull(ouserid)
					|| Validator.isNull(comeFrom)) {
				throw new ServiceException("查询接口参数有误");
			}
			
			final String keysJson=keysJSON;//常量化参数供内部类调用
			final Long ouserId=ouserid;
			final String comeFROM=comeFrom;
			
			Set<Entry<String,String>> thirdSet = Config.thirdParSysMap.entrySet();
			Iterator<Entry<String, String>> it = thirdSet.iterator();
			
			while(it.hasNext()){
				Entry<String, String> entry = it.next();
				//第三方系统名称
				final String cm = entry.getKey();
				//第三方系统
				final String url = entry.getValue();
				if(url.equals("local")){//更新本地库
					policyMangaer.parseJSON(keysJSON, ouserid,"0",comeFrom);
				}else{
					new Thread(new Runnable() {//直接使用线程
						@Override
						public void run() {
							String flag="false";
							final JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();  
							factory.setAddress(url);  
							factory.setServiceClass(PatrolService.class);  
							PatrolService patrolService = (PatrolService) factory.create();  
							CXFClientUtil.setTimeout(patrolService);
							try {
								flag=patrolService.syncKeys(keysJson, ouserId, comeFROM);//传数据
							} catch (Exception e){
								e.printStackTrace();
							}finally{//验证是否同步成功
								if (!flag.equals("success")) {//同步失败，将失败信息存入数据库
									SysSyncFail ssf=new SysSyncFail();
									ssf.setKeysJSON(keysJson);
									ssf.setSystem(url);
									ssf.setUserid(ouserId.intValue());
									ssf.setComeFrom(comeFROM);
									try {
										ssf.setFailtime(DateUtil.getFormatDate());
									} catch (ParseException e1) {
										logger.error("同步失败信息存储出错");
									}
									sysFailLogMangaer.save(ssf);
								}
							}
						}}).start();
				}
			}
			return "success";
		} catch (Exception e) {
			throw new ServiceException("服务器内部错误");
		}
	}
	/**
	 * 
	 * @param parId 父节点ID
	 * @param ouserid 操作用户ID,可为空
	 * @param  comeFrom 系统来源 
	 * @return 返回用户组集合
	 */
	public List<SysGroup> getSysGroupList(Long parId, Long ouserid, String comeFrom) throws Exception {
		try {
			if (Validator.isNull(parId) || Validator.isNull(comeFrom)) {
				throw new ServiceException("查询接口参数有误");
			}
			List<SysGroup> returnList = null;
			returnList = sysGroupManager.findByProperty("parId", parId);
			return returnList;
		} catch (Exception e) {
			throw new ServiceException("服务器内部错误");
		}
	}
	/**
	 * @param code 父节点ID
	 * @param ouserid 操作用户ID,可为空
	 * @param cflag 是否包含非直接子节点，空为只取直接子节点，非空取所有
	 * @param sflag	是否获取包含code的节点，空为不获取，非空获取
	 * @param  comeFrom 系统来源 
	 * @return 返回用户组集合
	 */
	public List<SysGroup> getSysGroupListByCode(String code, Long ouserid,
			String cflag, String sflag, String comeFrom) throws Exception {
		try {
			if (Validator.isNull(code) || Validator.isNull(comeFrom)) {
				throw new ServiceException("查询接口参数有误");
			}
			List<SysGroup> returnList = new ArrayList<SysGroup>();
			
			if(Validator.isNotNull(cflag)){//去所有节点
				if(Validator.isNotNull(sflag)){//包含自己的节点
					returnList = sysGroupManager.getAll(Restrictions.or(Restrictions.like("code", code+"-", MatchMode.START),Restrictions.eq("code", code)));
				}else{
					returnList = sysGroupManager.getAll(Restrictions.like("code", code+"-", MatchMode.START));
				}
			}else{//取直接下级节点
				SysGroup sysGroup = sysGroupManager.findByPropertySingle("code", code);
				if(Validator.isNotNull(sflag)){
					returnList.add(sysGroup);
				}
				if(Validator.isNotNull(sysGroup)){
					List<SysGroup> cList = sysGroupManager.findByProperty("parId", sysGroup.getId());
					if(Validator.isNotNull(cList)){
						returnList.addAll(cList);
					}
				}
			}
			return returnList;
		} catch (Exception e) {
			throw new ServiceException("服务器内部错误");
		}
	}
	/**
	 * 根据用户姓名获取用户，不做任何逻辑处理，只取符合条件的用户信息
	 * @param groupId 用户的组id
	 * @param ouserid 操作的用户ID
	 * @param comeFrom 系统来源
	 * @return 用户
	 */
	public List<SysUser> getUserByGroupId(Long groupId, Long ouserid,
			String comeFrom) throws Exception {
		try{
			if(Validator.isNull(groupId)|| Validator.isNull(ouserid) || Validator.isNull(comeFrom)){
				throw new ServiceException("查询参数有误");
			}else{
				List<SysUser> userList = sysUserManager.findByProperty("groupid", groupId);
				for(int i = 0 ; i < userList.size();i++){
					SysUser user = userList.get(i);
					if(Validator.isNotNull(user)){
						sysUserManager.evict(user);
						user.setPassword("");
					}
				}
				return userList;
			}
		}catch (Exception e) {
			throw new ServiceException("服务器内部错误");
		}
	}
	
	
	public Long reportFinfoReturn(Finfo finfo, String username, Long ouserid,
			Integer cutImgFlag,String[] attfileIds,String comeFrom) throws Exception {
		try{
			if(Validator.isNull(username)||Validator.isNull(ouserid)||Validator.isNull(comeFrom)){
				throw new ServiceException("查询接口参数有误");
			}
			finfo.setStatus(1);
			if(Validator.isNotNull(attfileIds)){
				String ids = "";
				for (String id : attfileIds) {
					if(Validator.isNotNull(id)){
						if(Validator.isNotNull(id))ids += id + ",";
					}
				}
				if(Validator.isNotNull(ids)) {
					ids = ids.substring(0, ids.length()-1);
				}
				if(Validator.isNotNull(ids)){	
					finfo.setAttachmentsid(ids);
				} 
			}
			finfoManager.save(finfo,username,ouserid);
			uploadFileManager.updateFileInfo(finfo.getId(),444,attfileIds,null);
			
			final List<String> urlList = finfo.getUrlList();
			final Long fid = finfo.getId();
			if(Validator.isNotNull(urlList)){
				new Thread(new Runnable() {
					public void run() {
						 favoriteManager.urlFileDownload(urlList,fid,444);
					}
				}).start();
			}
		}catch (Exception e) {
			logger.error("URL获取mht",e);
			throw new ServiceException("服务器内部错误");
		} 
		return finfo.getId();
	}
	
	public Long reportYinfoReturn(Yinfo yinfo, String username, Long ouserid,
			Integer cutImgFlag,String[] attfileIds,String comeFrom) throws Exception {
		try{
			if(Validator.isNull(username)||Validator.isNull(ouserid)||Validator.isNull(comeFrom)){
				throw new ServiceException("查询接口参数有误");
			}
			yinfo.setStatus(1);
			if(Validator.isNotNull(attfileIds)){
				String ids = "";
				for (String id : attfileIds) {
					if(Validator.isNotNull(id)){
						if(Validator.isNotNull(id))ids += id + ",";
					}
				}
				if(Validator.isNotNull(ids)) {
					ids = ids.substring(0, ids.length()-1);
				}
				if(Validator.isNotNull(ids)){	
					yinfo.setAttachmentsid(ids);
				} 
			}
			yinfoManager.save(yinfo,username , ouserid);
			uploadFileManager.updateFileInfo(yinfo.getId(),7,attfileIds,null);
			
			final List<String> urlList = yinfo.getUrlList();
			final Long fid = yinfo.getId();
			if(Validator.isNotNull(urlList)){
				new Thread(new Runnable() {
					public void run() {
						 favoriteManager.urlFileDownload(urlList,fid,7);
					}
				}).start();
			}
		}catch (Exception e) {
			logger.error("URL获取mht",e);
			throw new ServiceException("服务器内部错误");
		} 
		return yinfo.getId();
	}

	
	/* (non-Javadoc)
	 * @see com.meiah.services.PatrolService#checkFinfoUniqueness(com.meiah.entity.reportinfo.Finfo, java.lang.Long, java.lang.String)
	 */
	public String checkFinfoUniqueness(Finfo finfo, Long ouserid,
			String comeFrom) throws Exception {
		try{
			if(Validator.isNull(finfo.getUrl())||Validator.isNull(ouserid)||Validator.isNull(comeFrom)){
				throw new ServiceException("查询接口参数有误");
			}
			List<Criterion> criterionList = new ArrayList<Criterion>();
			if (Validator.isNotNull(finfo.getUrl())) {
				criterionList.add(Restrictions.eq("url", finfo.getUrl().trim()));
			}
			Criterion[] criterions = new Criterion[criterionList.size()];
			criterionList.toArray(criterions);
			List<Finfo> list = finfoManager.findByCriteria(criterions);
			// list为空，则无重复
			if (Validator.isNull(list)) {
				return "0";
			} else{
				return "1";
			}
		}catch (Exception e) {
			logger.error("判断URL重复出错:" + e.getMessage());
			throw new ServiceException("服务器内部错误");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.meiah.services.PatrolService#checkYinfoUniqueness(com.meiah.entity.reportinfo.Yinfo, java.lang.Long, java.lang.String)
	 */
	public String checkYinfoUniqueness(Yinfo yinfo, Long ouserid,
			String comeFrom) throws Exception {
		try{
			if(Validator.isNull(yinfo.getUrl())||Validator.isNull(ouserid)||Validator.isNull(comeFrom)){
				throw new ServiceException("查询接口参数有误");
			}
			List<Criterion> criterionList = new ArrayList<Criterion>();
			if (Validator.isNotNull(yinfo.getUrl())) {
				criterionList.add(Restrictions.eq("url", yinfo.getUrl().trim()));
			}
			Criterion[] criterions = new Criterion[criterionList.size()];
			criterionList.toArray(criterions);
			List<Yinfo> list = yinfoManager.findByCriteria(criterions);
			// list为空，则无重复
			if (Validator.isNull(list)) {
				return "0";
			} else{
				return "1";
			}
		}catch (Exception e) {
			logger.error("判断URL重复出错:" + e.getMessage());
			throw new ServiceException("服务器内部错误");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.meiah.services.PatrolService#checkHwinfoUniqueness(com.meiah.entity.reportinfo.Hwinfo, java.lang.Long, java.lang.String)
	 */
	public String checkHwinfoUniqueness(Hwinfo hwinfo, Long ouserid,
			String comeFrom) throws Exception {
		try{
			if(Validator.isNull(hwinfo.getSiteurls())||Validator.isNull(ouserid)||Validator.isNull(comeFrom)){
				throw new ServiceException("查询接口参数有误");
			}
			List<Criterion> criterionList = new ArrayList<Criterion>();
			if (Validator.isNotNull(hwinfo.getSiteurls())) {
				criterionList.add(Restrictions.eq("siteurls", hwinfo.getSiteurls().trim()));
			}
			Criterion[] criterions = new Criterion[criterionList.size()];
			criterionList.toArray(criterions);
			List<Hwinfo> list = hwinfoManager.findByCriteria(criterions);
			// list为空，则无重复
			if (Validator.isNull(list)) {
				return "0";
			} else{
				return "1";
			}
		}catch (Exception e) {
			logger.error("判断URL重复出错:" + e.getMessage());
			throw new ServiceException("服务器内部错误");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.meiah.services.PatrolService#checkwinfoUniqueness(com.meiah.entity.reportinfo.Winfo, java.lang.Long, java.lang.String)
	 */
	public String checkWinfoUniqueness(Winfo winfo, Long ouserid,
			String comeFrom) throws Exception {
		try{
			if(Validator.isNull(winfo.getSiteurl())||Validator.isNull(ouserid)||Validator.isNull(comeFrom)){
				throw new ServiceException("查询接口参数有误");
			}
			List<Criterion> criterionList = new ArrayList<Criterion>();
			if (Validator.isNotNull(winfo.getSiteurl())) {
				criterionList.add(Restrictions.eq("siteurl", winfo.getSiteurl().trim()));
			}
			Criterion[] criterions = new Criterion[criterionList.size()];
			criterionList.toArray(criterions);
			List<Winfo> list = winfoManager.findByCriteria(criterions);
			// list为空，则无重复
			if (Validator.isNull(list)) {
				return "0";
			} else{
				return "1";
			}
		}catch (Exception e) {
			logger.error("判断URL重复出错:" + e.getMessage());
			throw new ServiceException("服务器内部错误");
		}
	}
}
