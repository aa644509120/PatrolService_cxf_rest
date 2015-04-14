/** * A级 */
/**  
* @Title MathUtil.java  
* @Package com.meiah.core.util  
* @Description  
* @author "zhangshaofeng"  
* @time Oct 20, 2011 2:50:24 PM  
*/ 


package com.meiah.core.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author "zhangshaofeng"
 *
 */
public class MathUtil {

    /** 
     * 两个字符类型的小数进行相加为a+b; 
     *  
     * @param a 
     * @param b 
     * @return 
     */  
    public String addBigDecimal(String a, String b) {  
        double a1 = Double.parseDouble(a);  
        double b1 = Double.parseDouble(b);  
        BigDecimal a2 = BigDecimal.valueOf(a1);  
        BigDecimal b2 = BigDecimal.valueOf(b1);  
        BigDecimal c2 = a2.add(b2);  
        String c1 = c2 + "";  
        return c1;  
    }  
    
    /** 
     * 两个字符类型的小数进行相减为a-b; 
     *  
     * @param a 
     * @param b 
     * @return 
     */  
    public String reduceBigDecimal(String a, String b) {  
        double a1 = Double.parseDouble(a);  
        double b1 = Double.parseDouble(b);  
        BigDecimal a2 = BigDecimal.valueOf(a1);  
        BigDecimal b2 = BigDecimal.valueOf(b1);  
        BigDecimal c2 = a2.subtract(b2);  
        String c1 = c2 + "";  
        return c1;  
    }  
    
    
    /** 
     * 两个字符类型的数相除 a/b=c； 
     *  
     * @param a 
     * @param b 
     * @return 
     */  
    public String divideString(String a, String b) {  
        double a1 = Double.parseDouble(a);  
        double b1 = Double.parseDouble(b);  
        BigDecimal a2 = BigDecimal.valueOf(a1);  
        BigDecimal b2 = BigDecimal.valueOf(b1);  
        BigDecimal c2 = a2.divide(b2,a2.scale());  
        String c1 = c2 + "";  
        return c1;  
    } 
    
    /** 
     * 两个字符类型的小数进行比较 a<b=-1,a==b=0,a>b = 1 
     *  
     * @param a 
     * @param b 
     * @return 
     */  
    public int compareString(String a, String b) {  
        double a1 = Double.parseDouble(a);  
        double b1 = Double.parseDouble(b);  
        BigDecimal a2 = BigDecimal.valueOf(a1);  
        BigDecimal b2 = BigDecimal.valueOf(b1);  
        int c = a2.compareTo(b2);  
        return c;  
    }
    
    /**
     * 比较两个double大小
     * @param a
     * @param b
     * @return
     */
    public static int compareDouble(double a,double b){
    	BigDecimal   data1   =   new   BigDecimal(a);
    	BigDecimal   data2   =   new   BigDecimal(b);
    	return data1.compareTo(data2); 
    }
    
    /**
     * 分隔任意输入数字的整数与小数部分
     * @param a
     * @return
     */
    public static String breakFloat(double a ,double b ){
    	double c = a/b;
    	DecimalFormat d = new DecimalFormat("#.##");
    	String result = "";
    	double data = Double.parseDouble(d.format(c));
    	int Int = (int)data;
    	double digitls = data-Int;
    	result = Int+" "+d.format(digitls);
    	return result;
    }
    
}
