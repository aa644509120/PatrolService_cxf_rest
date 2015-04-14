/** * A级 */
package com.meiah.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.mail.internet.MimeUtility;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 上传下传附件工具类
 *
 * @author huanglb
 * @since 2012-9-20上午10:26:40
 */
public class FileUtil {

	public FileUtil() {
	}

	/**
	 * @param request
	 * @param savePath	保存附件的路径
	 * @return
	 */
	public static String[] upload(HttpServletRequest request, String savePath) {
		try {
			DiskFileItemFactory fac = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(fac);
			upload.setHeaderEncoding("utf-8");
			String name = "";
			String realName = "";
			String extName = "";
			String fileSize = "";
			List fileList = upload.parseRequest(request);
			Iterator it = fileList.iterator();
			while (it.hasNext()) {
				FileItem item = (FileItem) it.next();
				if (item.isFormField())
					continue; /* Loop/switch isn't completed */
				realName = item.getName();
				name = realName;
				fileSize = String.valueOf(item.getSize());
				if (name == null || name.trim().equals(""))
					continue; /* Loop/switch isn't completed */
				if (name.lastIndexOf(".") >= 0)
					extName = name.substring(name.lastIndexOf("."));
				File file = null;
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/HH/");
				savePath = (new StringBuilder(String.valueOf(savePath))).append(sdf.format(date)).toString();
				(new File(savePath)).mkdirs();
				sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_");
				String fname = sdf.format(date);
				do {
					Random r = new Random();
					name = (new StringBuilder(String.valueOf(fname))).append(String.valueOf(r.nextInt(99999) * 999)).toString();
					file = new File((new StringBuilder(String.valueOf(savePath))).append(name).append(extName).toString());
				} while (file.exists());
				File saveFile = new File((new StringBuilder(String.valueOf(savePath))).append(name).append(extName).toString());
				item.write(saveFile);
				return (new String[] { (new StringBuilder(String.valueOf(name))).append(extName).toString(), realName, fileSize });
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

	/**
	 * 
	 * @param request
	 * @param response
	 * @param srcFilePath
	 *            源文件路径
	 * @param realFileName
	 *            真实文件名
	 */
	public static void download(HttpServletRequest request, HttpServletResponse response, String srcFilePath, 
			String realFileName) {
		FileInputStream in = null;
		ServletOutputStream out = null;
		try {
			String agent = request.getHeader("USER-AGENT");
			if (agent != null && -1 != agent.indexOf("MSIE")) {
				String codedfilename = URLEncoder.encode(realFileName, "UTF-8");
				response.setContentType("application/x-download");
				response.setHeader("Content-Disposition", (new StringBuilder("attachment;filename=")).append(codedfilename).toString());
			} else if (agent != null && -1 != agent.indexOf("Mozilla")) {
				String codedfilename = MimeUtility.encodeText(realFileName, "UTF-8", "B");
				response.setContentType("application/x-download");
				response.setHeader("Content-Disposition", (new StringBuilder("attachment;filename=")).append(codedfilename).toString());
			} else {
				response.setContentType("application/x-download");
				response.setHeader("Content-Disposition", (new StringBuilder("attachment;filename=")).append(realFileName).toString());
			}
			in = new FileInputStream(srcFilePath);
			out = response.getOutputStream();
			byte b[] = new byte[8192];
			for (int len = 0; (len = in.read(b)) > 0;)
				out.write(b,0,len); 

			out.flush();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			in.close();
			out.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
}
