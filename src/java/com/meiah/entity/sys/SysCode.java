/** * A级 */
package com.meiah.entity.sys;

public class SysCode implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String codeType;
	private String codeKey;
	private String codeValue;
	private String remark;
	private Integer seq;
	//status=1启用，0为停用
	private Integer status;
	private Long ex1;
	private String ex2;
	private String comeFrom;
 
	public SysCode() {
	}

	public SysCode(String codeType, String codeKey, String codeValue,
			String remark, Integer seq, Integer status, Long ex1, String ex2,
			String comeFrom) {
		this.codeType = codeType;
		this.codeKey = codeKey;
		this.codeValue = codeValue;
		this.remark = remark;
		this.seq = seq;
		this.status = status;
		this.ex1 = ex1;
		this.ex2 = ex2;
		this.comeFrom = comeFrom;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeType() {
		return this.codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCodeKey() {
		return this.codeKey;
	}

	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}

	public String getCodeValue() {
		return this.codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSeq() {
		return this.seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getEx1() {
		return this.ex1;
	}

	public void setEx1(Long ex1) {
		this.ex1 = ex1;
	}

	public String getEx2() {
		return this.ex2;
	}

	public void setEx2(String ex2) {
		this.ex2 = ex2;
	}

	public String getComeFrom() {
		return this.comeFrom;
	}

	public void setComeFrom(String comeFrom) {
		this.comeFrom = comeFrom;
	}

}
