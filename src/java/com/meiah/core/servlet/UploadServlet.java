/** * A级 */
package com.meiah.core.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.meiah.core.util.Config;
import com.meiah.core.util.FileUtil;
import com.meiah.core.util.SpringContextUtil;
import com.meiah.core.util.Validator;
import com.meiah.manager.sys.UploadFileManager;

/**
 * uploadify上传附件servlet处理
 * 通用类，以后附件上传可以用这种存在文件与具体业务逻辑的关联信息
 *
 * @since 2012-9-19上午10:09:32
 */
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(UploadServlet.class);

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 保存附件路径
		final String basePath = Config.imgFilePath; // 项目路径
		String relativePath = "upload";
		
		String type = request.getParameter("type");
		if(Validator.isNotNull(type)){
			relativePath +="\\"+ type + File.separator;
		}else{
			relativePath = "upload" + File.separator; // 相对路径
		}
		String savePath = basePath + relativePath; // 附件的保存路径
		String[] fileName = FileUtil.upload(request, savePath);
		
		String extend1 =  request.getParameter("extend1");
		if ((fileName != null) && (fileName.length == 3)) {
			try {
				UploadFileManager uploadFileManager = (UploadFileManager) SpringContextUtil.getBean("uploadFileManager");
				Long uploadFileId = uploadFileManager.saveUploadFileName(relativePath, fileName,extend1);
				//返回附件记录的ID是为了过后关联相应的业务逻辑表
				response.getWriter().print(uploadFileId);
			} catch (Exception e) {
				logger.error("附件上传失败", e);
			}
		}
	}

}
