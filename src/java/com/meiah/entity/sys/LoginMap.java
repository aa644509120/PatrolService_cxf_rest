/** * A级 */
package com.meiah.entity.sys;

import java.sql.Timestamp;

/**
 * LoginMap entity. @author MyEclipse Persistence Tools
 */

public class LoginMap implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String objid;
	private Long userid;
	private String username;
	private String status;
	private String ctime;
	private String mtime;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObjid() {
		return this.objid;
	}

	public void setObjid(String objid) {
		this.objid = objid;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getMtime() {
		return mtime;
	}

	public void setMtime(String mtime) {
		this.mtime = mtime;
	}
}
