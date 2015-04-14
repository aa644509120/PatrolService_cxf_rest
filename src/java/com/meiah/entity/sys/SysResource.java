/** * A级 */
package com.meiah.entity.sys;

import java.util.Date;

/**
 * SysResource entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysResource implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Integer pid;
	private String code;
	private String name;
	private Integer level;
	private String url;
	private String icon;
	private Integer enable;
	private Integer weight;
	private Byte isguide;
	private Integer foldtype;
	private Integer type;
	private String uniqueCode;
	private Date createtime;
	private Byte portalConfig;
	private Integer positionType;
	private Integer requireAuth;
	private String comeFrom;
	private String pname;			//上级资源名称;
	private Integer tid; //子菜单的id，主要用于第三级菜单的构建
	private Integer sid; //子菜单的id，主要用于第二级菜单的构建
	private Long typeId;//系统大类别,用于桌面系统
	// Constructors
	/** default constructor */
	public SysResource() {
	}

	/** full constructor */
	public SysResource(Integer pid, String code, String name, Integer level,
			String url, String icon, Integer enable, Integer weight,
			Byte isguide, Integer foldtype, Integer type, String uniqueCode,
			Date createtime, Byte portalConfig, Integer positionType,
			Integer requireAuth, String comeFrom) {
		this.pid = pid;
		this.code = code;
		this.name = name;
		this.level = level;
		this.url = url;
		this.icon = icon;
		this.enable = enable;
		this.weight = weight;
		this.isguide = isguide;
		this.foldtype = foldtype;
		this.type = type;
		this.uniqueCode = uniqueCode;
		this.createtime = createtime;
		this.portalConfig = portalConfig;
		this.positionType = positionType;
		this.requireAuth = requireAuth;
		this.comeFrom = comeFrom;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getEnable() {
		return this.enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public Integer getWeight() {
		return this.weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Byte getIsguide() {
		return this.isguide;
	}

	public void setIsguide(Byte isguide) {
		this.isguide = isguide;
	}

	public Integer getFoldtype() {
		return this.foldtype;
	}

	public void setFoldtype(Integer foldtype) {
		this.foldtype = foldtype;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUniqueCode() {
		return this.uniqueCode;
	}

	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Byte getPortalConfig() {
		return this.portalConfig;
	}

	public void setPortalConfig(Byte portalConfig) {
		this.portalConfig = portalConfig;
	}

	public Integer getPositionType() {
		return this.positionType;
	}

	public void setPositionType(Integer positionType) {
		this.positionType = positionType;
	}

	public Integer getRequireAuth() {
		return this.requireAuth;
	}

	public void setRequireAuth(Integer requireAuth) {
		this.requireAuth = requireAuth;
	}

	public String getComeFrom() {
		return this.comeFrom;
	}

	public void setComeFrom(String comeFrom) {
		this.comeFrom = comeFrom;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
}
