/** * A级 */
package com.meiah.manager.sys;

import java.io.File;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meiah.core.service.AbstractCrudManager;
import com.meiah.core.util.Validator;
import com.meiah.entity.sys.UploadFile;

@Service
@Transactional
public class UploadFileManager extends AbstractCrudManager<UploadFile>{
	
	private Logger logger=Logger.getLogger(UploadFileManager.class);

	/**
	 * @author huanglb
	 * @param relativePath
	 * @param filename
	 * 			fileName[0]是文件夹日期+文件名：2012_09_19_09_37_00_39503457.txt
	 * 			fileName[1]是真实文件名：test.txt
	 * 			fileName[2]是文件大小
	 * @return
	 */
	public Long saveUploadFileName(String relativePath, String[] filename,String extend1) {
		try {
			UploadFile obj = new UploadFile();
			obj.setFilename(filename[1]);
			String[] pathFolder = filename[0].split("_");
			String fullPath = "";
			for (int i=0; i<pathFolder.length-3; i++) {
				//文件夹只精确到小时级别
				fullPath += pathFolder[i] + File.separator;
			}
			obj.setFilepath(relativePath + fullPath + filename[0]);
			obj.setFilesize(Long.parseLong(filename[2]));
			if(Validator.isNotNull(extend1)){
				obj.setExtend1(Integer.parseInt(extend1));
			}else{
				obj.setExtend1(0);
			}
			this.save(obj);
			return obj.getId();
		} catch (Exception e) {
			logger.error("保存文件信息出错" + e);
		}
		return 0L;
	}
	/**
	 * 更新附件信息
	 * @param infoid	关联的信息表id
	 * @param type		关联的附件类型
	 * @param extend1	扩展字段
	 * @param attachIds	要更新的列表数组
	 * @throws Exception
	 */
	public void updateFileInfo(Long infoid, Integer type, String[] attachIds,Integer extend1) throws Exception {
		try {
			if(Validator.isNotNull(attachIds)){
			String ids = "";
			for (String id : attachIds) {
				if(Validator.isNotNull(id)){
					if(Validator.isNotNull(id))ids += id + ",";
				}
			}
			if(Validator.isNotNull(ids)) {
				ids = ids.substring(0, ids.length()-1);
			}
			
				final String hql = "update " + UploadFile.class.getName() + " t "
				+ "set t.infoid=?, t.type=?"+(Validator.isNotNull(extend1)?",t.extend1="+extend1:"")
				+ " where t.id in (" + ids + ")";
				if(Validator.isNotNull(ids)){
					int count = dao.createQuery(hql, infoid, type).executeUpdate();
				    logger.info("成功更新" + count + "条附件信息");
				}
			}
		} catch (Exception e) {
			logger.error("更新附件信息失败" + e);
		}
	}
}
