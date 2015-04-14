/** * A级 */
package com.meiah.entity.task;

import java.util.List;

public class KeyJson {
// 	{
// 		 * 		type:"add",        ---其中type为操作类型，add为新增、del为删除、edit为修改
// 		 * 		total:18,		   ---其中total为本次操作受影响的关键词总数
// 		 * 		userid:["1","2","3"],	   ---userid为共享对象的用户id或者其他的用户唯一标识，不限制类型
// 		 * 		keys:[			   		   ---keys为操作的关键词数字
// 		 * 				{"key":"暴力","type":"涉及警察","uuid":"121xc234"}, --key为关键词 ，type为关键词类别 ，uuid为关键词的唯一标识
// 		 * 				{"key":"反恐","type":"涉及警察","uuid":"121xc235"}
// 		 * 			 ]
// 		 *  }
	
	private String type;
	private int total;
	private List<String> userid;
	private List<Key> keys;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<String> getUserid() {
		return userid;
	}
	public void setUserid(List<String> userid) {
		this.userid = userid;
	}
	public List<Key> getKeys() {
		return keys;
	}
	public void setKeys(List<Key> keys) {
		this.keys = keys;
	}
	
}
