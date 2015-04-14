/** * A级 */
package com.meiah.entity.reportinfo;

import java.util.Date;
import java.util.List;

/**
 * Hwinfo entity. @author MyEclipse Persistence Tools
 */

public class Hwinfo implements java.io.Serializable {
	private static final long serialVersionUID = -1035774946949390353L;
	private Long userid;//用户ID
	private Integer status;//信息状态  0:保存  1:上报 
	private Long id;
	private String susr;
	private Long sflag;
	private Long rflag;
	private Date stime;
	
	private Integer iscommend;//是否推荐
	
	private String sdanwei;//发送单位
	
	private String rdanwei;//接收单位
	private String wuli;
	
	private String siteurls;//URL地址
	
	private String sitenames;//网站名称
	private String langname;
	
	private String lanshuxing;//栏目名称
	
	private String dizhi;//网站物理地址
	
	private String ip;//网站物理IP
	
	private String owner;//活动形式
	
	private String fabushijian;//发帖时间
	
	private String fabuip;//发帖IP
	
	private String fatieren;//发帖人
	
	private String xingzhi;//主关键词
	
	private String yuanbiaoti;//原始网页标题
	
	private String beizhu;//备注
	
	private String bendichuzhi;//处置意见
	private String fengdu;
	private String shenpiren;
	private String buyijian;
	private String yidichuzhi;
	private Date yidichuzhitime;
	private String sitemiaoshu;
	private Date fendutime;
	private String jingwaixinzhi;
	private Long defen;
	private String gongshenpiren;
	private String qingkuangbeizhu;
	private String jiange;
	
	private String leibie;//有害信息类别
	
	private String kwords;//次关键词
	
	private String bianji;//编辑人
	
	private String shenhe;//核稿人
	
	private String shenpi;//审批人
	private Long zhuangtai;
	
	private String falvguize;//法律依据
	private String wulileibie;
	
	private String beian;//信息产业部备案信息
	
	private String yhxzbz;//关键词备注
	
	private String faburenshudi;//发布人物理属地
	
	private String gajgbaxx;//公安机关备案信息
	
	private Long xxly;//信息来源
	private String newid;
	private String urldomain;
	private String sitetype;
	
	private Long hwinfotype;//有害信息类型
	private String attfile;
	private String dealman;
	private String attfileas;
	private String hechakeywords;//核查关键字
	private String hwinfoid;
	private String hechasentence;//核查长句
	private Integer iscommit;//是否已报送
	private String codeKey;//类别编码
	private String codeType;//类别
	private Date committime;
	private Integer cutImageResult;//自动截屏结果 0:失败  1成功
	private Integer ipAnalyResult;//IP自动落地结果  0:失败  1成功
	private Integer cutImgFlag;//是否截图，如果为空或者为零，表示没有截图，如果为1表示截图成功，如果为2表示正在截图
	private Integer attfileFlag;//是否存在附件，如果为空或者为零，表示没有附件，如果为1表示存在附件
	private String attachIds;//附件ID串，例如：1,2,3,4
	
	private List<String> urlList;
	
	private Long codeId;
	// Constructors

	/** default constructor */
	public Hwinfo() {
	}

	/** minimal constructor */
	public Hwinfo(String susr, Long sflag, Long rflag, String sdanwei, String rdanwei, String siteurls, String sitenames, String dizhi, String ip, String xingzhi, String yuanbiaoti, String leibie,
			String bianji, String shenhe, String shenpi) {
		this.susr = susr;
		this.sflag = sflag;
		this.rflag = rflag;
		this.sdanwei = sdanwei;
		this.rdanwei = rdanwei;
		this.siteurls = siteurls;
		this.sitenames = sitenames;
		this.dizhi = dizhi;
		this.ip = ip;
		this.xingzhi = xingzhi;
		this.yuanbiaoti = yuanbiaoti;
		this.leibie = leibie;
		this.bianji = bianji;
		this.shenhe = shenhe;
		this.shenpi = shenpi;
	}
	
	public Hwinfo(String susr,String rdanwei, String wuli, String siteurls, String sitenames, String langname, String lanshuxing,
			String dizhi, String ip, String owner, String fabushijian, String fabuip, String fatieren, String xingzhi, String yuanbiaoti, String beizhu, String bendichuzhi, String fengdu,
			String shenpiren, String buyijian, String yidichuzhi, Date yidichuzhitime, String sitemiaoshu, Date fendutime, String jingwaixinzhi, Long defen, String gongshenpiren,
			String qingkuangbeizhu, String jiange, String leibie, String kwords, String bianji, String shenhe, String shenpi, Long zhuangtai, String falvguize, String wulileibie, String beian,
			String yhxzbz, String faburenshudi, String gajgbaxx, Long xxly, String newid, String urldomain, String sitetype, Long hwinfotype, String attfile, String dealman, String attfileas,
			String hechakeywords, String hwinfoid, String hechasentence) {
		this.rdanwei = rdanwei;
		this.wuli = wuli;
		this.siteurls = siteurls;
		this.sitenames = sitenames;
		this.langname = langname;
		this.lanshuxing = lanshuxing;
		this.dizhi = dizhi;
		this.ip = ip;
		this.owner = owner;
		this.fabushijian = fabushijian;
		this.fabuip = fabuip;
		this.fatieren = fatieren;
		this.xingzhi = xingzhi;
		this.yuanbiaoti = yuanbiaoti;
		this.beizhu = beizhu;
		this.bendichuzhi = bendichuzhi;
		this.fengdu = fengdu;
		this.shenpiren = shenpiren;
		this.buyijian = buyijian;
		this.yidichuzhi = yidichuzhi;
		this.yidichuzhitime = yidichuzhitime;
		this.sitemiaoshu = sitemiaoshu;
		this.fendutime = fendutime;
		this.jingwaixinzhi = jingwaixinzhi;
		this.defen = defen;
		this.gongshenpiren = gongshenpiren;
		this.qingkuangbeizhu = qingkuangbeizhu;
		this.jiange = jiange;
		this.leibie = leibie;
		this.kwords = kwords;
		this.bianji = bianji;
		this.shenhe = shenhe;
		this.shenpi = shenpi;
		this.zhuangtai = zhuangtai;
		this.falvguize = falvguize;
		this.wulileibie = wulileibie;
		this.beian = beian;
		this.yhxzbz = yhxzbz;
		this.faburenshudi = faburenshudi;
		this.gajgbaxx = gajgbaxx;
		this.xxly = xxly;
		this.newid = newid;
		this.urldomain = urldomain;
		this.sitetype = sitetype;
		this.hwinfotype = hwinfotype;
		this.attfile = attfile;
		this.dealman = dealman;
		this.attfileas = attfileas;
		this.hechakeywords = hechakeywords;
		this.hwinfoid = hwinfoid;
		this.hechasentence = hechasentence;
	}

	/** full constructor */
	public Hwinfo(String susr, Long sflag, Long rflag, Date stime, String sdanwei, String rdanwei, String wuli, String siteurls, String sitenames, String langname, String lanshuxing,
			String dizhi, String ip, String owner, String fabushijian, String fabuip, String fatieren, String xingzhi, String yuanbiaoti, String beizhu, String bendichuzhi, String fengdu,
			String shenpiren, String buyijian, String yidichuzhi, Date yidichuzhitime, String sitemiaoshu, Date fendutime, String jingwaixinzhi, Long defen, String gongshenpiren,
			String qingkuangbeizhu, String jiange, String leibie, String kwords, String bianji, String shenhe, String shenpi, Long zhuangtai, String falvguize, String wulileibie, String beian,
			String yhxzbz, String faburenshudi, String gajgbaxx, Long xxly, String newid, String urldomain, String sitetype, Long hwinfotype, String attfile, String dealman, String attfileas,
			String hechakeywords, String hwinfoid, String hechasentence) {
		this.susr = susr;
		this.sflag = sflag;
		this.rflag = rflag;
		this.stime = stime;
		this.sdanwei = sdanwei;
		this.rdanwei = rdanwei;
		this.wuli = wuli;
		this.siteurls = siteurls;
		this.sitenames = sitenames;
		this.langname = langname;
		this.lanshuxing = lanshuxing;
		this.dizhi = dizhi;
		this.ip = ip;
		this.owner = owner;
		this.fabushijian = fabushijian;
		this.fabuip = fabuip;
		this.fatieren = fatieren;
		this.xingzhi = xingzhi;
		this.yuanbiaoti = yuanbiaoti;
		this.beizhu = beizhu;
		this.bendichuzhi = bendichuzhi;
		this.fengdu = fengdu;
		this.shenpiren = shenpiren;
		this.buyijian = buyijian;
		this.yidichuzhi = yidichuzhi;
		this.yidichuzhitime = yidichuzhitime;
		this.sitemiaoshu = sitemiaoshu;
		this.fendutime = fendutime;
		this.jingwaixinzhi = jingwaixinzhi;
		this.defen = defen;
		this.gongshenpiren = gongshenpiren;
		this.qingkuangbeizhu = qingkuangbeizhu;
		this.jiange = jiange;
		this.leibie = leibie;
		this.kwords = kwords;
		this.bianji = bianji;
		this.shenhe = shenhe;
		this.shenpi = shenpi;
		this.zhuangtai = zhuangtai;
		this.falvguize = falvguize;
		this.wulileibie = wulileibie;
		this.beian = beian;
		this.yhxzbz = yhxzbz;
		this.faburenshudi = faburenshudi;
		this.gajgbaxx = gajgbaxx;
		this.xxly = xxly;
		this.newid = newid;
		this.urldomain = urldomain;
		this.sitetype = sitetype;
		this.hwinfotype = hwinfotype;
		this.attfile = attfile;
		this.dealman = dealman;
		this.attfileas = attfileas;
		this.hechakeywords = hechakeywords;
		this.hwinfoid = hwinfoid;
		this.hechasentence = hechasentence;
	}


	public Hwinfo(String siteurls, String yuanbiaoti, String sitenames,
			String bianji, String shenhe, String shenpi,
			String attachmentFilenames, String lanshuxing, String beian,
			String gajgbaxx, String fatieren, String fabushijian) {
		this.siteurls=siteurls;
	    this.yuanbiaoti=yuanbiaoti;
	    this.sitenames=sitenames;
	    this.bianji=bianji;
	    this.shenhe=shenhe;
	    this.shenpi=shenpi;
	    this.lanshuxing=lanshuxing;
	    this.beian=beian;
	    this.gajgbaxx=gajgbaxx;
	    this.fatieren=fatieren;
	    this.fabushijian=fabushijian;
	}

	public Long getId() {
		return this.id==null?0:this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSusr() {
		return this.susr;
	}

	public void setSusr(String susr) {
		this.susr = susr;
	}

	public Long getSflag() {
		return this.sflag==null?0:this.sflag;
	}

	public void setSflag(Long sflag) {
		this.sflag = sflag;
	}

	public Long getRflag() {
		return this.rflag==null?0:this.rflag;
	}

	public void setRflag(Long rflag) {
		this.rflag = rflag;
	}

	public Date getStime() {
		return this.stime;
	}

	public void setStime(Date stime) {
		this.stime = stime;
	}

	public String getSdanwei() {
		return this.sdanwei;
	}

	public void setSdanwei(String sdanwei) {
		this.sdanwei = sdanwei;
	}

	public String getRdanwei() {
		return this.rdanwei;
	}

	public void setRdanwei(String rdanwei) {
		this.rdanwei = rdanwei;
	}

	public String getWuli() {
		return this.wuli;
	}

	public void setWuli(String wuli) {
		this.wuli = wuli;
	}

	public String getSiteurls() {
		return this.siteurls;
	}

	public void setSiteurls(String siteurls) {
		this.siteurls = siteurls;
	}

	public String getSitenames() {
		return this.sitenames;
	}

	public void setSitenames(String sitenames) {
		this.sitenames = sitenames;
	}

	public String getLangname() {
		return this.langname;
	}

	public void setLangname(String langname) {
		this.langname = langname;
	}

	public String getLanshuxing() {
		return this.lanshuxing;
	}

	public void setLanshuxing(String lanshuxing) {
		this.lanshuxing = lanshuxing;
	}

	public String getDizhi() {
		return this.dizhi;
	}

	public void setDizhi(String dizhi) {
		this.dizhi = dizhi;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getFabushijian() {
		return this.fabushijian;
	}

	public void setFabushijian(String fabushijian) {
		this.fabushijian = fabushijian;
	}

	public String getFabuip() {
		return this.fabuip;
	}

	public void setFabuip(String fabuip) {
		this.fabuip = fabuip;
	}

	public String getFatieren() {
		return this.fatieren;
	}

	public void setFatieren(String fatieren) {
		this.fatieren = fatieren;
	}

	public String getXingzhi() {
		return this.xingzhi;
	}

	public void setXingzhi(String xingzhi) {
		this.xingzhi = xingzhi;
	}

	public String getYuanbiaoti() {
		return this.yuanbiaoti;
	}

	public void setYuanbiaoti(String yuanbiaoti) {
		this.yuanbiaoti = yuanbiaoti;
	}

	public String getBeizhu() {
		return this.beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public String getBendichuzhi() {
		return this.bendichuzhi;
	}

	public void setBendichuzhi(String bendichuzhi) {
		this.bendichuzhi = bendichuzhi;
	}

	public String getFengdu() {
		return this.fengdu;
	}

	public void setFengdu(String fengdu) {
		this.fengdu = fengdu;
	}

	public String getShenpiren() {
		return this.shenpiren;
	}

	public void setShenpiren(String shenpiren) {
		this.shenpiren = shenpiren;
	}

	public String getBuyijian() {
		return this.buyijian;
	}

	public void setBuyijian(String buyijian) {
		this.buyijian = buyijian;
	}

	public String getYidichuzhi() {
		return this.yidichuzhi;
	}

	public void setYidichuzhi(String yidichuzhi) {
		this.yidichuzhi = yidichuzhi;
	}

	public Date getYidichuzhitime() {
		return this.yidichuzhitime;
	}

	public void setYidichuzhitime(Date yidichuzhitime) {
		this.yidichuzhitime = yidichuzhitime;
	}

	public String getSitemiaoshu() {
		return this.sitemiaoshu;
	}

	public void setSitemiaoshu(String sitemiaoshu) {
		this.sitemiaoshu = sitemiaoshu;
	}

	public Date getFendutime() {
		return this.fendutime;
	}

	public void setFendutime(Date fendutime) {
		this.fendutime = fendutime;
	}

	public String getJingwaixinzhi() {
		return this.jingwaixinzhi;
	}

	public void setJingwaixinzhi(String jingwaixinzhi) {
		this.jingwaixinzhi = jingwaixinzhi;
	}

	public Long getDefen() {
		return this.defen==null?0:this.defen;
	}

	public void setDefen(Long defen) {
		this.defen = defen;
	}

	public String getGongshenpiren() {
		return this.gongshenpiren;
	}

	public void setGongshenpiren(String gongshenpiren) {
		this.gongshenpiren = gongshenpiren;
	}

	public String getQingkuangbeizhu() {
		return this.qingkuangbeizhu;
	}

	public void setQingkuangbeizhu(String qingkuangbeizhu) {
		this.qingkuangbeizhu = qingkuangbeizhu;
	}

	public String getJiange() {
		return this.jiange;
	}

	public void setJiange(String jiange) {
		this.jiange = jiange;
	}

	public String getLeibie() {
		return this.leibie;
	}

	public void setLeibie(String leibie) {
		this.leibie = leibie;
	}

	public String getKwords() {
		return this.kwords;
	}

	public void setKwords(String kwords) {
		this.kwords = kwords;
	}

	public String getBianji() {
		return this.bianji;
	}

	public void setBianji(String bianji) {
		this.bianji = bianji;
	}

	public String getShenhe() {
		return this.shenhe;
	}

	public void setShenhe(String shenhe) {
		this.shenhe = shenhe;
	}

	public String getShenpi() {
		return this.shenpi;
	}

	public void setShenpi(String shenpi) {
		this.shenpi = shenpi;
	}

	public Long getZhuangtai() {
		return this.zhuangtai==null?0:this.zhuangtai;
	}

	public void setZhuangtai(Long zhuangtai) {
		this.zhuangtai = zhuangtai;
	}

	public String getFalvguize() {
		return this.falvguize;
	}

	public void setFalvguize(String falvguize) {
		this.falvguize = falvguize;
	}

	public String getWulileibie() {
		return this.wulileibie;
	}

	public void setWulileibie(String wulileibie) {
		this.wulileibie = wulileibie;
	}

	public String getBeian() {
		return this.beian;
	}

	public void setBeian(String beian) {
		this.beian = beian;
	}

	public String getYhxzbz() {
		return this.yhxzbz;
	}

	public void setYhxzbz(String yhxzbz) {
		this.yhxzbz = yhxzbz;
	}

	public String getFaburenshudi() {
		return this.faburenshudi;
	}

	public void setFaburenshudi(String faburenshudi) {
		this.faburenshudi = faburenshudi;
	}

	public String getGajgbaxx() {
		return this.gajgbaxx;
	}

	public void setGajgbaxx(String gajgbaxx) {
		this.gajgbaxx = gajgbaxx;
	}

	public Long getXxly() {
		return this.xxly==null?0:this.xxly;
	}

	public void setXxly(Long xxly) {
		this.xxly = xxly;
	}

	public String getNewid() {
		return this.newid;
	}

	public void setNewid(String newid) {
		this.newid = newid;
	}

	public String getUrldomain() {
		return this.urldomain;
	}

	public void setUrldomain(String urldomain) {
		this.urldomain = urldomain;
	}

	public String getSitetype() {
		return this.sitetype;
	}

	public void setSitetype(String sitetype) {
		this.sitetype = sitetype;
	}

	public Long getHwinfotype() {
		return this.hwinfotype==null?0:this.hwinfotype;
	}

	public void setHwinfotype(Long hwinfotype) {
		this.hwinfotype = hwinfotype;
	}

	public String getAttfile() {
		return this.attfile;
	}

	public void setAttfile(String attfile) {
		this.attfile = attfile;
	}

	public String getDealman() {
		return this.dealman;
	}

	public void setDealman(String dealman) {
		this.dealman = dealman;
	}

	public String getAttfileas() {
		return this.attfileas;
	}

	public void setAttfileas(String attfileas) {
		this.attfileas = attfileas;
	}

	public String getHechakeywords() {
		return this.hechakeywords;
	}

	public void setHechakeywords(String hechakeywords) {
		this.hechakeywords = hechakeywords;
	}

	public String getHwinfoid() {
		return this.hwinfoid;
	}

	public void setHwinfoid(String hwinfoid) {
		this.hwinfoid = hwinfoid;
	}

	public String getHechasentence() {
		return this.hechasentence;
	}

	public void setHechasentence(String hechasentence) {
		this.hechasentence = hechasentence;
	}

	public Integer getIscommit() {
		return iscommit==null?0:this.iscommit;
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

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

	public Integer getIscommend() {
		return iscommend;
	}

	public void setIscommend(Integer iscommend) {
		this.iscommend = iscommend;
	}
	public Integer getCutImageResult() {
		return cutImageResult;
	}
	public void setCutImageResult(Integer cutImageResult) {
		this.cutImageResult = cutImageResult;
	}

	public Integer getIpAnalyResult() {
		return ipAnalyResult;
	}

	public void setIpAnalyResult(Integer ipAnalyResult) {
		this.ipAnalyResult = ipAnalyResult;
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

	public Integer getCutImgFlag() {
		return cutImgFlag;
	}

	public void setCutImgFlag(Integer cutImgFlag) {
		this.cutImgFlag = cutImgFlag;
	}

	public Integer getAttfileFlag() {
		return attfileFlag;
	}

	public void setAttfileFlag(Integer attfileFlag) {
		this.attfileFlag = attfileFlag;
	}

	public List<String> getUrlList() {
		return urlList;
	}

	public void setUrlList(List<String> urlList) {
		this.urlList = urlList;
	}

	public String getAttachIds() {
		return attachIds;
	}

	public void setAttachIds(String attachIds) {
		this.attachIds = attachIds;
	}
	
}
