package com.bj.排序sort.cmp;

import com.bj.排序sort.Sort;

public class InsertionSort1 <T extends Comparable<T>> extends Sort<T>{

	@Override
	protected void sort() {
		for(int begin = 1;begin<array.length;begin++) {
			int cur = begin;
			while(cur>0 && cmp(cur, cur-1)<0) {
				swap(cur, cur-1);
				cur--;
			}
		}
		
	}

}
