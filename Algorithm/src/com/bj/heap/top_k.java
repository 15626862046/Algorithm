package com.bj.heap;

import java.util.Comparator;

import com.bj.printer.BinaryTrees;

public class top_k {
	static void test4() {
		// 新建一个小顶堆
		BinaryHeap<Integer> heap = new BinaryHeap<>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		
		// 找出最大的前k个数
		int k = 3;
		Integer[] data = {51, 30, 39, 92, 74, 25, 16, 93, 
				91, 19, 54, 47, 73, 62, 76, 63, 35, 18, 
				90, 6, 65, 49, 3, 26, 61, 21, 48};
		for (int i = 0; i < data.length; i++) {
			if (heap.size() < k) { // 前k个数添加到小顶堆
				heap.add(data[i]); // logk
			} else if (data[i] > heap.get()) { // 如果是第k + 1个数，并且大于堆顶元素
				heap.replace(data[i]); // logk
			}
		}
		// O(nlogk)
		BinaryTrees.println(heap);
	}
}
