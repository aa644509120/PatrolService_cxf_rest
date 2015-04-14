/** * A级 */
package com.meiah.entity.task;

import java.sql.Timestamp;

/**
 * WbPatrolLog entity. @author MyEclipse Persistence Tools
 */

public class WbPatrolLog implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id; 
	private String btype; //类别
	private String userid; //微博用户
	private String nickname;//微博昵称,不能为空，没有昵称就用微博号
	private String keyword;//关键词
	private String time;//巡查时间，格式用 yyyy-MM-dd HH:mm:ss。不为空
	private String url;//微博URL，可为空
	private String addtime;//入库时间，不填
	private Long opUserid;//系统用户

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

 

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getBtype() {
		return btype;
	}

	public void setBtype(String btype) {
		this.btype = btype;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Long getOpUserid() {
		return opUserid;
	}

	public void setOpUserid(Long opUserid) {
		this.opUserid = opUserid;
	}
}
