/** * A级 */
package com.meiah.entity.task;

import java.sql.Timestamp;

/**
 * 任务提醒实体类
 * @author empty
 */
public class TaskNotice implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String content;//提醒内容，格式由各个子系统自己定义
	private Long userid;//提醒任务添加用户
	private Long tuserid;//任务提醒对象，
	private String time;//提醒任务添加时间
	private Integer flag;//是否已读


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

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Long getTuserid() {
		return tuserid;
	}

	public void setTuserid(Long tuserid) {
		this.tuserid = tuserid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
