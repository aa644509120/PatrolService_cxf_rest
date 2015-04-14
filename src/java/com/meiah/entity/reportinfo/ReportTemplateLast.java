/** * A级 */
package com.meiah.entity.reportinfo;

import com.meiah.annotation.Description;


/**用户最后一次模板选择
 * ReportTemplateLast entity. @author MyEclipse Persistence Tools
 */

public class ReportTemplateLast implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 9213849928941978750L;
	private Long id;
	@Description(name="用户ID")
	private Long userid;
	@Description(name="最后一次选择模板ID")
	private Long templateId;

	// Constructors

	/** default constructor */
	public ReportTemplateLast() {
	}

	/** full constructor */
	public ReportTemplateLast(Long userid, Long templateId) {
		this.userid = userid;
		this.templateId = templateId;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getTemplateId() {
		return this.templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

}
