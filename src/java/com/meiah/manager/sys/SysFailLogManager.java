/** * A级 */
package com.meiah.manager.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meiah.core.service.AbstractCrudManager;
import com.meiah.core.util.CXFClientUtil;
import com.meiah.core.util.Validator;
import com.meiah.entity.sys.SysSyncFail;
import com.meiah.manager.task.PolicyManager;
import com.meiah.services.PatrolService;

@Service
@Transactional
public class SysFailLogManager extends AbstractCrudManager<SysSyncFail> {
	@Resource
	private PolicyManager policyManager;
	@SuppressWarnings("unchecked")
	public void sycnKey(){
		Map<String,Object> map = new HashMap<String, Object>(); 
		List<SysSyncFail> list = this.dao.findByNamedQuery("SysSyncFail.getALL", map);
		if(Validator.isNotNull(list)){
			for(int i = 0 ; i < list.size() ; i++){
				SysSyncFail syncFail = list.get(i);
				String json = syncFail.getKeysJSON();
				String system =  syncFail.getSystem();
				Integer opUserid = syncFail.getUserid();
				String comeFrom = syncFail.getComeFrom();
				if(system.equals("local")){
					String result = policyManager.parseJSON(json, opUserid*1L,"1","");
					if(result.equals("true")){
						this.delete(syncFail);
					}
				}else{
					final JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();  
					factory.setAddress(system);  
					factory.setServiceClass(PatrolService.class);  
					PatrolService patrolService = (PatrolService) factory.create();  
					CXFClientUtil.setTimeout(patrolService);
					try {
						String success = patrolService.syncKeys(json, opUserid*1l, comeFrom);//传数据
						if(success.equals("success")){
							this.delete(syncFail);
						}
					} catch (Exception e){
						e.printStackTrace();
					}
				}
			}
		}
	}
	
}
