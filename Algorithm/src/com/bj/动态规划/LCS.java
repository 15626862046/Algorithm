package com.bj.动态规划;

/**
 * 最长公共子序列 
 * 字串是连续的子序列
 * 
 * @author hee 
 * 假设dp(i, j) 是【nums1前i 个元素】与【nums2前j个元素】的最长公共子序列长度
 * dp(i, 0)、dp(0,j) 初始值均为0 
 * 如果nums1[i–1] = nums2[j–1]，那么dp(i, j) = dp(i–1, j–1) + 1
 * 如果nums1[i–1] ≠nums2[j–1]，那么dp(i, j) = max { dp(i–1, j), dp(i, j–1) }
 */
public class LCS {
	public static void main(String[] args) {
		int len = lcs(new int[] {1, 3, 5, 9, 10}, new int[] {1, 4, 9, 10});
		System.out.println(len);
	}
	static int lcs1(int[] nums1, int[] nums2) {
		if (nums1 == null || nums1.length == 0)
			return 0;
		if (nums2 == null || nums2.length == 0)
			return 0;
		return lcs1(nums1, nums1.length, nums2, nums2.length);
	}

	/**
	 * 求nums1前i个元素和nums2前j个元素的最长公共子序列长度 递归实现--重复递归调用
	 * 
	 * @param nums1
	 * @param i
	 * @param nums2
	 * @param j
	 */
	static int lcs1(int[] nums1, int i, int[] nums2, int j) {
		if (i == 0 || j == 0)
			return 0;
		if (nums1[i - 1] != nums2[j - 1]) {
			return Math.max(lcs1(nums1, i - 1, nums2, j), lcs1(nums1, i, nums2, j - 1));
		}
		return lcs1(nums1, i - 1, nums2, j - 1) + 1;
	}
	

	/**
	 * 非递归实现
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	static int lcs2(int[] nums1, int[] nums2) {
		if (nums1 == null || nums1.length == 0) return 0;
		if (nums2 == null || nums2.length == 0) return 0;
		int dp[][] = new int[nums1.length + 1][nums2.length + 1];
		for (int i = 1; i <= nums1.length; i++) {
			for (int j = 1; j <= nums2.length; j++) {
				if(nums1[i-1] == nums2[j-1]) {
					dp[i][j] = dp[i-1][j-1] +1;
				}else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		return dp[nums1.length][nums2.length];
	}
	
	/**
	 * 非递归实现–滚动数组
	 * 可以使用滚动数组优化空间复杂度
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	static int lcs3(int[] nums1, int[] nums2) {
		if (nums1 == null || nums1.length == 0) return 0;
		if (nums2 == null || nums2.length == 0) return 0;
		int dp[][] = new int[2][nums2.length + 1];
		for (int i = 1; i <= nums1.length; i++) {
			int row = i & 1;//%2的位运算优化
			int prevRow = (i-1)&1;
			for (int j = 1; j <= nums2.length; j++) {
				if(nums1[i-1] == nums2[j-1]) {
				dp[row][j] = dp[prevRow][j-1]+1;
				}else {
					dp[row][j] = Math.max(dp[prevRow][j], dp[row][j-1]);
				}
			}
		}
		return dp[nums1.length & 1][nums2.length];
	}

	/**
	 * 非递归实现–一维数组
	 * 可以将二维数组优化成一维数组，进一步降低空间复杂度
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	static int lcs4(int[] nums1, int[] nums2) {
		if (nums1 == null || nums1.length == 0) return 0;
		if (nums2 == null || nums2.length == 0) return 0;
		int dp[] = new int[nums2.length + 1];
		for (int i = 1; i <= nums1.length; i++) {
			int cur=0;
			for (int j = 1; j <= nums2.length; j++) {
				int leftTop = cur;
				cur=dp[j];
				if(nums1[i-1] == nums2[j-1]) {
				dp[j] = leftTop+1;
				}else {
					dp[j] = Math.max(dp[j], dp[j-1]);
				}
			}
		}
		return dp[nums2.length];
	}
	
	/**
	 * 最长公共子序列–非递归实现–一维数组
	 * 可以空间复杂度优化至Ok,k=min{n,m}
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	static int lcs(int[] nums1, int[] nums2) {
		if (nums1 == null || nums1.length == 0) return 0;
		if (nums2 == null || nums2.length == 0) return 0;
		int[] rowsNums = nums1,colsNums = nums2;
		if(nums1.length<nums2.length) {
			colsNums = nums1;
			rowsNums = nums2;
		}
		int[]dp = new int[colsNums.length+1];
		for (int i = 1; i <= rowsNums.length; i++) {
			int cur=0;
			for (int j = 1; j <= colsNums.length; j++) {
				int leftTop = cur;
				cur = dp[j];
				if(rowsNums[i-1] == colsNums[j-1]) {
					dp[j]=leftTop+1;
				}else {
					dp[j] = Math.max(dp[j], dp[j-1]);
				}
			}
		}
		return dp[colsNums.length];
	}
	/**
	 * 最长公共子序列--字符串
	 * @param text1
	 * @param text2
	 * @return
	 */
	public int longestCommonSubsequence(String text1, String text2) {
		if (text1 == null || text2 == null) return 0;
		char[] chars1 = text1.toCharArray();  
		if (chars1.length == 0) return 0;//将字符串转换为字符数组
		char[] chars2 = text2.toCharArray();  
		if (chars2.length == 0) return 0;
		char[] rowsChars = chars1, colsChars = chars2;
		if (chars1.length < chars2.length) {
			colsChars = chars1;
			rowsChars = chars2;
		}
		int[] dp = new int[colsChars.length + 1];
		for (int i = 1; i <= rowsChars.length; i++) {
			int cur = 0;
			for (int j = 1; j <= colsChars.length; j++) {
				int leftTop = cur;
				cur = dp[j];
				if (rowsChars[i - 1] == colsChars[j - 1]) {
					dp[j] = leftTop + 1;
				} else {
					dp[j] = Math.max(dp[j], dp[j - 1]);
				}
			}
		}
		return dp[colsChars.length];
    }
    
}
