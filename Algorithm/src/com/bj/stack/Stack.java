package com.bj.stack;

import com.bj.list.ArrayList;
import com.bj.list.List;

public class Stack<E>  {
	
	List<E> list = new ArrayList<>(); 
	
	// 元素的数量
	public int size() {
		return list.size();
	}
	
	// 是否为空
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	// 入栈
	public void push(E element) {
		list.add(element);
	}
	
	// 出栈
	public E pop() {
		return list.remove(list.size()-1);
	}
	
	//换取栈顶元素
	public E top() {
		return list.get(list.size()-1);
	}

}
