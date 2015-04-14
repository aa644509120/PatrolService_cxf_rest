/** * A级 */
package com.meiah.entity.favorite;

import java.sql.Timestamp;
import java.util.List;

/**
 * 收藏实体类,收藏的附件为type=555,extend1=4
 * @author empty
 */
public class Favorite implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String title;//标题
	private String content;//内容
	private String url;//url
	private String publishtime;//消息发布时间
	private String topdomain;//消息发布网站顶级域名
	private String author;//消息发布者
	private String authorurl;//消息发布者头像的URL
	private String ip;//消息IP
	private String comeFrom;//收藏来源哪个巡查系统
	private String category;//来源：论坛、微博、博客、新闻、QQ群、境外、网页（其他）
	private String address;//地理位置
	private Long userid;//收藏用户
	private String username;//收藏用户名
	private String favortime;//收藏时间
	private String remark;//备注
	private String lable;//收藏标签
	private String infotype;//信息类型
	private String infoFrom;//信息来源,例如论坛来源哪个论坛哪个版块格式如:崇阳论坛-崇阳热线
	private List<String> urlList;//图片的URL地址,多线程去下载图片
	private String codeType;
	private String codeKey;
	private Integer isdel;
	private Long infoid;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	 

	public String getTopdomain() {
		return this.topdomain;
	}

	public void setTopdomain(String topdomain) {
		this.topdomain = topdomain;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthorurl() {
		return this.authorurl;
	}

	public void setAuthorurl(String authorurl) {
		this.authorurl = authorurl;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getComeFrom() {
		return this.comeFrom;
	}

	public void setComeFrom(String comeFrom) {
		this.comeFrom = comeFrom;
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

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLable() {
		return this.lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}

	public String getInfotype() {
		return this.infotype;
	}

	public void setInfotype(String infotype) {
		this.infotype = infotype;
	}

	public List<String> getUrlList() {
		return urlList;
	}

	public void setUrlList(List<String> urlList) {
		this.urlList = urlList;
	}


	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCodeKey() {
		return codeKey;
	}

	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public Long getInfoid() {
		return infoid;
	}

	public void setInfoid(Long infoid) {
		this.infoid = infoid;
	}

	public String getInfoFrom() {
		return infoFrom;
	}

	public void setInfoFrom(String infoFrom) {
		this.infoFrom = infoFrom;
	}

	public String getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(String publishtime) {
		this.publishtime = publishtime;
	}

	public String getFavortime() {
		return favortime;
	}

	public void setFavortime(String favortime) {
		this.favortime = favortime;
	}
}
