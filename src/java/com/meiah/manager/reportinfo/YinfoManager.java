/** * A级 */
package com.meiah.manager.reportinfo;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meiah.core.service.AbstractCrudManager;
import com.meiah.core.util.Validator;
import com.meiah.entity.reportinfo.Yinfo;

@Service
@Transactional
public class YinfoManager extends AbstractCrudManager<Yinfo>{
	
	private Logger logger=Logger.getLogger(YinfoManager.class);


	public void save(Yinfo entity,String username,Long userid){
		if(Validator.isNull(entity.getSavetime())){
			entity.setSavetime(new Date());
		}
		
		if(Validator.isNull(entity.getIscommit())){
			entity.setIscommit(1);
		}
		if(Validator.isNull(entity.getSuser())){
			entity.setSuser(userid);
		}
		if(Validator.isNull(entity.getSusername())){
			entity.setSusername(username);
		}
		super.save(entity);
	}
}
