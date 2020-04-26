package com.bj.circle;

import com.bj.list.AbstractList;

public class CircleLinkedList<E> extends AbstractList<E> {
		
	private Node<E> first;
	
	private Node<E> last;
	
	private static class Node<E>{
		E element;
		Node<E> next;
		Node<E> prev;
		public Node(Node<E> prev,E element, Node<E> next) {
			this.prev=prev;
			this.element = element;
			this.next = next;
		}	
	} 

	public void clear() {
		first=null;
		last=null;
		size=0;
	}

	public E get(int index) {
		rangeCheck(index);
		return node(index).element;
	}

	
	public E set(int index, E element) {
		rangeCheck(index);
		Node<E> node=node(index);
		E oldElement=node.element;
		node.element=element;
		return oldElement;
	}


	public void add(int index, E element) {
		rangeCheckForAdd(index);
		if(index == size) { // 往最后面添加元素
			Node<E> oldLast = last;
			last = new Node<>(oldLast, element, first);
			if (oldLast == null) { // 这是链表添加的第一个元素
				first = last;
				first.next = first;
				first.prev = first;
			} else {
				oldLast.next = last;
				first.prev = last;
			}
		}else {
			Node<E> next=node(index);
			Node<E> prev=next.prev;
			Node<E> node=new Node<>(prev,element,next);
			next.prev = node;
			prev.next = node;
			if (next == first) { // index == 0
				first = node;
			}
		}
		size++;
		
	}
	@Override
	public E remove(int index) {
		rangeCheck(index);
		return remove(node(index));
	}
	
	private E remove(Node<E> node) {
		if (size == 1) {
			first = null;
			last = null;
		} else {
			Node<E> prev = node.prev;
			Node<E> next = node.next;
			prev.next = next;
			next.prev = prev;		
			if (node == first) { // index == 0
				first = next;
			}	
			if (node == last) { // index == size - 1
				last = prev;
			}
		}
		
		size--;
		return node.element;
	}

	public int indexOf(E element) {	
		if(element==null) {
			Node<E> node = first;
			for (int i = 0; i < size; i++) {
			if (node.element == null) return i;
			node = node.next;
			}	
		}else {
			Node<E> node = first;
			for(int i=0;i<size;i++) {
				if(element.equals(node.element)) return i;
				node = node.next;
			}
		}
		return ELEMENT_NOT_FOUND;
	}
	
	/**
	 * 获取index位置对应的节点对象
	 * @param index
	 * @return
	 */
	private Node<E> node(int index){
		rangeCheck(index);
		//折半查找
		if(index<size>>1) {
			Node<E> node = first;
			for(int i=0;i<index;i++) {
				node=node.next;
			}
			return node;
		}else {
			Node<E> node = last;
			for(int i=size;i>index;i--) {
				node=node.prev;
			}
			return node;
		}
	}
	
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("size=").append(size).append(", [");
		Node<E> node = first;
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				string.append(", ");
			}
			string.append(node.element);	
			node = node.next;
		}
		string.append("]");
		return string.toString();
	}

}
