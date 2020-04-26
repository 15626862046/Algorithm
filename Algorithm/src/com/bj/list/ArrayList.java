package com.bj.list;


@SuppressWarnings("unchecked")
public class ArrayList<E> extends AbstractList<E> {
	
	/**
	 * 所有的元素
	 */
	private E[] elements;
	
	private static final int DEFAULT_CAPACITY = 10;
	

	public ArrayList(int capaticy) {	
		capaticy=(capaticy<DEFAULT_CAPACITY)? DEFAULT_CAPACITY : capaticy;
		elements = (E[]) new Object[capaticy];
	}
	
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * 清除所有元素
	 */
	public void clear() {	
		for(int i=0;i<size;i++) {
			elements[i]=null;
		}
		size=0;
	}


	/**
	 * 获取index位置的元素
	 * @param index
	 * @return
	 */
	public E get(int index) {
		rangeCheck(index);
		return elements[index];
	}
	
	/**
	 * 设置index位置的元素
	 * @param index
	 * @param element
	 * @return 原来的元素ֵ
	 */
	public E set(int index, E element) {
		rangeCheck(index);
		E oldelement=elements[index];
        elements[index]=element;
		return oldelement;
	}

	/**
	 * 在index位置插入一个元素
	 * @param index
	 * @param element
	 */
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		ensureCapacity(size + 1);
	    for(int i=size;i>index;i--) {
		elements[i]=elements[i-1];
	    }
	    elements[index]=element;
	    size++;
	}


	public E remove(int index) {
	
		E old = elements[index];
		for (int i=index+1;i<size;i++) {
			elements[i-1]=elements[i];
		}
		elements[--size] = null;
		trim();
		return old;
	}

	/**
	 * 查看元素的索引
	 * @param element
	 * @return
	 */
	public int indexOf(E element) {
		if(element==null) {
			for(int i=0;i<size;i++) {
				if(elements[i]==null)return i;
			}
		}else {
			for(int i=0;i<size;i++) {
				if(elements[i].equals(element))return i;
			}
		}
		return ELEMENT_NOT_FOUND;
	}
	/**
	 * 扩容
	 * 保证要有capacity的容量
	 * @param capacity
	 */
	private void ensureCapacity(int capacity) {
		int oldCapacity=elements.length;
		if(oldCapacity>=capacity) return ;
		int newCapacity=oldCapacity + (oldCapacity>>1);
		E[] newElements = (E[]) new Object[newCapacity];
		for(int i=0;i<elements.length;i++){
			newElements[i] = elements[i];
		}
		elements = newElements;
		System.out.println(oldCapacity + "扩容为" + newCapacity);
	}
	private void trim() {
		// 30
		int oldCapacity = elements.length;
		// 15
		int newCapacity = oldCapacity >> 1;
		if (size > (newCapacity) || oldCapacity <= DEFAULT_CAPACITY) return;
		
		// 剩余空间还很多
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
		
		System.out.println(oldCapacity + "缩容为" + newCapacity);
	}

	@Override
	public String toString() {
		StringBuffer string= new StringBuffer();
		string.append("size=").append(size).append(",[");
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				string.append(", ");
			}
		string.append(elements[i]);
		}
		string.append("]");
		return string.toString();
	}
	
	

}
