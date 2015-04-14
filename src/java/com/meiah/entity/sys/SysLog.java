/** * A级 */
package com.meiah.entity.sys;

import java.util.Date;

/**
 * SysLog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysLog implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long userid;
	private String loginname;
	private String url;
	private String params;
	private String content;
	private String remark;
	private Date time;
	private String ip;
	private String comeFrom;
	private String name;

	/** default constructor */
	public SysLog() {
	}

	/** minimal constructor */
	public SysLog(Long userid, String content, String remark, Date time,
			String ip, String comeFrom) {
		this.userid = userid;
		this.content = content;
		this.remark = remark;
		this.time = time;
		this.ip = ip;
		this.comeFrom = comeFrom;
	}

	/** full constructor */
	public SysLog(Long userid, String url, String params, String content,
			String remark, Date time, String ip, String comeFrom) {
		this.userid = userid;
		this.url = url;
		this.params = params;
		this.content = content;
		this.remark = remark;
		this.time = time;
		this.ip = ip;
		this.comeFrom = comeFrom;
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParams() {
		return this.params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getComeFrom() {
		return this.comeFrom;
	}

	public void setComeFrom(String comeFrom) {
		this.comeFrom = comeFrom;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
