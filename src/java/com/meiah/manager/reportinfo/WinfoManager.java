/** * A级 */
package com.meiah.manager.reportinfo;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meiah.core.service.AbstractCrudManager;
import com.meiah.entity.reportinfo.Winfo;
import com.meiah.manager.sys.UploadFileManager;
import com.meiah.util.Validator;

@Service
@Transactional
public class WinfoManager extends AbstractCrudManager<Winfo>{
	
	private Logger logger=Logger.getLogger(WinfoManager.class);
	

	@Resource
	private UploadFileManager uploadFileManager;

	public void save(Winfo entity,String username,Long userid) {
		//更新附件信息
		if (Validator.isNull(entity.getId())) {
			 entity.setSflag(-1L);	//送报单位级别，初始值全部设为-1，金松在后台再重新赋值
			 entity.setRflag(-1L);	//接收单位级别，初始值全部设为-1，金松在后台再重新赋值
			 entity.setSdanwei("-1");
			 if(Validator.isNotNull(entity.getStatus())&&1==entity.getStatus().intValue()){
				 entity.setIscommit(0);	//默认设为0，未提交
			 }
		}
		if (Validator.isNull(entity.getUserid())) {
			entity.setSusr(username);	//送报人
			entity.setUserid(userid);
		}
		entity.setStime(new Date());
		//如果只有一个则为1,如果为两个位1,2, 必须带逗号
		if (Validator.isNotNull(entity.getXinxileixing())) {
			String xxlx=entity.getXinxileixing();
			 if (xxlx.split(",").length>1&&!xxlx.endsWith(",")) {
				entity.setXinxileixing(xxlx+",");
			}
		}
		super.save(entity);
	}
	/**
	 *  保存实体，同时更新相应附件信息
	 * @param entity
	 * @param attachIds
	 * @author "zhangshaofeng"  
	 * @time Sep 21, 2012 2:44:19 PM
	 */
	public void save(Winfo entity, String[] attachIds) {
		try {
			this.save(entity);
			if(Validator.isNotNull(attachIds)) {
				uploadFileManager.updateFileInfo(entity.getId(), 1,  attachIds,0);
			}
		} catch (Exception e) {
			logger.error("保存互联网报送信息出错" + e);
		}
	}
}
