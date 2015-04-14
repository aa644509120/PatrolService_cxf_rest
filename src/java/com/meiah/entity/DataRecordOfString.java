/** * A级 */
package com.meiah.entity;

import java.util.ArrayList;
import java.util.List;
/**
 * 返回字符串的结果集
 * @author empty
 */
public class DataRecordOfString {
	private int i_total;//结果总数
	private List<String> m_item = new ArrayList<String>();//字符串结果集合
	public DataRecordOfString(){};
	public DataRecordOfString(int iTotal, List<String> mItem) {
		i_total = iTotal;
		m_item = mItem;
	}
	public List<String> getM_item() {
		return m_item;
	}
	public void setM_item(List<String> mItem) {
		m_item = mItem;
	}
	public int getI_total() {
		return i_total;
	}
	public void setI_total(int iTotal) {
		i_total = iTotal;
	}
}
