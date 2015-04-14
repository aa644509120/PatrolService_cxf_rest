/** * A级 */
package com.meiah.entity.sys;

import java.util.HashMap;
import java.util.Map;

/**
 * SysRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysRole implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String code;
	private String resources;
	private String remark;
	private Integer weight;
	private Integer type; //1超级管理员 2信息值班员 3信息巡查组管理员 4信息巡查人员 5PAD账户
	private Long userId;

	private String phoneresource;
	
	public static Map<String,Integer> ROLE_CODE_MAP = new HashMap<String, Integer>();
	static{
		ROLE_CODE_MAP.put("ADMIN", 1); 
		ROLE_CODE_MAP.put("OPERATOR", 2);
		ROLE_CODE_MAP.put("MANAGER", 3);
		ROLE_CODE_MAP.put("USER", 4);
		ROLE_CODE_MAP.put("PAD", 5);
	}

	// Constructors

	/** default constructor */
	public SysRole() {
	}

	/** full constructor */
	public SysRole(String name, String code, String resources, String remark,
			Integer weight, Integer type, String phoneresource , Long userId) {
		this.name = name;
		this.code = code;
		this.resources = resources;
		this.remark = remark;
		this.weight = weight;
		this.type = type;
		this.phoneresource = phoneresource;
		this.userId = userId;
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

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getResources() {
		return this.resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getWeight() {
		return this.weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPhoneresource() {
		return this.phoneresource;
	}

	public void setPhoneresource(String phoneresource) {
		this.phoneresource = phoneresource;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
