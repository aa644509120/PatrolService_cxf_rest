/** * A级 */
package com.meiah.core.orm;

import java.util.List;

/**
 * 封装分页和排序参数及分页查询结果.
 * @author zengxb
 * @param <T> Page中的记录类型
 */
public class Page<T> {

	private int pageNo = 1;

	private int pageSize = -1;

	private String orderBy = "id";

	private boolean asc = false;

	
	private boolean autoCount = false;

	private List<T> result = null;

	private int totalCount = 0;
	
	private int limitPageCount = 999999999;

	private int pageNumber = 0;  //总页数
	
	private float qtime;  		 //查询反馈时间，主要是综合查询使用
	
	private String preQueryUrl;
	
	private boolean useUserSetting = false;	//读取用户配置的页码，默认为true
	
	public Page() {
		// TODO Auto-generated constructor stub
	}
	
	public Page(int pageSize, boolean autoCount) {
		this.autoCount = autoCount;
		this.pageSize = pageSize;
	}
	
	public Page(int pageSize, boolean autoCount,boolean useUserSetting) {
		this.autoCount = autoCount;
		this.pageSize = pageSize;
		this.useUserSetting=useUserSetting;
	}

	public Page(int pageSize, boolean autoCount,int limitPageCount) {
		this.autoCount = autoCount;
		this.pageSize = pageSize;
		this.limitPageCount = limitPageCount;
	}

	/**
	 * 每页的记录数量，无默认20条.
	 */
	public int getPageSize() {
		return pageSize;
	}

	public boolean isPageSizeSetted() {
		return pageSize > -1;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 当前页的页号,序号.
	 */
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		if(pageNo>this.limitPageCount) pageNo = this.limitPageCount;
		this.pageNo = pageNo;
	}

	/**
	 * 第一条记录在结果集中的位置,序号�?0�?�?.
	 */
	public int getFirst() {
		if (pageNo < 1 || pageSize < 1)
			return -1;
		else
			return ((pageNo - 1) * pageSize);
	}

	public int getLast() {
		return (pageNo * pageSize);
	}
	
	public boolean isFirstSetted() {
		return (pageNo > 0 && pageSize > 0);
	}

	/**
	 * 单个的排序字�?.
	 */
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	public void setDefaultOrderBy(String orderBy, boolean asc) {
		if(this.orderBy.equals("id")){
			this.orderBy = orderBy;
			this.asc = asc;
		}
	}

	public boolean isOrderBySetted() {
		return this.orderBy != null;
	}

	/**
	 * 是否升序，默认为true.
	 */
	public boolean isAsc() {
		return asc;
	}

	/**
	 * 是否升序，默认为true.
	 */
	public boolean getAsc() {
		return asc;
	}
	
	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	/**
	 * 是否自动获取总页数,默认为false,query by HQL时本属性无�?.
	 */
	public boolean isAutoCount() {
		if(!autoCount) return false;
		if(this.getTotalCount() == 0) return true;
		return false;
	}

	public void setAutoCount(boolean autoCount) {
		this.autoCount = autoCount;
	}

	/**
	 * 页内的数据列�?.
	 */
	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	/**
	 * 总记录数.
	 */
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 计算总页�?.
	 */
	public int getTotalPages() {
		if (totalCount == -1)
			return -1;

		int count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count++;
		}
		if(count > this.limitPageCount) count = this.limitPageCount;
		pageNumber =count;
		return count;
	}

	/**
	 * 是否还有下一�?.
	 */
	public boolean isHasNext() {
		return (pageNo + 1 <= getTotalPages());
	}

	/**
	 * 返回下页的页�?,序号�?1�?�?.
	 */
	public int getNextPage() {
		if (isHasNext())
			return pageNo + 1;
		else
			return pageNo;
	}

	/**
	 * 是否还有上一�?. 
	 */
	public boolean isHasPre() {
		return (pageNo - 1 >= 1);
	}

	/**
	 * 返回上页的页�?,序号�?1�?�?.
	 */
	public int getPrePage() {
		if (isHasPre())
			return pageNo - 1;
		else
			return pageNo;
	}
	
	public int getLimitPageCount() {
		return limitPageCount;
	}

	public void setLimitPageCount(int limitPageCount) {
		this.limitPageCount = limitPageCount;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public float getQtime() {
		return qtime;
	}

	public void setQtime(float qtime) {
		this.qtime = qtime;
	}

	public String getPreQueryUrl() {
		return preQueryUrl;
	}

	public void setPreQueryUrl(String preQueryUrl) {
		this.preQueryUrl = preQueryUrl;
	}
	
	public boolean isUseUserSetting() {
		return useUserSetting;
	}

	public void setUseUserSetting(boolean useUserSetting) {
		this.useUserSetting = useUserSetting;
	}
}
