package com.bj.动态规划;
/**
 * 0-1背包
 * @author hee
 *假设dp(i, j) 是最大承重为j、有前i 件物品可选时的最大总价值，i∈ [1, n]，j∈ [1, W]
 *dp(i, 0)、dp(0, j) 初始值均为0
 *如果j< weights[i–1]，那么dp(i, j) = dp(i–1, j)
 *如果j≥ weights[i–1]，那么dp(i, j) = max { dp(i–1, j), dp(i–1, j–weights[i–1]) + values[i–1] }
 */
public class Knapsack {
	public static void main(String[] args) {
		int[] values = {6, 3, 5, 4, 6};
		int[] weights = {2, 2, 6, 5, 4};
		int capacity = 10;
		System.out.println(maxValueExactly(values, weights, capacity));
	}
	static int maxValue1(int[] values, int[] weights, int capacity) {
		if(values == null || values.length == 0) return 0;
		if(weights == null || weights.length == 0) return 0;
		if(weights.length != values.length) return 0;
		if(capacity <=0) return 0;
		int[][] dp = new int [values.length+1][capacity+1];
		for (int i = 1; i <= values.length; i++) {
			for (int j = 1; j <= capacity; j++) {
				if(j<weights[i-1]) {
					dp[i][j] = dp[i-1][j];
				}else {
					dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-weights[i-1]]+values[i-1]);
				}
			}
		}
		return dp[values.length][capacity];
	}
	
	/**一维数组
	 * dp(i, j) 都是由dp(i–1, k) 推导出来的，也就是说，第i 行的数据是由它的上一行第i–1 行推导出来的
	 * 因此，可以使用一维数组来优化
	 * 另外，由于k ≤ j ，所以j 的遍历应该由大到小，否则导致数据错乱
	 */
	static int maxValue2(int[] values, int[] weights, int capacity) {
		if (values == null || values.length == 0) return 0;
		if (weights == null || weights.length == 0) return 0;
		if (values.length != weights.length || capacity <= 0) return 0;
		int dp[] = new int [capacity+1];
		for (int i = 1; i <= values.length; i++) {
			for (int j = capacity; j >= 1; j--) {
				if(j<weights[i-1]) continue;
				dp[j] = Math.max(dp[j], values[i-1]+dp[j-weights[i-1]]);
			}
		}
		return dp[capacity];
	}
	
	/**一维数组优化
	 * 观察二维数组表，得出结论：j 的下界可以从1 改为weights[i–1]
	 */
	static int maxValue(int[] values, int[] weights, int capacity) {
		if (values == null || values.length == 0) return 0;
		if (weights == null || weights.length == 0) return 0;
		if (values.length != weights.length || capacity <= 0) return 0;
		int[] dp = new int[capacity + 1];
		for (int i = 1; i <= values.length; i++) {
			for (int j = capacity; j >= weights[i-1]; j--) {
				dp[j] = Math.max(dp[j], values[i-1]+dp[j-weights[i-1]]);
			}
		}
		return dp[capacity];
	}
	
	/**恰好装满
	 * dp(i, j) 初始状态调整
	 * dp(i, 0) = 0，总重量恰好为0，最大总价值必然也为0
	 * dp(0, j) = –∞（负无穷），j ≥ 1，负数在这里代表无法恰好装满
	 * @return 如果返回-1，代表没法刚好凑到capacity这个容量
	 */
	static int maxValueExactly(int[] values, int[] weights, int capacity) {
		if (values == null || values.length == 0) return 0;
		if (weights == null || weights.length == 0) return 0;
		if (values.length != weights.length || capacity <= 0) return 0;
		int[] dp = new int[capacity + 1];
		for (int j = 1; j <= capacity; j++) {
			dp[j] = Integer.MIN_VALUE;
		}
		for (int i = 1; i <= values.length; i++) {
			for (int j = capacity; j >=weights[i - 1]; j--) {
				dp[j] = Math.max(dp[j], values[i-1]+ dp[j-weights[i-1]]);
			}
		}
		return dp[capacity] < 0 ? -1 : dp[capacity];
	}
}
