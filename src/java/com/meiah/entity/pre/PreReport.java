/** * A级 */
package com.meiah.entity.pre;

import java.util.Date;
import java.util.List;
 
public class PreReport implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long operatorId;
	
	private Integer isread;
	
	
	private Long userId;
	
	private String username;
	
	private Date time;

	private String title;

	private String url;
	
	private String publishTime;
	
	private String remark;

	private String content;
	
	private Long attachmentId;
	
	private String author;
	
	private String authorUrl;
	
	private String ip;
	
	private String topdomain;
	
	private String comeFrom;
	
	private Float lon;
	
	private Float lat;
	
	private String category;
	
	private String address;
	
	private Integer ispush;
	
	private Integer isreport;
	
	private Integer isSend;
	private Integer topFlag;//是否置顶的标志，1是置顶
	
	private String codeKey;
	
	private String codeType;
	private Long codeId;
	private String codename;
	private Long updateuserid;
	private Long assessorid;
	private String approval;
	private Long approvaluserid;
	private Date updatetime;
	private Long signuserid;//被指派人
	private Long bsignuserid;//指派人
	private String signcontent;//指派备注
	private String signname;//被指派人姓名
	private String bsignname;//指派人姓名
	
	private Long views;//阅读数
	private Long replies;//回复/点赞数
	
	private String wechat;
	
	
	private Integer isView; //是否阅读过,1为已阅
	private Long vuserid;	//阅读者
	private String vtime;		//阅读时间
	private Integer attfileFlag;//是否存在附件的标志，1为存在
	private Integer cutImgFlag;//是否存在截图的标志，1为存在
	private Integer mould;//模板类型：1为巡查模板，2为落地模板、3为普通
	private String realname;//发布人真实名字
	private Integer sex;//性别
	private String age;//年龄
	private String registed;//户籍地
	private String tempAddress;//暂住地
	private String phone;//手机号
	private String publishContent;//发布内容
	private String suggest;//建议
	private String notice;//通报
	
	private String month;//月份
	private String day;//日期
	private String hm;//小时和分钟
	
	private String infofrom;//来源哪个系统，例如新浪微博、小鱼网之类的
	private String idCard;//身份证号码
	
	private List<String> urlList;
	// Constructors

	/** default constructor */
	public PreReport() {
	}

	/** minimal constructor */
	public PreReport(Long userId, Long attachmentId) {
		this.userId = userId;
		this.attachmentId = attachmentId;
	}

	/** full constructor */
	public PreReport(Long operatorId, Integer isread, Long userId, Date time,
			String title, String url, String publishTime, String remark,
			String content, Long attachmentId, String author, String authorUrl,
			String ip, String topdomain, String comeFrom, Float lon, Float lat,
			String category, String address,String username,Long updateuserid,Long assessorid,String approval,Long approvaluserid,
			Date updatetime) {
		this.operatorId = operatorId;
		this.isread = isread;
		this.userId = userId;
		this.time = time;
		this.title = title;
		this.url = url;
		this.publishTime = publishTime;
		this.remark = remark;
		this.content = content;
		this.attachmentId = attachmentId;
		this.author = author;
		this.authorUrl = authorUrl;
		this.ip = ip;
		this.topdomain = topdomain;
		this.comeFrom = comeFrom;
		this.lon = lon;
		this.lat = lat;
		this.category = category;
		this.address = address;
		this.username = username;
		this.updateuserid= updateuserid;
		this.assessorid = assessorid;
		this.approval = approval;
		this.approvaluserid = approvaluserid;
		this.updatetime = updatetime;
	}
	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOperatorId() {
		return this.operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}
	

	public Integer getIsread() {
		return isread;
	}

	public void setIsread(Integer isread) {
		this.isread = isread;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getAttachmentId() {
		return this.attachmentId;
	}

	public void setAttachmentId(Long attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthorUrl() {
		return authorUrl;
	}

	public void setAuthorUrl(String authorUrl) {
		this.authorUrl = authorUrl;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTopdomain() {
		return this.topdomain;
	}

	public void setTopdomain(String topdomain) {
		this.topdomain = topdomain;
	}

	public String getComeFrom() {
		return this.comeFrom;
	}

	public void setComeFrom(String comeFrom) {
		this.comeFrom = comeFrom;
	}

	public Float getLon() {
		return this.lon;
	}

	public void setLon(Float lon) {
		this.lon = lon;
	}

	public Float getLat() {
		return this.lat;
	}

	public void setLat(Float lat) {
		this.lat = lat;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getIspush() {
		return ispush;
	}

	public void setIspush(Integer ispush) {
		this.ispush = ispush;
	}

	public Integer getIsreport() {
		return isreport;
	}

	public void setIsreport(Integer isreport) {
		this.isreport = isreport;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCodeKey() {
		return codeKey;
	}

	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public Long getCodeId() {
		return codeId;
	}

	public void setCodeId(Long codeId) {
		this.codeId = codeId;
	}

	public Integer getIsSend() {
		return isSend;
	}

	public void setIsSend(Integer isSend) {
		this.isSend = isSend;
	}

	public String getCodename() {
		return codename;
	}

	public void setCodename(String codename) {
		this.codename = codename;
	}

	public Long getUpdateuserid() {
		return updateuserid;
	}

	public void setUpdateuserid(Long updateuserid) {
		this.updateuserid = updateuserid;
	}

	public Long getAssessorid() {
		return assessorid;
	}

	public void setAssessorid(Long assessorid) {
		this.assessorid = assessorid;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	public Long getApprovaluserid() {
		return approvaluserid;
	}

	public void setApprovaluserid(Long approvaluserid) {
		this.approvaluserid = approvaluserid;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Integer getTopFlag() {
		return topFlag;
	}

	public void setTopFlag(Integer topFlag) {
		this.topFlag = topFlag;
	}

	public Long getSignuserid() {
		return signuserid;
	}

	public void setSignuserid(Long signuserid) {
		this.signuserid = signuserid;
	}

	public Long getBsignuserid() {
		return bsignuserid;
	}

	public void setBsignuserid(Long bsignuserid) {
		this.bsignuserid = bsignuserid;
	}

	public String getSigncontent() {
		return signcontent;
	}

	public void setSigncontent(String signcontent) {
		this.signcontent = signcontent;
	}

	public String getSignname() {
		return signname;
	}

	public void setSignname(String signname) {
		this.signname = signname;
	}

	public String getBsignname() {
		return bsignname;
	}

	public void setBsignname(String bsignname) {
		this.bsignname = bsignname;
	}

	public List<String> getUrlList() {
		return urlList;
	}

	public void setUrlList(List<String> urlList) {
		this.urlList = urlList;
	}

	public Long getViews() {
		return views;
	}

	public void setViews(Long views) {
		this.views = views;
	}

	public Long getReplies() {
		return replies;
	}

	public void setReplies(Long replies) {
		this.replies = replies;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public Integer getAttfileFlag() {
		return attfileFlag;
	}

	public void setAttfileFlag(Integer attfileFlag) {
		this.attfileFlag = attfileFlag;
	}

	public Integer getCutImgFlag() {
		return cutImgFlag;
	}

	public void setCutImgFlag(Integer cutImgFlag) {
		this.cutImgFlag = cutImgFlag;
	}

	public Integer getMould() {
		return mould;
	}

	public void setMould(Integer mould) {
		this.mould = mould;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getRegisted() {
		return registed;
	}

	public void setRegisted(String registed) {
		this.registed = registed;
	}

	public String getTempAddress() {
		return tempAddress;
	}

	public void setTempAddress(String tempAddress) {
		this.tempAddress = tempAddress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPublishContent() {
		return publishContent;
	}

	public void setPublishContent(String publishContent) {
		this.publishContent = publishContent;
	}

	public String getSuggest() {
		return suggest;
	}

	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public Integer getIsView() {
		return isView;
	}

	public void setIsView(Integer isView) {
		this.isView = isView;
	}

	public Long getVuserid() {
		return vuserid;
	}

	public void setVuserid(Long vuserid) {
		this.vuserid = vuserid;
	}

	public String getVtime() {
		return vtime;
	}

	public void setVtime(String vtime) {
		this.vtime = vtime;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getHm() {
		return hm;
	}

	public void setHm(String hm) {
		this.hm = hm;
	}

	public String getInfofrom() {
		return infofrom;
	}

	public void setInfofrom(String infofrom) {
		this.infofrom = infofrom;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
}
