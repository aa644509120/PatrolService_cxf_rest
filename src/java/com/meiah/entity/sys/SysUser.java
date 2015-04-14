/** * A级 */
package com.meiah.entity.sys;

import java.util.Date;
/**
 * @author MyEclipse Persistence Tools
 */
public class SysUser implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String usercode;
	private String loginname;
	private String password;
	private Integer roleid;
	private String roleName;
	private String rolecode;
	private Long groupid;
	private String groupName;
	private String areacode;
	private String policecode;
	private String address;
	private String phone;
	private String mobile;
	private String email;
	private String qq;
	private Integer status;
	private Date expiringdate;
	private String ukeyserialno;
	private String remark;
	private Integer weight;
	private Byte showallarea;
	private Short isanalyst;
	private Byte isOnline;
	private Byte isPcOnline;
	private Integer showCommend;
	private String mobileSequence;
	private String dbcode;
	private String worktel;
	private Integer ischeckuser;
	private Integer mvalidate;
	private Integer browserTFlag;
	private String decode; //缁勭粐缂栫爜
	private String pids;
	private String loginNames;
	private String roleNames;//瀛樻斁瑙掕壊鍚嶇О涓诧紝渚嬪 绠＄悊鍛�淇℃伅绠＄悊鍛�
	private String rids; //瀛樻斁鐨勬槸瑙掕壊id涓�渚嬪1,2,3
	private String pnumber;//鎵嬫満搴忓垪鍙凤紝绌轰负娌℃湁缁戝畾锛屽涓簭鍒楀彿鐢ㄩ�鍙峰垎寮�
	private Integer pnumberflag;//0涓烘湭寮�惎搴忓垪鍙烽攣瀹氾紝1鏄紑鍚簭鍒楀彿閿佸畾
	private Integer pflag;//鎵嬫満搴忓垪鍙凤紝绌轰负娌℃湁缁戝畾锛屽涓簭鍒楀彿鐢ㄩ�鍙峰垎寮�
	private Long organizationid;
	private String organizationname;
	private String wechat;
	
	// Constructors
	/** default constructor */
	public SysUser() {
	}

	/** full constructor */
	public SysUser(String name, String usercode, String loginname,
			String password, Integer roleid, String rolecode, Long groupid,
			String areacode, String policecode, String address, String phone,
			String mobile, String email, String qq, Integer status,
			Date expiringdate, String ukeyserialno, String remark,
			Integer weight, Byte showallarea, Short isanalyst, Byte isOnline,
			Byte isPcOnline, Integer showCommend, String mobileSequence,
			String dbcode, String worktel, Integer ischeckuser,
			Integer mvalidate , String roleName , String groupName , String pids,String loginNames,String rids,String roleNames) {
		this.name = name;
		this.usercode = usercode;
		this.loginname = loginname;
		this.password = password;
		this.roleid = roleid;
		this.rolecode = rolecode;
		this.groupid = groupid;
		this.areacode = areacode;
		this.policecode = policecode;
		this.address = address;
		this.phone = phone;
		this.mobile = mobile;
		this.email = email;
		this.qq = qq;
		this.status = status;
		this.expiringdate = expiringdate;
		this.ukeyserialno = ukeyserialno;
		this.remark = remark;
		this.weight = weight;
		this.showallarea = showallarea;
		this.isanalyst = isanalyst;
		this.isOnline = isOnline;
		this.isPcOnline = isPcOnline;
		this.showCommend = showCommend;
		this.mobileSequence = mobileSequence;
		this.dbcode = dbcode;
		this.worktel = worktel;
		this.ischeckuser = ischeckuser;
		this.mvalidate = mvalidate;
		this.roleName = roleName;
		this.groupName = groupName;
		this.pids = pids;
		this.loginNames = loginNames;
		this.rids = rids;
		this.roleNames = roleNames;
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

	public String getUsercode() {
		return this.usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getLoginname() {
		return this.loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public String getRolecode() {
		return this.rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

	public Long getGroupid() {
		return this.groupid;
	}

	public void setGroupid(Long groupid) {
		this.groupid = groupid;
	}

	public String getAreacode() {
		return this.areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getPolicecode() {
		return this.policecode;
	}

	public void setPolicecode(String policecode) {
		this.policecode = policecode;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getExpiringdate() {
		return this.expiringdate;
	}

	public void setExpiringdate(Date expiringdate) {
		this.expiringdate = expiringdate;
	}

	public String getUkeyserialno() {
		return this.ukeyserialno;
	}

	public void setUkeyserialno(String ukeyserialno) {
		this.ukeyserialno = ukeyserialno;
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

	public Byte getShowallarea() {
		return this.showallarea;
	}

	public void setShowallarea(Byte showallarea) {
		this.showallarea = showallarea;
	}

	public Short getIsanalyst() {
		return this.isanalyst;
	}

	public void setIsanalyst(Short isanalyst) {
		this.isanalyst = isanalyst;
	}

	public Byte getIsOnline() {
		return this.isOnline;
	}

	public void setIsOnline(Byte isOnline) {
		this.isOnline = isOnline;
	}

	public Byte getIsPcOnline() {
		return this.isPcOnline;
	}

	public void setIsPcOnline(Byte isPcOnline) {
		this.isPcOnline = isPcOnline;
	}

	public Integer getShowCommend() {
		return this.showCommend;
	}

	public void setShowCommend(Integer showCommend) {
		this.showCommend = showCommend;
	}

	public String getMobileSequence() {
		return this.mobileSequence;
	}

	public void setMobileSequence(String mobileSequence) {
		this.mobileSequence = mobileSequence;
	}

	public String getDbcode() {
		return this.dbcode;
	}

	public void setDbcode(String dbcode) {
		this.dbcode = dbcode;
	}

	public String getWorktel() {
		return this.worktel;
	}

	public void setWorktel(String worktel) {
		this.worktel = worktel;
	}

	public Integer getIscheckuser() {
		return this.ischeckuser;
	}

	public void setIscheckuser(Integer ischeckuser) {
		this.ischeckuser = ischeckuser;
	}

	public Integer getMvalidate() {
		return this.mvalidate;
	}

	public void setMvalidate(Integer mvalidate) {
		this.mvalidate = mvalidate;
	}

	public Integer getBrowserTFlag() {
		return browserTFlag;
	}

	public void setBrowserTFlag(Integer browserTFlag) {
		this.browserTFlag = browserTFlag;
	}

	public String getDecode() {
		return decode;
	}

	public void setDecode(String decode) {
		this.decode = decode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getPids() {
		return pids;
	}

	public void setPids(String pids) {
		this.pids = pids;
	}

	public String getLoginNames() {
		return loginNames;
	}

	public void setLoginNames(String loginNames) {
		this.loginNames = loginNames;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public String getRids() {
		return rids;
	}

	public void setRids(String rids) {
		this.rids = rids;
	}
 

	public Long getOrganizationid() {
		return organizationid;
	}

	public void setOrganizationid(Long organizationid) {
		this.organizationid = organizationid;
	}

	public String getOrganizationname() {
		return organizationname;
	}

	public void setOrganizationname(String organizationname) {
		this.organizationname = organizationname;
	}

	public String getPnumber() {
		return pnumber;
	}

	public void setPnumber(String pnumber) {
		this.pnumber = pnumber;
	}

	public Integer getPnumberflag() {
		return pnumberflag;
	}

	public void setPnumberflag(Integer pnumberflag) {
		this.pnumberflag = pnumberflag;
	}

	public Integer getPflag() {
		return pflag;
	}

	public void setPflag(Integer pflag) {
		this.pflag = pflag;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
}
