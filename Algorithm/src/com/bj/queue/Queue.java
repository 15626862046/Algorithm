package com.bj.queue;

import com.bj.list.LinkedList;
import com.bj.list.List;

public class Queue<E> {
	
	private List<E> list = new LinkedList<E>();
	
	// 元素的数量
	public int size() {
		return list.size();
	}
	
	//清空
	public void clear() {
		list.clear();
	}

	// 是否为空
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	// 入队
	public void enQueue(E element) {
		list.add(element);
	};
	
	// 出队
	public E deQueue() {
		return list.remove(0);
	}
	
	public E front() {
		return list.get(0);
	}

}
