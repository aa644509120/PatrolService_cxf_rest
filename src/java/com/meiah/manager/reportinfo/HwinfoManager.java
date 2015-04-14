/** * A级 */
package com.meiah.manager.reportinfo;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meiah.core.service.AbstractCrudManager;
import com.meiah.core.util.Validator;
import com.meiah.entity.reportinfo.Hwinfo;
import com.meiah.manager.sys.UploadFileManager;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class HwinfoManager extends AbstractCrudManager<Hwinfo>{
	private Logger logger=Logger.getLogger(HwinfoManager.class);
	@Resource(name="uploadFileManager")
	private UploadFileManager uploadFileManager;
  
	public void save(Hwinfo entity,String username , Long userid) {
		fillEntity(entity,  username , userid);
		super.save(entity);
	}
	/**
	 * @param entity
	 */
	private void fillEntity(Hwinfo entity,String username , Long userid) {
		//更新附件信息
		if (Validator.isNull(entity.getId())) {
			 entity.setSflag(-1L);	//送报单位级别，初始值全部设为-1，金松在后台再重新赋值
			 entity.setRflag(-1L);	//接收单位级别，初始值全部设为-1，金松在后台再重新赋值
			 entity.setSdanwei("-1");
			 if(Validator.isNotNull(entity.getStatus())&&1==entity.getStatus()){
				 entity.setIscommit(0);	//默认设为0，未提交
			 }
			 //特殊修改，数据库非空字段
			 entity.setLeibie("");
			 entity.setRdanwei("");
			 entity.setXingzhi("");
			 entity.setBianji("");
			 entity.setShenpi("");
			 entity.setShenhe("");
		}
		if(Validator.isNull(entity.getDizhi())) {
			entity.setDizhi("");
		}
		if(Validator.isNull(entity.getIp())) {
			entity.setIp("");
		}
		if (Validator.isNull(entity.getUserid())) {
			entity.setSusr(username);	//送报人
			entity.setUserid(userid);
		}
		if (Validator.isNull(entity.getCutImageResult())) {
			entity.setCutImageResult(0);
		}
		if (Validator.isNull(entity.getIpAnalyResult())) {
			entity.setIpAnalyResult(0);
		}
		entity.setStime(new Date());
	}
	
	public void saveByNewSession(Hwinfo entity,String username , Long userid) {
		fillEntity(entity,username , userid);
		super.saveByNewSession(entity);
	}
	
	
	/**
	 *  保存实体，同时更新相应附件信息
	 * @param entity
	 * @param attachIds
	 * @author "zhangshaofeng"  
	 * @time Sep 21, 2012 2:44:19 PM
	 */
	public void save(Hwinfo entity, String[] attachIds) {
		try {
			this.save(entity);
			if(Validator.isNotNull(attachIds)) {
				uploadFileManager.updateFileInfo(entity.getId(), 2,  attachIds,0);
			}
		} catch (Exception e) {
			logger.error("保存违法报送信息出错" + e);
		}
	}
	
	/**
	 * 批量更改截图状态
	 * @param ids
	 * @param status
	 * @author "zhangshaofeng"  
	 * @time Sep 23, 2012 3:46:25 PM
	 */
	public void batchCutImgStatus(String ids){
		 String hql = "update " + Hwinfo.class.getName() + " t "
				+ "set t.cutImgFlag=?"
				+ " where t.id in (" + ids + ") and (t.cutImgFlag is null or t.cutImgFlag=0)";
		int count = dao.createQuery(hql, 2).executeUpdate();
		logger.info("成功更新" + count + "条专项信息");
	}
           
	/**
	 * 进行url排重
	 * @param url
	 * @return
	 * @author liuhm
	 */
	public Boolean isExist(String url) {
		List list = this.dao.findByProperty("siteurls", url);
		if(Validator.isNotNull(list)) {
			return true;
		}else {
			return false;
		}
	}
	
}
