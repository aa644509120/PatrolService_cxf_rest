/** * A级 */
package com.meiah.core.util;


/**
 * 中缀表达式转后缀表达式,带中缀表达式格式校验,仅支持()+|四个符号及转义符\
 * @author lvjs
 */

public class InfixToSuffix  {
	private MyStack operStack;// 操作符栈
	private MyStack outputStack;//后缀表达式栈
	private String input;// 输入中缀表达式
	private String ERROR;//错误信息
	private String info;
	private int index;

	public InfixToSuffix(String input) {
		this.input = input;
		int size = input.length();
		operStack = new MyStack(size);
		outputStack = new MyStack(size);
	}
	
	public String doIfError(String input){
		input = input.replaceAll("\\|", " ").replaceAll("\\+", " ")
		.replaceAll("\\(", " ").replaceAll("\\)", " ").replaceAll(
				"(^([\\s]+))|(([\\s]+)$)", "").replaceAll("[\\s]+", "\\|");
		InfixToSuffix its = new InfixToSuffix(input);
		return its.doTrans();
	}

	public String doTrans() {
		int isKeyAfterOper = -1;//0为关键词,1为符号,2为左括号,3为右括号
		for (int i = 0; i < input.length(); i++) {
			if(ERROR!=null){
				//System.out.println(ERROR);
				//return ERROR;
				return doIfError(input);
			}
			char ch = input.charAt(i);
			index = i;
			
			switch (ch) {
			case '+':
				if(isKeyAfterOper==1){
					setERROR("符号前面不能为符号!");
				}else if(isKeyAfterOper == 2){
					setERROR("符号前面不能为左括号!");
				}else if(isKeyAfterOper == -1){
					setERROR("符号不能放在开头!");
				}
				getOper(ch,2);
				isKeyAfterOper = 1;
				break;
			case '|':
				if(isKeyAfterOper==1){
					setERROR("符号前面不能为符号!");
				}else if(isKeyAfterOper == 2){
					setERROR("符号前面不能为左括号!");
				}else if(isKeyAfterOper == -1){
					setERROR("符号不能放在开头!");
				}
				getOper(ch,1);
				isKeyAfterOper = 1;
				break;
			case '(':
				if(isKeyAfterOper==0){
					setERROR("左括号前面不能为关键词!");
				}else if(isKeyAfterOper==3){
					setERROR("左括号前面不能为右括号!");
				}
				operStack.push(ch);
				isKeyAfterOper = 2;
				break;
			case ')':
				if(isKeyAfterOper==1){
					setERROR("右括号前面不能为符号!");
				}else if(isKeyAfterOper==2){
					setERROR("右括号前面不能为左括号!");
				}else if(isKeyAfterOper == -1){
					setERROR("右括号不能放在开头!");
				}
				getParent(ch);
				isKeyAfterOper = 3;
				break;
			default:
				if(isKeyAfterOper==3){
					setERROR("关键词前面不能为右括号!");
				}
			
				if(isKeyAfterOper!=0){
					outputStack.push(ch);
					isKeyAfterOper = 0;
				}else
					outputStack.push(outputStack.pop() + ch);
				break;
			}
		}
		while (!operStack.isEmpty()) {
			String a = outputStack.pop();
			String b = outputStack.pop();
			if(a.trim().isEmpty()|| b.trim().isEmpty()){
				resetERROR("表达式出错,缺失右括号!");
				return doIfError(input);
			}else{
				outputStack.push(b + " "+ a + " "+ operStack.pop());
			}
		}
		if(outputStack.size() > 1){
			resetERROR("表达式出错!"+input);
			return doIfError(input);
		}
		
		return outputStack.pop();
	}

	public void getParent(char ch) {
		if(!operStack.isEmpty()&&"(".equals(operStack.peek())){
			this.setInfo("关键词:"+outputStack.peek()+"的左右两边括号可以去掉!");
		}
		while (true) {
			if(operStack.isEmpty()){
				resetERROR("表达式出错,缺失左括号!");
				break;
			}
			String chx = operStack.pop();
			if ("(".equals(chx)) {
				return;
			} else {
				String a = outputStack.pop();
				String b = outputStack.pop();
				if(a.trim().isEmpty()||a.trim().isEmpty()){
					resetERROR("表达式出错!"+input);
				}else{
					outputStack.push(b + " "+ a + " "+ chx);
				}
			}
		}
	}

	public void getOper(char ch,int level) {
		while (!operStack.isEmpty()) {
			String operTop = operStack.pop();
			if ("(".equals(operTop)) {
				operStack.push(operTop);
				break;
			} else {
				String a = outputStack.pop();
				String b = outputStack.pop();
				if("".equals(a) || "".equals(b)){
					resetERROR("表达式出错!"+input);
					break;
				}else{
					outputStack.push(b +" "+ a +" " + operTop);
				}
			}
		}
		operStack.push(ch);
	}
	
	public String getERROR() {
		return ERROR;
	}

	private void setERROR(String msg){
		ERROR = "表达式出错,index="+(index+1)+","+msg;
	}
	private void resetERROR(String msg){
		ERROR = msg;
	}
	public void setInfo(String info){
		this.info = "提示:"+info;
	}
	public String getInfo(){
		return this.info;
	}
	//
	public static void main(String args[]) {
		//输入:input不能为空,全角符号转为半角"+|()",去除所有空格.
		//返回值为后缀表达式,如果出错则将所有关键词按或逻辑.
		//可以通过getERROR获得出错信息,getInfo获得提示信息.
		String input, output;
		//input = "((AB|C))&C&AC";
		input = "(非法添加 |食品安全问题 |监管| ((滥用)+(染色馒头 |毒奶粉| 染色生姜| 假牛肉| 毒豆芽食品添加剂| 瘦肉精 |瘦肉精 添加剂)";
		System.out.println(input);
		InfixToSuffix itp = new InfixToSuffix(input);
		
		output = itp.doTrans();
		System.out.println("Profix is " + output);
		System.out.println(itp.getERROR());
		System.out.println(itp.getInfo());
	}
}
