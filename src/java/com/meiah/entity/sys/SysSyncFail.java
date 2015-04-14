/** * A级 */
package com.meiah.entity.sys;

import java.util.Date;

public class SysSyncFail {
	private Long id;
	private String keysJSON;
	private Integer userid;
	private String system;
	private Date failtime;
	private String comeFrom;
	
	public SysSyncFail(Long id, String keyJSON, Integer userid, String system,
			Date failtime) {
		this.id = id;
		this.keysJSON = keyJSON;
		this.userid = userid;
		this.system = system;
		this.failtime = failtime;
	}
	public SysSyncFail() {
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKeysJSON() {
		return keysJSON;
	}
	public void setKeysJSON(String keysJSON) {
		this.keysJSON = keysJSON;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public Date getFailtime() {
		return failtime;
	}
	public void setFailtime(Date failtime) {
		this.failtime = failtime;
	}
	public String getComeFrom() {
		return comeFrom;
	}
	public void setComeFrom(String comeFrom) {
		this.comeFrom = comeFrom;
	}
	
}
