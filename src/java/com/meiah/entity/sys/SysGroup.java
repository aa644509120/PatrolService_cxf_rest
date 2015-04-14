/** * A级 */
package com.meiah.entity.sys;

/**
 * SysGroup entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysGroup implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private Long parId;
	private String code;

	// Constructors

	/** default constructor */
	public SysGroup() {
	}

	/** full constructor */
	public SysGroup(String name, Long parId, String code) {
		this.name = name;
		this.parId = parId;
		this.code = code;
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

	public Long getParId() {
		return this.parId;
	}

	public void setParId(Long parId) {
		this.parId = parId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
