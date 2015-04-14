/** * A级 */
package com.meiah.entity.sys;

import java.sql.Timestamp;

/**
 * SysNotice entity. @author MyEclipse Persistence Tools
 */

public class SysNotice implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String content;
	private Long opUserid;
	private String opTime;
	private String opName;
	private String toUserids;//通知接收对象，all接收用户为全部，其他为用户id串
  
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getOpUserid() {
		return this.opUserid;
	}

	public void setOpUserid(Long opUserid) {
		this.opUserid = opUserid;
	}

 
	public String getOpName() {
		return opName;
	}

	public void setOpName(String opName) {
		this.opName = opName;
	}

	public String getOpTime() {
		return opTime;
	}

	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}

	public String getToUserids() {
		return toUserids;
	}

	public void setToUserids(String toUserids) {
		this.toUserids = toUserids;
	}

}
