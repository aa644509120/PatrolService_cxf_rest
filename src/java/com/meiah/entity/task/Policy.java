/** * A级 */
package com.meiah.entity.task;

import java.util.Date;

import com.meiah.annotation.Description;
import com.meiah.annotation.EntityDesc;

/**
 * Policy entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
@EntityDesc(name="关键词",tableName="policy")
public class Policy implements java.io.Serializable {

	// Fields

	private Long id;
	@Description(name="关键词",columnName="name")
	private String name;
	
	@Description(name="类别",columnName="ptype")
	private String ptype;
	
	private Integer status;
	
	@Description(name="添加时间",columnName="addtime")
	private Date addtime;
	
	private Integer share;//是否共享的标志，1共享 其他非共享
	private Integer eshare;//是否已经共享的标志，1为已经共享过
	private Date shareTime;//共享时间
	private String uuid;//唯一标志 
	
	@Description(name="添加用户ID",columnName="userid")
	private Integer userid;
	
	@Description(name="创建用户",columnName="loginName")
	private String loginName;
	
	private Date btime;
	
	private Date etime;
	private Integer isRegular;
	
	private String remark;
	
	private String shareContent;//分享的备注
	private Integer shareNotice;//分享是否已经提醒

	// Constructors

	/** default constructor */
	public Policy() {
	}

	/** full constructor */
	public Policy(String name, String ptype, Integer status, Date addtime,
			Date btime, Date etime, Integer userid, String loginName,
			Integer isRegular, String remark) {
		this.name = name;
		this.ptype = ptype;
		this.status = status;
		this.addtime = addtime;
		this.btime = btime;
		this.etime = etime;
		this.userid = userid;
		this.loginName = loginName;
		this.isRegular = isRegular;
		this.remark = remark;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPtype() {
		return this.ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getAddtime() {
		return this.addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Date getBtime() {
		return this.btime;
	}

	public void setBtime(Date btime) {
		this.btime = btime;
	}

	public Date getEtime() {
		return this.etime;
	}

	public void setEtime(Date etime) {
		this.etime = etime;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Integer getIsRegular() {
		return this.isRegular;
	}

	public void setIsRegular(Integer isRegular) {
		this.isRegular = isRegular;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getShare() {
		return share;
	}

	public void setShare(Integer share) {
		this.share = share;
	}

	public Date getShareTime() {
		return shareTime;
	}

	public void setShareTime(Date shareTime) {
		this.shareTime = shareTime;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getEshare() {
		return eshare;
	}

	public void setEshare(Integer eshare) {
		this.eshare = eshare;
	}

	public String getShareContent() {
		return shareContent;
	}

	public void setShareContent(String shareContent) {
		this.shareContent = shareContent;
	}
	public Integer getShareNotice() {
		return shareNotice;
	}
	public void setShareNotice(Integer shareNotice) {
		this.shareNotice = shareNotice;
	}
}
