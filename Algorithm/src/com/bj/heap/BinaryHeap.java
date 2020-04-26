package com.bj.heap;

import java.util.Comparator;

import com.bj.printer.BinaryTreeInfo;

public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {

	private E[] elements;//数组
	private static final int DEFAULT_CAPACITY=10;//默认大小
	public BinaryHeap(E[] elements, Comparator<E> comparator)  {
		super(comparator);
		
		if (elements == null || elements.length == 0) {
			this.elements = (E[]) new Object[DEFAULT_CAPACITY];
		} else {
			size = elements.length;
			int capacity = Math.max(elements.length, DEFAULT_CAPACITY);
			this.elements = (E[]) new Object[capacity];
			for (int i = 0; i < elements.length; i++) {
				this.elements[i] = elements[i];
			}
			heapify();
		}
	}
	
	public BinaryHeap(E[] elements)  {
		this(elements, null);
	}
	
	public BinaryHeap(Comparator<E> comparator) {
		this(null, comparator);
	}
	
	public BinaryHeap() {
		this(null, null);
	}
	
	//清空
	public void clear() {
		for(int i = 0;i<size;i++) {
			elements[i] = null;
		}
		size=0;
	}

	@Override
	public void add(E element) {
		elementNotNullCheck(element);
		ensureCapacity(size + 1);
		elements[size++] = element;//先往数组最后添加元素
		siftUp(size-1);//再进行上滤
		
	}

	//堆是返回得到堆顶元素
	public E get() {
		emptyCheck();
		return elements[0];
	}

	@Override
	public E remove() {
        emptyCheck();
		
		int lastIndex = --size;
		E root = elements[0];
		elements[0] = elements[lastIndex];
		elements[lastIndex] = null;
		
		siftDown(0);
		return root;
	}

	// 删除堆顶元素的同时插入一个新元素
	public E replace(E element) {
		elementNotNullCheck(element);
		E root = null;
		if(size==0) {
			elements[0] = element;
			size++;
		}else {
			root = elements[0];
			elements[0] = element;
			siftDown(0);
		}
		return root;
	}
	
	/**
	 * 批量建堆
	 */
	private void heapify() {
		// 自上而下的上滤
//		for (int i = 1; i < size; i++) {
//			siftUp(i);
//		}
		// 自下而上的下滤
		for(int i =(size>>1)-1;i>=0;i--) {
			siftDown(i);
		}
	}
	
	
	/**
	 * 让index位置的元素下滤
	 * @param index
	 */
	private void siftDown(int index) {
		E element = elements[index];
		int half = size>>1;
		//第一个叶子节点的索引 == 非叶子节点的数量
		//index 《 第一个叶子节点的索引  因为叶子节点不用下滤了
		//所以必须保证index位置是非叶子节点
		while(index<half) {
			//index的节点只有两种情况，1.只有左节点   2.同时有左右节点
			//默认是左节点进行比较
			int childIndex = (index<<1)+1;//它的左子节点的索引为2i + 1
			E child = elements[childIndex];
			
			//右子节点
			int rightIndex = childIndex+1;
			
			//选出左右子节点最大的那个
			if(rightIndex<size && compare(elements[rightIndex], child)>0) {
				child = elements[childIndex = rightIndex];
			}
			//如果当前节点大于等于子节点 ，则退出
			if(compare(element, child)>=0) break;
			
			//否则将子节点存放在index位置
			elements[index] = child;
			//重新设置index
			index = childIndex;
		}
		elements[index] = element;
	}
	
	/**
	 * 让index位置的元素上滤
	 * @param index
	 */
	private void siftUp(int index) {
		E element = elements[index];//得到该index位置的元素
		while(index>0) {
			int parentIndex = (index-1)>>1;//父节点的索引为floor( (i –1) / 2 )
			E parent = elements[parentIndex];
			if(compare(element, parent)<=0) break;
			//将父元素存储在index位置
			elements[index] = parent;
			//重新赋值index
			index = parentIndex;
		}
		elements[index] = element;//将循环最后得到的index位置 放上element值，完成上滤
		
	}

	//扩容
	private void ensureCapacity(int capacity) {
		int oldCapacity = elements.length;
		if(oldCapacity>=capacity) return;
		
		//新容量为旧容量的1.5倍
		int newCapacity = oldCapacity + (oldCapacity>>1);
		E[] newElements = (E[]) new Object[newCapacity];
		for(int i =0;i<size;i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
	}
	
	//检查是否有值
	private void emptyCheck() {
		if (size == 0) {
			throw new IndexOutOfBoundsException("Heap is empty");
		}
	}
	//检查元素是否为空
	private void elementNotNullCheck(E element) {
		if (element == null) {
			throw new IllegalArgumentException("element must not be null");
		}
	}
	
	@Override
	public Object root() {
		return 0;
	}

	@Override
	public Object left(Object node) {
		int index = ((int)node << 1) + 1;
		return index >= size ? null : index;
	}

	@Override
	public Object right(Object node) {
		int index = ((int)node << 1) + 2;
		return index >= size ? null : index;
	}

	@Override
	public Object string(Object node) {
		return elements[(int)node];
	}
	

}
