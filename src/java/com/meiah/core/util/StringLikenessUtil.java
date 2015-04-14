/** * A级 */
package com.meiah.core.util;


/**
 * 编辑距离的两字符串相似度
 * 
 * @author duansx
 * @email duansx@mail.pico.net
 * @version 2012-2-20
 */
public class StringLikenessUtil {

	public static double similarTo(String str1, String str2) {
		str1 = (str1 == null) ? "" : str1;
		str2 = (str2 == null) ? "" : str2;
		int n = str1.length();
		int m = str2.length();

		if (n == 0 ||m==0) {
			return 0;
		}
		
		int p[] = new int[n];
		int d[] = new int[n];
		int _d[];
		int i;
		int j;

		char t_j;
		int cost; // cost
		for (i = 0; i < n; i++) {
			p[i] = i;
		}
		for (j = 1; j < m; j++) {
//			for(int Q=0;Q<n;Q++){
//				System.out.print(p[Q]+"\t");
//			}System.out.println();
			t_j = str2.charAt(j - 1);
			d[0] = j;
			for (i = 1; i < n; i++) {
				cost = str1.charAt(i - 1) == t_j ? 0 : 1;
				d[i] = Math.min(Math.min(d[i - 1] + 1, p[i] + 1), p[i - 1] + cost);
			}
			_d = p;
			p = d;
			d = _d;
		}

		int ld = p[n - 1];
//		System.out.println(ld);
		// 计算相似度
		double similarity = 1 - (double) ld / Math.max(str1.length(), str2.length());
//		System.out.println(similarity);
		return similarity;
	}

	/**
	 * 对比两个字符串是否相似
	 * 
	 * @param str1
	 * @param str2
	 */
	public static boolean isSame(String str1, String str2, double pointLike) {
		return similarTo(str1, str2) >= pointLike;
	}
public static void main(String[] args) {
	boolean same = StringLikenessUtil.isSame("nothingis2014非农学生的心声谁来替我们做主高高兴", "非农学生的心声谁来替我们做主高高兴兴考上大学了风风光光迁走了", 0.55D);
	System.out.println(same);
}
}
