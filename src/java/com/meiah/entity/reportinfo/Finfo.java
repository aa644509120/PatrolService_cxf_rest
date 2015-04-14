/** * A级 */
package com.meiah.entity.reportinfo;

import java.util.Date;
import java.util.List;

import com.meiah.annotation.Description;
import com.meiah.annotation.EntityDesc;

/**
 * Finfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@EntityDesc(name="四类信息",tableName="FINFO")
public class Finfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	@Description(name="标题",columnName="title")
	private String title;
	
	@Description(name="内容",columnName="content")
	private String content;
	
	@Description(name="地址",columnName="url")
	private String url;
	
	@Description(name="信息类型",columnName="type")
	private String type;
	
	@Description(name="发表时间",columnName="publishtime")
	private Date publishtime;
	
	@Description(name="附件ID",columnName="attachmentsid")
	private String attachmentsid;
	
	@Description(name="保存时间",columnName="savetime")
	private Date savetime;
	
	@Description(name="状态",columnName="status")
	private Integer status;
	
	@Description(name="上报用户",columnName="suser")
	private Long suser;
	
	@Description(name="是否打包",columnName="isCommit")
	private Integer iscommit;
	
	private String codeKey;
	
	
	private String codeType;
	
	private String susername;
	
	private Date committime;
	
	private List<String> urlList;//图片的URL地址,多线程去下载图片

	// Constructors

	/** default constructor */
	public Finfo() {
	}

	/** full constructor */
	public Finfo(String title, String content, String url, String type,
			Date publishtime, String attachmentsid, Date savetime,
			Integer status, Long suser, Integer iscommit,String codeKey,
			String codeType,String susername,Date committime) {
		this.title = title;
		this.content = content;
		this.url = url;
		this.type = type;
		this.publishtime = publishtime;
		this.attachmentsid = attachmentsid;
		this.savetime = savetime;
		this.status = status;
		this.suser = suser;
		this.iscommit = iscommit;
		this.codeKey = codeKey;
		this.codeType = codeType;
		this.susername = susername;
		this.committime = committime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getPublishtime() {
		return this.publishtime;
	}

	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}

	public String getAttachmentsid() {
		return this.attachmentsid;
	}

	public void setAttachmentsid(String attachmentsid) {
		this.attachmentsid = attachmentsid;
	}

	public Date getSavetime() {
		return this.savetime;
	}

	public void setSavetime(Date savetime) {
		this.savetime = savetime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getSuser() {
		return this.suser;
	}

	public void setSuser(Long suser) {
		this.suser = suser;
	}

	public Integer getIscommit() {
		return iscommit;
	}

	public void setIscommit(Integer iscommit) {
		this.iscommit = iscommit;
	}

	public String getCodeKey() {
		return codeKey;
	}

	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getSusername() {
		return susername;
	}

	public void setSusername(String susername) {
		this.susername = susername;
	}

	public Date getCommittime() {
		return committime;
	}

	public void setCommittime(Date committime) {
		this.committime = committime;
	}

	public List<String> getUrlList() {
		return urlList;
	}

	public void setUrlList(List<String> urlList) {
		this.urlList = urlList;
	}
	
}
