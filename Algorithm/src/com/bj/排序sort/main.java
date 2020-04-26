package com.bj.排序sort;

import java.util.Arrays;

import com.bj.工具类tools.*;
import com.bj.排序sort.*;
import com.bj.排序sort.cmp.*;
public class main {

	public static void main(String[] args) {
		
		
		Integer[] array =Integers.random(10000, 0, 10000);
		testSorts(array, 
				//new BubbleSort1(),
				//new BubbleSort2(),
				//new BubbleSort3(),
				//new SelectionSort<>(),
				new HeapSort<>(),
				//new InsertionSort3<>(),
				new MergeSort<>(),
				new QuickSort<>(),
				new ShellSort<>(),
				new CountingSort(),
				new RadixSort()
				);
	}
	
	static void testSorts(Integer[] array, Sort... sorts) {
		for (Sort sort : sorts) {
			Integer[] newArray = Integers.copy(array);
			sort.sort(newArray);
			Asserts.test(Integers.isAscOrder(newArray));
			//Integers.println(newArray);
		}
		Arrays.sort(sorts);
		for (Sort sort : sorts) {
			System.out.println(sort);
		}
	}

}
