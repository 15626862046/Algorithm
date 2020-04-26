package com.bj.set;

import com.bj.list.LinkedList;
import com.bj.list.List;

public class ListSet<E> implements Set<E> {

	private List<E> list = new LinkedList<>();
	
	//数量
	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public void clear() {
		list.clear();
	}

	public boolean contains(E element) {
		return list.contains(element);
	}

	//去重
	@SuppressWarnings("static-access")
	public void add(E element) {
		int index=list.indexOf(element);
		if(index != list.ELEMENT_NOT_FOUND) {//存在就覆盖 去重
			list.set(index, element);
		}else {
			list.add(element);
		}
	}

	public void remove(E element) {
		int index=list.indexOf(element);
		if (index != List.ELEMENT_NOT_FOUND) {//如果存在就删除
			list.remove(index);
		}
		
	}

	public void traversal(Visitor<E> visitor) {
		if(visitor==null) return;
		int size = list.size();
		for(int i = 0 ; i<size;i++) {
			if (visitor.visit(list.get(i))) return;
		}
		
	}

}
