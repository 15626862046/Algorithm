package com.bj.动态规划;
/**
 * 最长上升子序列（最长递增子序列LIS)
 */
public class LIS {
	public static void main(String[] args) {
		System.out.println(lengthOfLIS(new int[] {10, 2, 2, 5, 1, 7, 101, 18}));
	}
	
	/**
	 * 最长上升子序列–二分搜索–思路
	 *（假设数组是nums，也就是最初的牌数组）
	 *top[i] 是第i个牌堆的牌顶，len是牌堆的数量，初始值为0
	 *遍历每一张牌num
	 *✓利用二分搜索找出num 最终要放入的牌堆位置index
	 *✓num 作为第index 个牌堆的牌顶，top[index] = num
	 *✓如果index 等于len，相当于新建一个牌堆，牌堆数量+1，也就是len++
	 */
	/**
	 * 牌顶
	 */
	static int lengthOfLIS(int[] nums) {
		if(nums == null || nums.length ==0) return 0;
		// 牌堆的数量
		int len = 0;
		// 牌顶数组
		int top[] = new int[nums.length];
		// 遍历所有的牌
		for(int num : nums) {
			int begin =0;
			int end = len;
			while(begin<end) {
				int mid = (begin + end ) >> 1;
			    if(num <= top[mid]) {
			    	end = mid;
			    }else {
			    	begin = mid + 1;
			    }
			}
			// 覆盖牌顶
			top[begin] = num;
			// 检查是否要新建一个牌堆
			if(begin == len) len++;
		}
		return len;
	}
	
	
	/**最长上升子序列–二分搜索–想法
	 * 把每个数字看做是一张扑克牌，从左到右按顺序处理每一个扑克牌
	 * 将它压在（从左边数过来）第一个牌顶≥ 它的牌堆上面
	 * 如果找不到牌顶≥ 它的牌堆，就在最右边新建一个牌堆，将它放入这个新牌堆中
	 * 当处理完所有牌，最终牌堆的数量就是最长上升子序列的长度
	 */
	/**
	 * 牌顶
	 */
	static int lengthOfLIS2(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		// 牌堆的数量
		int len = 0;
		// 牌顶数组
		int[] top = new int[nums.length];
		// 遍历所有的牌
		for (int num : nums) {
			int j = 0;
			while (j < len) {
				// 找到一个>=num的牌顶
				if (top[j] >= num) {
					top[j] = num;
					break;
				}
				// 牌顶 < num
				j++;
			}
			if (j == len) { // 新建一个牌堆
				len++;
				top[j] = num;
			}
		}
		return len;
	}

	
	
	/**
	 * 动态规划
	 * 	 * dp(i) 是以nums[i] 结尾的最长上升子序列的长度，i∈ [s0, nums.length) 
	 * 
	 * 遍历j∈ [0, i)
	 * 
	 * 当nums[i] > nums[j]
	 * ✓nums[i] 可以接在nums[j] 后面，形成一个比dp(j) 更长的上升子序列，长度为dp(j) + 1
	 * ✓dp(i) = max { dp(i), dp(j) + 1 }
	 * 
	 * 当nums[i] ≤ nums[j]
	 * ✓nums[i] 不能接在nums[j] 后面，跳过此次遍历（continue）
	 * 
	 * 状态的初始值dp(0) = 1所有的dp(i) 默认都初始化为1
	 * 
	 * 最长上升子序列的长度是所有dp(i) 中的最大值max { dp(i) }，i∈ [0, nums.length) 
	 */
	static int lengthOfLIS1(int[] nums) {
		if(nums == null || nums.length == 0) return 0;
		int[] dp = new int[nums.length];
		int max = dp[0] = 1;
		for (int i = 1; i < dp.length; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if(nums[i] <= nums[j]) continue;
				dp[i] = Math.max(dp[i], dp[j]+1);
			}
			max = Math.max(max, dp[i]);
		}
		return max;
	}
	
}
