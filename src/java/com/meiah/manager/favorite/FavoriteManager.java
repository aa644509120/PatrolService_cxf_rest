/** * A级 */
package com.meiah.manager.favorite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.meiah.core.service.AbstractCrudManager;
import com.meiah.core.util.ImgDownload;
import com.meiah.core.util.StringUtil;
import com.meiah.entity.DataRecordOfString;
import com.meiah.entity.favorite.Favorite;
import com.meiah.entity.reportinfo.Finfo;
import com.meiah.entity.reportinfo.Hwinfo;
import com.meiah.entity.reportinfo.Winfo;
import com.meiah.entity.reportinfo.Yinfo;
import com.meiah.entity.sys.UploadFile;
import com.meiah.manager.reportinfo.FinfoManager;
import com.meiah.manager.reportinfo.HwinfoManager;
import com.meiah.manager.reportinfo.WinfoManager;
import com.meiah.manager.reportinfo.YinfoManager;
import com.meiah.manager.sys.UploadFileManager;
import com.meiah.util.Validator;

@Service
@Transactional
public class FavoriteManager extends AbstractCrudManager<Favorite> {
	@Resource
	private UploadFileManager uploadFileManager;
	@Resource
	private HwinfoManager hwinfoManager;
	@Resource
	private WinfoManager winfoManager;
	@Resource
	private YinfoManager yinfoManager;
	@Resource
	private FinfoManager finfoManager;
	
	
	
	@SuppressWarnings("unchecked")
	public JSONObject getLabelByUserid(Long userid){
		JSONObject lableJSON = new JSONObject();
		Map<String,Object> map  = new HashMap<String, Object>();
		map.put("userid", userid);
		List<Object[]> list = this.dao.findByNamedQuery("Favorite.getLabelByUserid", map);
		List<String> resultList = new ArrayList<String>();
		for(Object[] obs : list){
			String lable = (String) obs[0];
			resultList.add(lable);
		}
		lableJSON.put("lable",resultList.toArray());
		return lableJSON;
	}
	@SuppressWarnings("unchecked")
	public DataRecordOfString getLabelByUseridData(Long userid){
		DataRecordOfString dataRecordOfString = new DataRecordOfString();
		Map<String,Object> map  = new HashMap<String, Object>();
		map.put("userid", userid);
		List<Object[]> list = this.dao.findByNamedQuery("Favorite.getLabelByUserid", map);
		List<String> resultList = new ArrayList<String>();
		for(Object obs : list){
			String lable = (String) obs;
			resultList.add(lable);
		}
		dataRecordOfString.setI_total(resultList.size());
		dataRecordOfString.setM_item(resultList);
		return dataRecordOfString;
	}
	/**
	 * 收藏在附件表中的type为555
	 * @param urlList 文件的地址
	 * @param fid 收藏的ID
	 */
	public void urlFileDownload(List<String> urlList,Long fid,int type){
		for (int i = 0; i < urlList.size(); i++) {
			String url = urlList.get(i);
			UploadFile uploadFile = ImgDownload.makeImg(url);
			if(Validator.isNotNull(uploadFile)){
				uploadFile.setExtend1(4);
				uploadFile.setType(type);
				uploadFile.setInfoid(fid);
				uploadFileManager.save(uploadFile);
				if(type == 1){
					Winfo winfo = winfoManager.load(fid);
					if(Validator.isNotNull(winfo)){
						winfo.setAttfileFlag(1);
						winfoManager.save(winfo);
					}
				}else if(type == 2){//专项信息
					Hwinfo hwinfo = hwinfoManager.load(fid);
					if(Validator.isNotNull(hwinfo)){
						hwinfo.setAttfileFlag(1);
						hwinfoManager.save(hwinfo);
					}
				}else if(type ==444){
					Finfo finfo = finfoManager.load(fid);
					if(Validator.isNotNull(finfo)){
						String attachmentsid = finfo.getAttachmentsid();
						finfo.setAttachmentsid(StringUtil.getNormalString(attachmentsid+","+uploadFile.getId())); 
						finfoManager.save(finfo);
					}
				}else if(type == 7){
					Yinfo yinfo = yinfoManager.load(fid);
					if(Validator.isNotNull(yinfo)){
						String attachmentsid = yinfo.getAttachmentsid();
						yinfo.setAttachmentsid(StringUtil.getNormalString(attachmentsid+","+uploadFile.getId()));
						yinfoManager.save(yinfo);
					}
				}
			}
		}
	}
}
