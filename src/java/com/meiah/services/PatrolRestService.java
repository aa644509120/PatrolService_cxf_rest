/** * A级 */
package com.meiah.services;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.meiah.entity.favorite.Favorite;
import com.meiah.entity.sys.SysLog;
import com.meiah.entity.task.TaskNotice;
@WebService(targetNamespace="http://tempuri.org/")
public interface PatrolRestService {
	public String testServer(@WebParam(name="content" )String content);
	
	/**
	 * 添加任务提醒,userid、tuserid、content都不能为空
	 * @param taskNotice
	 * @return json字符串
	 */
	public String addTaskNotice(TaskNotice taskNotice);
	/**
	 * 根据用户ID获取用户收藏的标签
	 * @param userid 用户标签
	 * @param ouserid 哪个用户查询(操作用户ID)
	 * @return 标签的JSON串,以及操作状态
	 */
	public String favoriteLabel(Long userid,Long ouserid);
	
	/**
	 * 添加收藏
	 * @param favorite 要添加的收藏
	 * @param ouserid 收藏的用户
	 * @return 是否收藏成功
	 */
	public String addFavorite(Favorite favorite , Long ouserid);
	
	/**
	 * 系统日志保存
	 * @param sysLog 系统日志
	 * @param ouserid 日志操作用户
	 * @return 是否添加成功
	 */
	public String addLog(SysLog sysLog,Long ouserid);
	
	/**
	 * 根据用户名获取用户，不做任何逻辑处理，只取符合条件的用户信息
	 * @param loginName 用户的登录名
	 * @param ouserid 操作的用户ID
	 * @return 用户的JSON串
	 */
	public String getUserByLoginName(String loginName,Long ouserid);
	/**
	 * 根据用户ID获取用户，不做任何逻辑处理，只取符合条件的用户信息
	 * @param loginName 用户的登录名
	 * @param ouserid 操作的用户ID
	 * @return 用户的JSON串
	 */
	public String getUserByLoginUserId(Long uid,Long ouserid);
	/**
	 * 根据浏览器序列号获取在线用户，不做任何逻辑处理，只取符合条件的用户信息
	 * @param loginName 用户的登录名
	 * @param ouserid 操作的用户ID
	 * @return 用户的JSON串
	 */
	public String getLoginMapOnLine(String objid,Long ouserid);
}
