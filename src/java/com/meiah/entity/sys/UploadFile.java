/** * A级 */
package com.meiah.entity.sys;

/**
 * UploadFile entity. @author MyEclipse Persistence Tools
 * 
 * 系统上传附件统一存放的临时表
 *
 * @since 2012-9-19下午07:07:37
 */
public class UploadFile implements java.io.Serializable {

	private static final long serialVersionUID = -3872358810880581236L;
	private Long id;
	private String filename;
	private String filepath;
	private Long filesize;
	private Long infoid;
	private Integer type;
	private Integer extend1;
	private String isimg;

	// Constructors

	/** default constructor */
	public UploadFile() {
	}

	/** full constructor */
	public UploadFile(String filename, String filepath, Long filesize, Long infoid, Integer type, Integer extend1) {
		this.filename = filename;
		this.filepath = filepath;
		this.filesize = filesize;
		this.infoid = infoid;
		this.type = type;
		this.extend1 = extend1;
	}
	
	/** full constructor */
	public UploadFile(String filename, String filepath, Long filesize, Long infoid, Integer type, Integer extend1,String isimg) {
		this.filename = filename;
		this.filepath = filepath;
		this.filesize = filesize;
		this.infoid = infoid;
		this.type = type;
		this.extend1 = extend1;
		this.isimg = isimg;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return this.filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Long getFilesize() {
		return this.filesize;
	}

	public void setFilesize(Long filesize) {
		this.filesize = filesize;
	}

	public Long getInfoid() {
		return this.infoid;
	}

	public void setInfoid(Long infoid) {
		this.infoid = infoid;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getExtend1() {
		return this.extend1;
	}

	public void setExtend1(Integer extend1) {
		this.extend1 = extend1;
	}

	public String getIsimg() {
		return isimg;
	}

	public void setIsimg(String isimg) {
		this.isimg = isimg;
	}

}
