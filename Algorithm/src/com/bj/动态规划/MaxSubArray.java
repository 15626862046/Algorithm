package com.bj.动态规划;
/**
 * 最大连续子序列和–动态规划
 * @author hee
 *
 */
public class MaxSubArray {

	public static void main(String[] args) {
		System.out.println(maxSubArray2(new int[] {-2,1,-3,4,-1,2,1,-5,4}));

	}

	/**
	 * 最大连续子序列和–动态规划–实现
	 * 
	 * 状态定义
	 * 假设dp(i) 是以nums[i] 结尾的最大连续子序列和（nums是整个序列）
	 * 
	 * 状态转移方程
	 * 如果dp(i –1) ≤0，那么dp(i) = nums[i]
	 * 如果dp(i –1) > 0，那么dp(i) = dp(i –1) + nums[i]
	 * 
	 * 初始状态
	 * dp(0) 的值是nums[0]
	 * 
	 * 最终的解
	 * 最大连续子序列和是所有dp(i) 中的最大值max { dp(i) }，i ∈ [0, nums.length) 
	 */
	static int maxSubArray1(int[] nums) {
		if(nums == null || nums.length ==0) return 0;
		int [] dp = new int[nums.length];
		int max = dp[0] = nums[0];//初始状态
		for (int i = 1; i < dp.length; i++) {
			int prev = dp[i-1];
			if(prev >0) {
				dp[i] = prev + nums[i];//如果dp(i –1) > 0，那么dp(i) = dp(i –1) + nums[i]
			}else {
				dp[i] = nums[i];// 如果dp(i –1) ≤0，那么dp(i) = nums[i]
			}
			max = Math.max(max, dp[i]);
		}
		return max;
	}
	
	/**
	 * 最大连续子序列和–动态规划–优化实现
	 * 优化空间
	 */
	static int maxSubArray2(int[] nums) {
		if(nums == null || nums.length ==0) return 0;
		int dp = nums[0];//初始状态
		int max = dp;
		for (int i = 1; i < nums.length; i++) {
			if(dp >0) {
				dp = dp +nums[i];
			}else {
				dp = nums[i];
			}
			max = Math.max(max, dp);
		}
		return max;
	}
}
