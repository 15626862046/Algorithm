package com.bj.排序sort.cmp;

import com.bj.排序sort.Sort;

public class BubbleSort2<T extends Comparable<T>> extends Sort<T>{

	@Override
	protected void sort() {
		for(int end = array.length-1;end>0;end--) {
			boolean sorted = true;
			for(int begin=1;begin<=end;begin++) {
				if(cmp(begin, begin-1)<0) {
					swap(begin, begin-1);
					sorted = false;
				}
			}
			if(sorted) break;
			
		}
		
		
	}

	
}
