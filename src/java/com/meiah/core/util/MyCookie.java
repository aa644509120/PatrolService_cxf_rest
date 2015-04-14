/** * A级 */
package com.meiah.core.util;

import java.util.Date;

public class MyCookie {
	private Long aid;
	private String btype;
	private Long cookieid;
	private String cookiestr;
	private Date issueddate;
	public Long getAid() {
		return aid;
	}
	public void setAid(Long aid) {
		this.aid = aid;
	}
	public String getBtype() {
		return btype;
	}
	public void setBtype(String btype) {
		this.btype = btype;
	}
	public Long getCookieid() {
		return cookieid;
	}
	public void setCookieid(Long cookieid) {
		this.cookieid = cookieid;
	}
	public String getCookiestr() {
		return cookiestr;
	}
	public void setCookiestr(String cookiestr) {
		this.cookiestr = cookiestr;
	}
	public Date getIssueddate() {
		return issueddate;
	}
	public void setIssueddate(Date issueddate) {
		this.issueddate = issueddate;
	}
}
