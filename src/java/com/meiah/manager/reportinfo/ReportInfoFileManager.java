/** * A级 */
package com.meiah.manager.reportinfo;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meiah.core.service.AbstractCrudManager;
import com.meiah.core.util.Config;
import com.meiah.core.util.CookieUtil;
import com.meiah.core.util.MyCookie;
import com.meiah.core.util.SinaUtil;
import com.meiah.core.util.Validator;
import com.meiah.entity.reportinfo.Hwinfo;

@Service
@Transactional
public class ReportInfoFileManager extends AbstractCrudManager<Hwinfo> {
	private Logger logger = Logger.getLogger(ReportInfoFileManager.class);
	public void getCookie(){
		logger.info("定时获取cookie开始");
		//获取新浪和QQ微博的cookie
		Config.qqCookie = CookieUtil.getCookie("qq");
		Config.sinaCookie = CookieUtil.getCookie("sina");
		logger.info("qqCookie为:"+(Config.qqCookie == null ?"空":Config.qqCookie.getCookiestr()));
		logger.info("sinaCookie为:"+(Config.sinaCookie == null ?"空":Config.sinaCookie.getCookiestr()));
		logger.info("定时获取cookie结束");
	}
	public void validatorSinaCookie(){
		logger.info("定时验证cookie开始");
		//sinaCookie
		if(Validator.isNotNull(Config.sinaCookie)){
			if(!SinaUtil.validatorCookie()){
				logger.info("cookie失效,上报该Cookie");
				MyCookie newCookie = CookieUtil.reportCookie(Config.sinaCookie.getBtype(), Config.sinaCookie.getAid(), Config.sinaCookie.getCookieid());
				if(Validator.isNotNull(newCookie)){
					Config.sinaCookie = newCookie;
				}
			}
		}
		logger.info("定时验证cookie结束");
	}
}
