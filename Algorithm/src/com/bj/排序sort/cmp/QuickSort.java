package com.bj.排序sort.cmp;

import com.bj.排序sort.Sort;

public class QuickSort<T extends Comparable<T>> extends Sort<T> {

	@Override
	protected void sort() {
		sort(0,array.length);	
	}
	private void sort(int begin,int end) {
		//至少要有两个元素
		if(end - begin<2) return;
		//确定轴点元素位置
		int mid =pivotIndex(begin, end);
		//对子序列进行快排
		sort(begin,mid);
		sort(mid+1,end);
	}
	/**
	 * 构造出 [begin, end) 范围的轴点元素
	 * @return 轴点元素的最终位置
	 */
	private int pivotIndex(int begin, int end) {
		//随机选择一个元素跟begin位置进行交换,随机轴点
		swap(begin, begin+(int)(Math.random()*(end-begin)));
		//备份begin位置元素
		T privot= array[begin];
		//end指向最后一个元素
		end--;
		//双while与break是为了交替方向
		while (begin<end) {
			while(begin<end) {
				if(cmp(privot, array[end])<0) { // 右边元素 > 轴点元素
					end--;
				}else {// 右边元素 <= 轴点元素
					array[begin++] = array[end];
					break;
				}
			}
			while (begin<end) {
				if(cmp(privot, array[begin])>0) {// 左边元素 < 轴点元素
					begin++;
				}else {// 左边元素 >= 轴点元素
					array[end--] = array[begin];
					break;
				}
				
			}
			
		}
		// 将轴点元素放入最终的位置
		array[begin] = privot;
		// 返回轴点元素的位置
		return begin;
	}

}
