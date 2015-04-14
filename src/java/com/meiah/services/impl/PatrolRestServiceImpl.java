/** * A级 */
package com.meiah.services.impl;

import java.util.Date;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meiah.core.util.DateUtil;
import com.meiah.core.util.Validator;
import com.meiah.entity.favorite.Favorite;
import com.meiah.entity.sys.LoginMap;
import com.meiah.entity.sys.SysLog;
import com.meiah.entity.sys.SysUser;
import com.meiah.entity.task.TaskNotice;
import com.meiah.manager.favorite.FavoriteManager;
import com.meiah.manager.sys.LoginMapManager;
import com.meiah.manager.sys.SysLogManager;
import com.meiah.manager.sys.SysUserManager;
import com.meiah.manager.task.TaskNoticeManager;
import com.meiah.services.PatrolRestService;
@Path("/hello")
public class PatrolRestServiceImpl implements PatrolRestService{
	private  static Logger logger = Logger.getLogger(PatrolRestServiceImpl.class);
	@Resource
	private TaskNoticeManager taskNoticeManager;
	@Resource
	private SysLogManager sysLogManager;
	@Resource
	private FavoriteManager favoriteManager;
	@Resource
	private SysUserManager sysUserManager;
	@Resource
	private LoginMapManager loginMapManager;
	
 @GET
 @Path("/echo/{input}")
 @Produces("text/plain")
	public String testServer(String content) {
		return "From server : " +content;
	}
	/**
	 * 添加任务提醒,userid、tuserid、content都不能为空
	 * @param taskNotice
	 * @return json字符串
	 */
	public String addTaskNotice(TaskNotice taskNotice){
		logger.info("添加任务提醒开始...");
		JSONObject result = new JSONObject();
		try{
			if(Validator.isNull(taskNotice)){
				result.put("status", "faile");
				result.put("result", "任务对象不能为空");
			}else{
				if(Validator.isNull(taskNotice.getTime())){
					taskNotice.setTime(DateUtil.convertDateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
				}
				Long fuserid = taskNotice.getUserid();
				Long tuserid = taskNotice.getTuserid();
				String content = taskNotice.getContent();
				if(Validator.isNull(fuserid) || Validator.isNull(tuserid) || Validator.isNull(content)){
					result.put("status", "faile");
					result.put("result", "任务提醒参数不正确");
				}else{
					taskNoticeManager.save(taskNotice);
					result.put("status", "success");
//					sysLogManager.recordLog(fuserid , "添加巡查任务提醒["+content+"]" , "添加巡查任务提醒["+content+"]",context);
				}
			}
		}catch (Exception e) {
			result.put("status", "faile");
			result.put("result", "保存任务提醒失败");
			logger.error("添加任务提醒失败", e);
		}
		logger.info("添加任务提醒结束...");
		return  JSON.toJSONString(result);
	}
	/**
	 * 根据用户ID获取用户收藏的标签(自己看自己的,去除为空,为''的标签)
	 * @param userid 用户标签
	 * @param suserid 哪个用户查询
	 * @return 标签的JSON串
	 */
	public String favoriteLabel(Long userid,Long suserid) {
		if(Validator.isNull(userid)){
			JSONObject result = new JSONObject();
			result.put("status", "faile");
			result.put("result", "查询参数不能为空");
			return result.toJSONString();
		}else{
			try{
				JSONObject result = favoriteManager.getLabelByUserid(userid);
				result.put("status", "success");
				return result.toJSONString();
			}catch (Exception e) {
				JSONObject result = new JSONObject();
				result.put("status", "faile");
				result.put("result", "获取收藏标签失败");
				logger.error("获取收藏标签失败", e);
				return result.toJSONString();
			}
		}
	}
	/**
	 * 添加收藏
	 * @param favorite 要添加的收藏
	 * @param ouserid 收藏的用户
	 * @return 是否收藏成功
	 */
	public String addFavorite(Favorite favorite, Long ouserid) {
		JSONObject result = new JSONObject();
		if(Validator.isNull(ouserid) || Validator.isNull(favorite)){
			result.put("status", "faile");
			result.put("result", "查询参数不能为空");
		}else{
			try{
				favoriteManager.save(favorite);
				result.put("status", "success");
				result.put("result", "添加收藏成功");
			}catch (Exception e) {
				result.put("status", "faile");
				result.put("result", "获取收藏标签失败");
			}
		}
		return result.toJSONString();
	}
	
	
	/**
	 * 系统日志保存
	 * @param sysLog 系统日志
	 * @param ouserid 日志操作用户
	 * @return 是否添加成功
	 */
	public String addLog(SysLog sysLog, Long ouserid) {
		JSONObject result = new JSONObject();
		if(Validator.isNull(ouserid)){
			result.put("status", "faile");
			result.put("result", "查询参数不能为空");
		}else{
			try{
				sysLogManager.save(sysLog);
				result.put("status", "success");
				result.put("result", "添加日志成功");
			}catch (Exception e) {
				result.put("status", "faile");
				result.put("result", "添加日志成功失败");
			}
		}
		return result.toJSONString();
	}
	/**
	 * 根据浏览器序列号获取在线用户，不做任何逻辑处理，只取符合条件的用户信息
	 * @param loginName 用户的登录名
	 * @param ouserid 操作的用户ID
	 * @return 用户的JSON串
	 */
	public String getLoginMapOnLine(String objid, Long ouserid) {
		JSONObject result = new JSONObject();
		try{
			if(Validator.isNull(objid) || Validator.isNotNull(ouserid)){
				result.put("status", "faile");
				result.put("result", "查询条件为空");
			}else{
				String username = "";
				//根据objid判断是否为登陆状态
				LoginMap loginMap = loginMapManager.findByPropertySingle("objid", objid);
				if(Validator.isNotNull(loginMap)){
					String status = loginMap.getStatus();
					if(status.equals("online")){
						 username = loginMap.getUsername();
						 SysUser user = sysUserManager.getSysUserByLoginname(username);
						 result.put("status", "success");
						 result.put("user", JSONObject.toJSONString(user));
					}else{
						result.put("status", "faile");
						result.put("result", "用户不在线");
					}
				}
			}
			return result.toString();
		}catch (Exception e) {
			logger.error("查询用户",e);
			result.put("status", "faile");
			result.put("result", "服务器内部错误");
			return result.toString();
		}
	}
	/**
	 * 根据用户名获取用户，不做任何逻辑处理，只取符合条件的用户信息
	 * @param loginName 用户的登录名
	 * @param ouserid 操作的用户ID
	 * @return 用户的JSON串
	 */
	public String getUserByLoginName(String loginName, Long ouserid) {
		JSONObject result = new JSONObject();
		try{
			if(Validator.isNull(loginName) || Validator.isNotNull(ouserid)){
				result.put("status", "faile");
				result.put("result", "查询条件为空");
			}else{
				SysUser user = sysUserManager.getSysUserByLoginname(loginName);
				if(Validator.isNull(user)){
					result.put("status", "faile");
					result.put("result", "用户名或密码错误");
				}else{
					result.put("status", "success");
					result.put("user", JSONObject.toJSONString(user));
				}
			}
			return result.toString();
		}catch (Exception e) {
			logger.error("查询用户",e);
			result.put("status", "faile");
			result.put("result", "服务器内部错误");
			return result.toString();
		}
	}
	/**
	 * 根据用户ID获取用户，不做任何逻辑处理，只取符合条件的用户信息
	 * @param loginName 用户的登录名
	 * @param ouserid 操作的用户ID
	 * @return 用户的JSON串
	 */
	public String getUserByLoginUserId(Long uid, Long ouserid) {
		JSONObject result = new JSONObject();
		try{
			if(Validator.isNull(uid) || Validator.isNotNull(ouserid)){
				result.put("status", "faile");
				result.put("result", "查询条件为空");
			}else{
				SysUser user = sysUserManager.get(uid);
				if(Validator.isNull(user)){
					result.put("status", "faile");
					result.put("result", "用户不存在");
				}else{
					result.put("status", "success");
					result.put("user", JSONObject.toJSONString(user));
				}
			}
			return result.toString();
		}catch (Exception e) {
			logger.error("查询用户",e);
			result.put("status", "faile");
			result.put("result", "服务器内部错误");
			return result.toString();
		}
	}
}
