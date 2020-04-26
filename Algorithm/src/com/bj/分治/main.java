package com.bj.分治;
/**
 * 最大连续子序列的和
 * @author hee
 *
 */
public class main {

	public static void main(String[] args) {
		int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4 };
		System.out.println(maxSubArray(nums));
		}
		
		static int maxSubArray(int[] nums) {
			if (nums == null || nums.length == 0) return 0;
			return maxSubArray(nums, 0, nums.length);
		}
		
		/**
		 * 求解[begin, end)中最大连续子序列的和
		 * [i , j) 存在于[begin , mid) 中，同时S[i , j)也是[begin , mid) 的最大连续子序列和
		 * [i , j) 存在于[mid , end) 中，同时S[i , j)也是[mid , end) 的最大连续子序列和
		 *[i , j) 一部分存在于[begin , mid) 中，另一部分存在于[mid , end) 中
		 *✓[i , j) = [i , mid) + [mid, j)
		 *✓S[i , mid) = max { S[k , mid) }，begin≤ k ＜mid
		 *✓S[mid, j) = max { S[mid, k) }，mid＜k ≤ end
		 */
		static int maxSubArray(int[] nums, int begin, int end) {
			/*
			if (end - begin < 2) return nums[begin];
			int leftmax = Integer.MIN_VALUE;
			int mid = (begin + end) >> 1;
			int leftSum = 0;
			for (int i = mid - 1; i >= begin; i--) {
				leftSum += nums[i];
				leftmax = Math.max(leftmax, leftSum);
			}
			
			int rightmax = Integer.MIN_VALUE;
			int rightSum = 0;
			for (int i = mid; i < end; i++) {
				rightSum += nums[i];
				rightmax = Math.max(rightmax, rightSum);
			}
			*/
			
			if (end - begin < 2) return nums[begin];
		    int mid = (begin + end) >>1;
			int leftmax = nums[mid -1];
			int leftsum = leftmax;
			for(int i = mid-2;i>=begin;i--) {
				leftsum +=nums[i];
				leftmax = Math.max(leftmax, leftsum);
			}
			int rightmax = nums[mid];
			int rightsum = rightmax;
			for (int i = mid +1 ; i < end; i++) {
				rightsum +=nums[i];
				rightmax = Math.max(rightmax, rightsum);
			}
			
			return Math.max(leftmax+rightmax, Math.max(maxSubArray(nums,begin,mid), maxSubArray(nums,mid,end)));
		}
	
	
	
	/*
	 * 暴力出奇迹优化
	 * 重复利用前面计算过的结果
	 */
	static int maxSubarray2(int[] nums) {
		if(nums == null || nums.length ==0) return 0;
		int max = Integer.MIN_VALUE;
		for(int begin = 0;begin<nums.length;begin++) {
			int sum = 0;// sum是[begin, end]的和
			for (int end = begin; end < nums.length; end++) {
				sum +=nums[end];
				max=Math.max(max, sum);
			}
		}
		return max;
	}
		
	/*
	 * 求解[begin, end)中最大连续子序列的和
	 * 暴力出奇迹
	 * 穷举出所有可能的连续子序列，并计算出它们的和，最后取它们中的最大值
	 */
	static int maxSubarray1(int[] nums) {
		if(nums == null || nums.length ==0) return 0;
		int max = Integer.MIN_VALUE;
		for(int begin = 0;begin<nums.length;begin++) {
			for (int end = begin; end < nums.length; end++) {
				int sum = 0;// sum是[begin, end]的和
				for(int i=begin;i<=end;i++) {
					sum +=nums[i];
				}
				max=Math.max(max, sum);
			}
		}
		return max;
	}

}
