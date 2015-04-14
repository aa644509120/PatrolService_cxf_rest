/** * A级 */
package com.meiah.manager.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.meiah.core.service.AbstractCrudManager;
import com.meiah.entity.sys.SysUser;
import com.meiah.util.Validator;

@Service
@Transactional
public class SysUserManager extends AbstractCrudManager<SysUser> {
	private Logger logger = Logger.getLogger(SysUserManager.class);

	/**
	 * 根据登录名称查找用户
	 * @param loginname
	 * @return
	 */
	public SysUser getSysUserByLoginname(String loginname) {
		SysUser sysUser = null;
		try {
			sysUser = dao.findUniqueByProperty("loginname", loginname);
		} catch (Exception e) {
			logger.error("根据登录名称查找用户出错", e);
		}
		return sysUser;
	}
	
	/**
	 * 根据登录名称查找用户
	 * @param loginname
	 * @return
	 */
	public SysUser getSysUserByWeChat(String wechat) {
		SysUser sysUser = null;
		try {
			sysUser = dao.findUniqueByProperty("wechat", wechat);
		} catch (Exception e) {
			logger.error("根据微信查找用户出错", e);
		}
		return sysUser;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<SysUser> getChildUsers(String code){
		List<SysUser> userList = new ArrayList<SysUser>();
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("code", code);
		userList = this.dao.findByNamedQuery("SysUser.getUserByCode", map);
//		if(Validator.isNotNull(userList)){
//			for (SysUser sysUser : userList) {
//				evict(sysUser);
//				sysUser.setPassword("");
//			}
//		}
		return userList;
	}
	
	public List<SysUser> getName(String name,String flag){
		List<SysUser> userList = new ArrayList<SysUser>();
		Map<String, Object> map = new HashMap<String , Object>();
		if(Validator.isNotNull(flag)){
			map.put("flag", flag);
		}else{
			map.put("nflag", "nflag");
		}
		map.put("name", name);
		userList = this.dao.findByNamedQuery("SysUser.getUserByName", map);
		return userList;
	}
	
	
	/**
	 * 根据组织编码获取用户列表
	 * @param code
	 * @return
	 */
	public List<SysUser> getUserByCode(String code){
		List<SysUser> userList = null;
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("code", code);
		userList = this.dao.findByNamedQuery("SysUser.getUserByCode", map);
		return userList;
	}
}
