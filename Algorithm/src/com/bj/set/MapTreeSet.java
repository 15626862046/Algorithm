package com.bj.set;

import com.bj.map.Map;
import com.bj.map.TreeMap;

public class MapTreeSet<E> implements Set<E>{

	Map<E,Object> map = new TreeMap<>();

	public int size() {
		return map.size();
	}
	public boolean isEmpty() {
		return map.isEmpty();
	}
	public void clear() {
		map.clear();	
	}

	public boolean contains(E element) {
		return map.containsKey(element);
	}

	public void add(E element) {
		map.put(element, null);
		
	}

	public void remove(E element) {
		map.remove(element);
		
	}

	public void traversal(Visitor<E> visitor) {
		map.traversal(new Map.Visitor<E, Object>() {
			@Override
			public boolean visit(E key, Object value) {
				return visitor.visit(key);
			}
		});
		
	}

	
}
