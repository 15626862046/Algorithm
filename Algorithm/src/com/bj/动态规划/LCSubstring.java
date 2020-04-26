package com.bj.动态规划;
/**
 * 最长公共字串
 * 字串是连续的子序列
 * @author hee
 * 
 *假设2 个字符串分别是str1、str2i∈ [1, str1.length]j∈ [1, str2.length]
 *
 *假设dp(i, j) 是以str1[i–1]、str2[j–1] 结尾的最长公共子串长度
 *
 *dp(i, 0)、dp(0, j) 初始值均为0
 *如果str1[i–1] = str2[j–1]，那么dp(i, j) = dp(i–1, j–1) + 1
 *如果str1[i–1] ≠str2[j–1]，那么dp(i, j) = 0
 *
 *最长公共子串的长度是所有dp(i, j) 中的最大值max { dp(i, j) }
 */
public class LCSubstring {
	public static void main(String[] args) {
		System.out.println(lcs("ABDCBA", "ABBA"));
	}
	static int lcs1(String str1, String str2) {
		if(str1 == null || str2 == null) return 0;
		char[] cs1 = str1.toCharArray();
		if(cs1.length == 0) return 0;
		char[] cs2 = str2.toCharArray();
		if(cs2.length == 0) return 0;
		int dp[][] = new int[cs1.length+1][cs2.length+1];
		int max = 0;
		for (int i = 1; i <= cs1.length; i++) {
			for (int j = 1; j <= cs2.length; j++) {
				if(cs1[i-1] != cs2[j-1]) continue;
				dp[i][j] = dp[i-1][j-1] +1;
				max = Math.max(max, dp[i][j]);
			}
		}
		return max;
	}
	
	/**
	 * 一维数组实现
	 * @param str1
	 * @param str2
	 * @return
	 */
	static int lcs2(String str1, String str2) {
		if (str1 == null || str2 == null) return 0;
		char[] chars1 = str1.toCharArray();
		if (chars1.length == 0) return 0;
		char[] chars2 = str2.toCharArray();
		if (chars2.length == 0) return 0;
		char[] rowsChars = chars1, colsChars = chars2;
		if (chars1.length < chars2.length) {
			colsChars = chars1;
			rowsChars = chars2;
		}
		int[] dp = new int[colsChars.length + 1];
		int max = 0;
		for (int row = 1; row <= rowsChars.length; row++) {
			int cur = 0;
			for (int col = 1; col <= colsChars.length; col++) {
				int leftTop = cur;
				cur = dp[col];
				if(chars1[row-1] != chars2[col-1]) {
					dp[col] = 0;
				}else {
					dp[col] = leftTop+1;
					max = Math.max(dp[col], max);
				}
			}
		}
		return max;
	}
	
	/**
	 * 一维数组倒序优化实现
	 * @param str1
	 * @param str2
	 * @return
	 */
	static int lcs(String str1, String str2) {
		if (str1 == null || str2 == null) return 0;
		char[] chars1 = str1.toCharArray();
		if (chars1.length == 0) return 0;
		char[] chars2 = str2.toCharArray();
		if (chars2.length == 0) return 0;
		char[] rowsChars = chars1, colsChars = chars2;
		if (chars1.length < chars2.length) {
			colsChars = chars1;
			rowsChars = chars2;
		}
		
		int[] dp = new int[colsChars.length + 1];
		int max = 0;
		for (int row = 1; row <= rowsChars.length; row++) {
			for (int col = colsChars.length; col >=1; col--) {
				if(chars1[row-1] != chars2[col-1]) {
					dp[col] =0;
				}else {
					dp[col] = dp[col-1]+1;
					max = Math.max(max, dp[col]);
				}
			}
		}
		return max;
	}
	
}
