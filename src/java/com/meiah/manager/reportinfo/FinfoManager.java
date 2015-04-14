/** * A级 */
package com.meiah.manager.reportinfo;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meiah.core.service.AbstractCrudManager;
import com.meiah.core.util.Validator;
import com.meiah.entity.reportinfo.Finfo;

@Service
@Transactional
public class FinfoManager extends AbstractCrudManager<Finfo>{
	
	private Logger logger=Logger.getLogger(FinfoManager.class);

	public void save(Finfo entity,String username,Long userid){
		if(Validator.isNull(entity.getId())){

		}
		if(Validator.isNull(entity.getSavetime())){
			entity.setSavetime(new Date());
		}
		if(Validator.isNull(entity.getIscommit())){
			entity.setIscommit(0);
		}
		
		if (Validator.isNull(entity.getSusername())) {
			entity.setSusername(username);
		}
		if (Validator.isNull(entity.getSuser())) {
			entity.setSuser(userid);
		}
		super.save(entity);
	}
}
