package com.bj.贪心Greedy;
/**
 * 最优装载问题（加勒比海盗）
 */
import java.util.Arrays;

public class Pirate {
	public static void main(String[] args) {
		int[] weights = {3, 5, 4, 10, 7, 14, 2, 11};
		Arrays.sort(weights);
		int capacity = 30, weight = 0,count=0;
		for (int i = 0; i < weights.length && weight<capacity; i++) {
			int newWeight = weight + weights[i];
			if(newWeight <= capacity) {//小于装船，装满为止
				weight = newWeight;
				count++;
				System.out.println(weights[i]);
			}
		}
		System.out.println(count+"件");
	}
}
