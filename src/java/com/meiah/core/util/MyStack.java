/** * A级 */
package com.meiah.core.util;

public class MyStack {   
    private int maxSize;//栈的最大容量   
    private String[] ch;  //栈的数据   
    private int top;    //栈头标记   
  
    public MyStack(int s) {   
        maxSize = s;   
        ch = new String[s];   
        top = -1;   
    }   
  
    public void push(String c) {//入栈   
        ch[++top] = c;   
    }   
    public void push(char c){
    	ch[++top] = c+"";
    }
  
    public String pop() {//出栈   
    	if(top < 0 ){
    		return "";
    	}
        return ch[top--];   
    }   
  
    public String peek() {   
        return ch[top];   
    }   
  
    public boolean isEmpty() {   
        return top == -1;   
    }   
  
    public boolean isFull() {   
        return top == (maxSize - 1);   
    }   
  
    public int size() {   
        return top + 1;   
    }   
  
    public String get(int index) {   
        return ch[index];   
    }   
}  
