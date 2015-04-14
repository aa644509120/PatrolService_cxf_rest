/** * A级 */
package com.meiah.entity.reportinfo;

import java.util.Date;
import java.util.List;

import com.meiah.core.util.Validator;

/**
 * 互联网信息报送实体
 */
public class Winfo implements java.io.Serializable {

	private static final long serialVersionUID = -6551297715125215918L;
	private Long id;
	private Long userid;//用户ID
	private Integer status;//信息状态  0:保存  1:上报
	private String susr;//发送人
	private Long sflag;//发送单位级别
	private Long rflag;//接收单位级别
	private Date stime;//添加时间
	private String sdanwei;//发送单位
	private String rdanwei;//接收单位
	private String fenlei;//分类
	private String kword;//主关键词
	private String kwordsyi;//次关键词
	private String biaoti;//标题
	private String neirong;//内容
	private String yuanbiaoti;//原标题

	private String yuzhong;//语种

	private String siteurl;//网站URL

	private String sitename;//网站名称

	private String sitedns;//dns域名

	private String zuzhi;//组织

	private String renyuan;//人员

	private String bianji;//编辑

	private String shenhe;//核搞人

	private String shenpi;//审批人

	private String caiyong;//采用

	private String pishi;//处置情况

	private String beizhu;//备注

	private Double defen;//得分

	private String huodongxingshi;//活动形式
	private String wulidiyu;//物理地域
	private Long fenshu;//分数
	private String attfile;//附件
	private String caiyonguser;//采用人
	private Date caiyongtime;//采用时间
	private String shifouziyi;//是否自译
	private String personinfo;//人员信息
	private String diaocha;//调查人
	private String xinxileixing;//信息类型
	private Double leixingjiafen;//类型加分
	private String areaCode;//上报地区
	private String winfoid;
	private Integer iscommit;//是否已报送
	private Date committime;
	private Integer iscommend;//是否推荐
	private String codeKey;//类别编码
	private String codeType;//类别
	private Integer attfileFlag;//是否存在附件，如果为空或者为零，表示没有附件，如果为1表示存在附件
	private long codeId;
	private List<String> urlList;
	
	
	public Winfo(){}
	public Winfo(Long id,Long userid, String susr, Long sflag, Long rflag, Date stime,
			String sdanwei, String rdanwei, String fenlei, String kword,
			String kwordsyi, String biaoti, String neirong, String yuanbiaoti,
			String yuzhong, String siteurl, String sitename, String sitedns,
			String zuzhi, String renyuan, String bianji, String shenhe,
			String shenpi, String caiyong, String pishi, String beizhu,
			Double defen, String huodongxingshi, String wulidiyu, Long fenshu,
			String attfile, String caiyonguser, Date caiyongtime,
			String shifouziyi, String personinfo, String diaocha,
			String xinxileixing, Double leixingjiafen, String winfoid) {
		super();
		this.userid=userid;
		this.id = id;
		this.susr = susr;
		this.sflag = sflag;
		this.rflag = rflag;
		this.stime = stime;
		this.sdanwei = sdanwei;
		this.rdanwei = rdanwei;
		this.fenlei = fenlei;
		this.kword = kword;
		this.kwordsyi = kwordsyi;
		this.biaoti = biaoti;
		this.neirong = neirong;
		this.yuanbiaoti = yuanbiaoti;
		this.yuzhong = yuzhong;
		this.siteurl = siteurl;
		this.sitename = sitename;
		this.sitedns = sitedns;
		this.zuzhi = zuzhi;
		this.renyuan = renyuan;
		this.bianji = bianji;
		this.shenhe = shenhe;
		this.shenpi = shenpi;
		this.caiyong = caiyong;
		this.pishi = pishi;
		this.beizhu = beizhu;
		this.defen = defen;
		this.huodongxingshi = huodongxingshi;
		this.wulidiyu = wulidiyu;
		this.fenshu = fenshu;
		this.attfile = attfile;
		this.caiyonguser = caiyonguser;
		this.caiyongtime = caiyongtime;
		this.shifouziyi = shifouziyi;
		this.personinfo = personinfo;
		this.diaocha = diaocha;
		this.xinxileixing = xinxileixing;
		this.leixingjiafen = leixingjiafen;
		this.winfoid = winfoid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getSusr() {
		return susr;
	}

	public void setSusr(String susr) {
		this.susr = susr;
	}

	public Long getSflag() {
		return sflag;
	}

	public void setSflag(Long sflag) {
		this.sflag = sflag;
	}

	public Long getRflag() {
		return rflag;
	}

	public void setRflag(Long rflag) {
		this.rflag = rflag;
	}

	public Date getStime() {
		return stime;
	}

	public void setStime(Date stime) {
		this.stime = stime;
	}

	public String getSdanwei() {
		return sdanwei;
	}

	public void setSdanwei(String sdanwei) {
		this.sdanwei = sdanwei;
	}

	public String getRdanwei() {
		return rdanwei;
	}

	public void setRdanwei(String rdanwei) {
		this.rdanwei = rdanwei;
	}

	public String getFenlei() {
		return fenlei;
	}

	public void setFenlei(String fenlei) {
		this.fenlei = fenlei;
	}

	public String getKword() {
		return kword;
	}

	public void setKword(String kword) {
		this.kword = kword;
	}

	public String getKwordsyi() {
		return kwordsyi;
	}

	public void setKwordsyi(String kwordsyi) {
		this.kwordsyi = kwordsyi;
	}

	public String getBiaoti() {
		return biaoti;
	}

	public void setBiaoti(String biaoti) {
		this.biaoti = biaoti;
	}

	public String getNeirong() {
		return neirong;
	}

	public void setNeirong(String neirong) {
		this.neirong = neirong;
	}

	public String getYuanbiaoti() {
		return yuanbiaoti;
	}

	public void setYuanbiaoti(String yuanbiaoti) {
		this.yuanbiaoti = yuanbiaoti;
	}

	public String getYuzhong() {
		return yuzhong;
	}

	public void setYuzhong(String yuzhong) {
		this.yuzhong = yuzhong;
	}

	public String getSiteurl() {
		return siteurl;
	}

	public void setSiteurl(String siteurl) {
		this.siteurl = siteurl;
	}

	public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	public String getSitedns() {
		return sitedns;
	}

	public void setSitedns(String sitedns) {
		this.sitedns = sitedns;
	}

	public String getZuzhi() {
		return zuzhi;
	}

	public void setZuzhi(String zuzhi) {
		this.zuzhi = zuzhi;
	}

	public String getRenyuan() {
		return renyuan;
	}

	public void setRenyuan(String renyuan) {
		this.renyuan = renyuan;
	}

	public String getBianji() {
		return bianji;
	}

	public void setBianji(String bianji) {
		this.bianji = bianji;
	}

	public String getShenhe() {
		return shenhe;
	}

	public void setShenhe(String shenhe) {
		this.shenhe = shenhe;
	}

	public String getShenpi() {
		return shenpi;
	}

	public void setShenpi(String shenpi) {
		this.shenpi = shenpi;
	}

	public String getCaiyong() {
		return caiyong;
	}

	public void setCaiyong(String caiyong) {
		this.caiyong = caiyong;
	}

	public String getPishi() {
		return pishi;
	}

	public void setPishi(String pishi) {
		this.pishi = pishi;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public Double getDefen() {
		return defen;
	}

	public void setDefen(Double defen) {
		this.defen = defen;
	}

	public String getHuodongxingshi() {
		return huodongxingshi;
	}

	public void setHuodongxingshi(String huodongxingshi) {
		this.huodongxingshi = huodongxingshi;
	}

	public String getWulidiyu() {
		return wulidiyu;
	}

	public void setWulidiyu(String wulidiyu) {
		this.wulidiyu = wulidiyu;
	}

	public Long getFenshu() {
		return fenshu;
	}

	public void setFenshu(Long fenshu) {
		this.fenshu = fenshu;
	}

	public String getAttfile() {
		return attfile;
	}

	public void setAttfile(String attfile) {
		this.attfile = attfile;
	}

	public String getCaiyonguser() {
		return caiyonguser;
	}

	public void setCaiyonguser(String caiyonguser) {
		this.caiyonguser = caiyonguser;
	}

	public Date getCaiyongtime() {
		return caiyongtime;
	}

	public void setCaiyongtime(Date caiyongtime) {
		this.caiyongtime = caiyongtime;
	}

	public String getShifouziyi() {
		return shifouziyi;
	}

	public void setShifouziyi(String shifouziyi) {
		this.shifouziyi = shifouziyi;
	}

	public String getPersoninfo() {
		return personinfo;
	}

	public void setPersoninfo(String personinfo) {
		this.personinfo = personinfo;
	}

	public String getDiaocha() {
		return diaocha;
	}

	public void setDiaocha(String diaocha) {
		this.diaocha = diaocha;
	}

	public String getXinxileixing() {
		return xinxileixing;
	}

	public void setXinxileixing(String xinxileixing) {
		this.xinxileixing = xinxileixing;
	}

	public Double getLeixingjiafen() {
		return leixingjiafen;
	}

	public void setLeixingjiafen(Double leixingjiafen) {
		this.leixingjiafen = leixingjiafen;
	}

	public String getWinfoid() {
		return winfoid;
	}

	public void setWinfoid(String winfoid) {
		this.winfoid = winfoid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIscommit() {
		return iscommit;
	}

	public void setIscommit(Integer iscommit) {
		this.iscommit = iscommit;
	}

	public Date getCommittime() {
		return committime;
	}

	public void setCommittime(Date committime) {
		this.committime = committime;
	}
	
	/**
	 * 状态的转换方法
	 * @return
	 */
	public String getStatusConvert() {
		if(Validator.isNotNull(status)) {
			return status==1?"已提交":"未提交";
		}
		return "";
	}
	/**
	 *  状态的转换方法
	 * @param text
	 */
	public void setStatusConvert(String text) {
		if(Validator.isNotNull(text)) {
			status = "已提交".equals(text.trim())?1:0;
		}else status = 0;
	}
	public Integer getIscommend() {
		return iscommend;
	}
	public void setIscommend(Integer iscommend) {
		this.iscommend = iscommend;
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
	public long getCodeId() {
		return codeId;
	}
	public void setCodeId(long codeId) {
		this.codeId = codeId;
	}
	public Integer getAttfileFlag() {
		return attfileFlag;
	}
	public void setAttfileFlag(Integer attfileFlag) {
		this.attfileFlag = attfileFlag;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public List<String> getUrlList() {
		return urlList;
	}
	public void setUrlList(List<String> urlList) {
		this.urlList = urlList;
	}
}
