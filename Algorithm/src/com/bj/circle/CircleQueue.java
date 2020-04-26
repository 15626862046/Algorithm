package com.bj.circle;

@SuppressWarnings("unchecked")
public class CircleQueue<E> {
	private int front;//队头下标
	private int size;
	private E[] elements;
	private static final int DEFAULT_CAPACITY = 10;
	
	public CircleQueue() {
		elements = (E[]) new Object[DEFAULT_CAPACITY];
	}
	
	// 元素的数量
	public int size() {
		return size;
	}

	// 是否为空
	public boolean isEmpty() {
		return size==0;
	}
	
	public void clear() {
		front=0;
		for(int i=0;i<size;i++) {
			elements[i]=null;
		}
		size=0;
	}
	
	private int index(int index) {
		index += front;
		//（front+index）%elements.length
		return index - (index >= elements.length ? elements.length : 0);
	}
		
	// 入队
	public void enQueue(E element) {
		ensureCapacity(size+1);
		elements[index(size)]=element;
		size++;
	};
		
	// 出队
	public E deQueue() {
		E frontElement = elements[front];
		elements[front] = null;
		front = index(1);
		size--;
		return frontElement;
	}
		
	public E front() {
		return elements[front];
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("capcacity=").append(elements.length)
		.append(" size=").append(size)
		.append(" front=").append(front)
		.append(", [");
		for (int i = 0; i < elements.length; i++) {
			if (i != 0) {
				string.append(", ");
			}
			
			string.append(elements[i]);
		}
		string.append("]");
		return string.toString();
	}
	
	/**
	 * 保证要有capacity的容量
	 * @param capacity
	 */
	private void ensureCapacity(int capacity) {
		int oldCapacity = elements.length;
		if (oldCapacity >= capacity) return;
		
		// 新容量为旧容量的1.5倍
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[index(i)];
		}
		elements = newElements;
		
		// 重置front
		front = 0;
	}

}
