package com.bj.递归recursion;

public class ClimbStairs {

	int climbStairs1(int n) {
		if (n <= 2) return n;
		return climbStairs1(n - 1) + climbStairs1(n - 2);
	}
	
	int climbStairs2(int n) {
		if (n <= 2) return n;
		int first = 1;
		int second = 2;
		for(int i=3;i<=n;i++) {
			second = first+second;
			first = second -first;
		}
		return second;
	}
	
}
