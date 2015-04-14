/** * A级 */
package com.meiah.entity.sys;

import java.sql.Timestamp;


/**
 * Log entity. @author MyEclipse Persistence Tools
 */

public class Log  implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
     private Long userid; //用户ID
     private String comeFrom;//来源系统
     private String type;//操作类型
     private String module;//操作模块
     private String content;//操作内容
     private String time;//操作时间，时间格式为yyyy-MM-dd HH:mm:ss
     private String remark;//操作备注
     private String ip;//操作IP,必填
     private String status;//操作状态

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

    public String getComeFrom() {
        return this.comeFrom;
    }
    
    public void setComeFrom(String comeFrom) {
        this.comeFrom = comeFrom;
    }

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public String getModule() {
        return this.module;
    }
    
    public void setModule(String module) {
        this.module = module;
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

    public String getIp() {
        return this.ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
   








}
