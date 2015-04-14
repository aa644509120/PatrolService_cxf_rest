/** * A级 */
package com.meiah.manager.task;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.Flags.Flag;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meiah.core.service.AbstractCrudManager;
import com.meiah.core.util.DateUtil;
import com.meiah.core.util.Validator;
import com.meiah.entity.sys.SysSyncFail;
import com.meiah.entity.sys.SysUser;
import com.meiah.entity.task.Key;
import com.meiah.entity.task.KeyJson;
import com.meiah.entity.task.Policy;
import com.meiah.manager.sys.SysFailLogManager;
import com.meiah.manager.sys.SysUserManager;
@Service
@Transactional
public class PolicyManager extends AbstractCrudManager<Policy>{
	
	private static Logger logger = Logger.getLogger(PolicyManager.class);
	
	@Resource
	private SysUserManager sysUserManager;
	@Resource
	private SysFailLogManager sysFailLogManager;
	
//	 * 	{
//		 * 		type:"add",        ---其中type为操作类型，add为新增、del为删除、edit为修改
//		 * 		total:18,		   ---其中total为本次操作受影响的关键词总数
//		 * 		userid:["1","2","3"],	   ---userid为共享对象的用户id或者其他的用户唯一标识，不限制类型
//		 * 		keys:[			   		   ---keys为操作的关键词数字
//		 * 				{"key":"暴力","type":"涉及警察","uuid":"121xc234"}, --key为关键词 ，type为关键词类别 ，uuid为关键词的唯一标识
//		 * 				{"key":"反恐","type":"涉及警察","uuid":"121xc235"}
//		 * 			 ]
//		 *  }
	
	//解析JSON，比入库
	public String parseJSON(String json , Long opUserid,String flag,String comeFrom){
		String result = "true";
		logger.info("解析JSON,JSON为:["+json+"],用户为opUserid:["+opUserid+"]");
		try{
			KeyJson keyJson =  new Gson().fromJson(json, new TypeToken<KeyJson>() {}.getType());
			String type = keyJson.getType();
			List<String> userid = keyJson.getUserid();
			List<Key> keys = keyJson.getKeys();
			if(type.equals("add") || type.equals("edit")){//新增
				for(int i = 0 ; i < keys.size();i++){
					Key key = keys.get(i);
					String uuid = key.getUuid();
					for(int n = 0 ; n < userid.size() ; n++){
						Long oUserid = Long.parseLong(userid.get(n));
						List<Criterion> list = new ArrayList<Criterion>();
						list.add(Restrictions.eq("userid",oUserid.intValue()));
						list.add(Restrictions.eq("uuid", uuid));
						Criterion[] criterions = new Criterion[list.size()];
						list.toArray(criterions);
						List<Policy> nlist = this.getAll(criterions);
						
						//原系统已经存在就不做任何操作
						List<Policy> plist = this.findByCriteria(Restrictions.eq("userid",oUserid.intValue()),Restrictions.eq("name",key.getKey().trim()),Restrictions.eq("ptype",key.getType().trim()),Restrictions.ne("uuid", uuid));
						if(Validator.isNull(plist)){
							if(Validator.isNotNull(nlist)){
								Policy policy = nlist.get(0);
								if(Validator.isNotNull(key.getType())){
									policy.setPtype(key.getType());
								}
								if(Validator.isNotNull(key.getKey())){
									policy.setName(key.getKey());
								}
								this.dao.save(policy);
							}else{//共享
								Policy policy = new Policy();
								if(Validator.isNotNull(key.getType())){
									policy.setPtype(key.getType());
								}else{
									policy.setPtype("未分类");
								}
								if(Validator.isNotNull(key.getKey())){
									policy.setName(key.getKey());
								}else{
									continue;
								}
								policy.setUserid(oUserid.intValue());
								policy.setAddtime(new Date());
								policy.setUuid(uuid);
								policy.setShare(1);
								policy.setEshare(1);//共享的关键词 不能再次共享给别人
								policy.setShareTime(new Date());
								SysUser user = sysUserManager.load(opUserid);
								if(Validator.isNotNull(user)){
									policy.setShareContent("由"+user.getName()+"共享");
								}
								this.save(policy);
							}
						}
					}
					
				}
			}else if(type.equals("del")){//删除
				for(int i = 0 ; i < keys.size();i++){
					Key key = keys.get(i);
					String uuid = key.getUuid();
					for(int n = 0 ; n < userid.size() ; n++){
						Long oUserid = Long.parseLong(userid.get(n));
						this.execute("delete from Policy where userid="+oUserid+" and uuid="+uuid);
					}
				}
			}
		}catch (Exception e) {
			logger.info("解析JSON,JSON为:["+json+"],用户为opUserid:["+opUserid+"]失败");
			if(flag.equals("1")){
				SysSyncFail ssf=new SysSyncFail();
				ssf.setKeysJSON(json);
				ssf.setSystem("local");
				ssf.setUserid(opUserid.intValue());
				ssf.setComeFrom(comeFrom);
				try {
					ssf.setFailtime(DateUtil.getFormatDate());
				} catch (ParseException e1) {
					logger.error("同步失败信息存储出错");
				}
				sysFailLogManager.save(ssf);
				result = "false";
			}
		}
		
		return result;
	}
}
