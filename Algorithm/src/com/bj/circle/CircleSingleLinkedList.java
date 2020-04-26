package com.bj.circle;

import com.bj.list.AbstractList;

public class CircleSingleLinkedList<E> extends AbstractList<E> {
		
	private Node<E> first;
	
	private static class Node<E>{
		E element;
		Node<E> next;
		public Node(E element, Node<E> next) {
			this.element = element;
			this.next = next;
		}
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(element).append("_").append(next.element);
			return sb.toString();
		}
	} 

	public void clear() {
		first=null;
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
		if(index==0) {//第一个添加元素
			//这里新建一个结点是因为first在node()方法中被引用，避免在取最后一个结点时引发错误。取不到值。
			Node<E> newFirst = new Node<>(element, first); 
			// 拿到最后一个节点
			Node<E> last = (size==0)?first:node(size-1);
			last.next=newFirst;
			first=newFirst;
		}else {
			Node<E> prve=node(index-1);
			prve.next=new Node<>(element,prve.next);
		}
		size++;
		
	}
	
	public E remove(int index) {
		rangeCheck(index);
		Node<E> node = first;
		if(index==0) {
			if (size == 1) {
				first = null;
			} else {
				Node<E> last = node(size - 1);
				first = first.next;
				last.next = first;
			}
		}else {
			Node<E> prev=node(index-1);
			node = prev.next;
			prev.next=node.next;	
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
		Node<E> node = first;
		for(int i=0;i<index;i++) {
			node=node.next;
		}
		return node;
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
