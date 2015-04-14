/** * A级 */
package com.meiah.manager.pre;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meiah.core.service.AbstractCrudManager;
import com.meiah.core.util.Validator;
import com.meiah.entity.pre.PreReport;
import com.meiah.entity.sys.UploadFile;
@Service
@Transactional
public class PreReportManager extends AbstractCrudManager<PreReport>{

	/**
	 * 同用户组下是否存在URL，flag是否开启子用户判断
	 * @param code
	 * @return
	 */
	public List<PreReport> hasRepeatUrl(String code,String url , boolean flag){
		List<PreReport> preReportList = null;
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("url", url);
		if(flag){
			map.put("flag", flag);
		}
		preReportList = this.dao.findByNamedQuery("PreReport.hasRepeatUrl", map);
		return preReportList;
	}
	
	public void updateAttfileFlag(Long infoid) throws Exception {
		try {
			String hql = "UPDATE pre_report SET attfileFlag=1 WHERE id IN (SELECT infoid FROM upload_file WHERE infoid = ? AND extend1=0)";
			int count = dao.createSQLQuery(hql, infoid).executeUpdate();
		    logger.info("成功更新" + count + "条附件标志信息");
		} catch (Exception e) {
			logger.error("更新附件标志信息失败" + e);
		}
	}
	public void updateCutImgFlag(Long infoid) throws Exception {
		try {
			String hql = "UPDATE pre_report SET cutImgFlag=1 WHERE id IN (SELECT infoid FROM upload_file WHERE infoid = ? AND extend1=1)";
			int count = dao.createSQLQuery(hql, infoid).executeUpdate();
		    logger.info("成功更新" + count + "条截图标志信息");
		} catch (Exception e) {
			logger.error("更新截图标志信息失败" + e);
		}
	}
}
