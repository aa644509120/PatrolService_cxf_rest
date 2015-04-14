/** * A级 */
package com.meiah.core.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.meiah.core.util.Config;
import com.meiah.core.util.FileUtil;
import com.meiah.core.util.SpringContextUtil;
import com.meiah.entity.sys.UploadFile;
import com.meiah.manager.sys.UploadFileManager;

/**
 * 下载已上传的附件，存在于upload_file里面的信息
 *
 */
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DownloadServlet.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String fileId = request.getParameter("fileId");
			if (StringUtils.isNotEmpty(fileId)) {
				UploadFileManager uploadFileManager = (UploadFileManager) SpringContextUtil.getBean("uploadFileManager");
				UploadFile fileInfo = uploadFileManager.load(Long.parseLong(fileId));
				final String basePath = Config.imgFilePath; // 项目路径
				FileUtil.download(request, response, basePath + fileInfo.getFilepath(),fileInfo.getFilename());
			}
		} catch (Exception e) {
			logger.error("获取文件名失败", e);
		}
	}
}
