package com.bj.Single;

import com.bj.list.AbstractList;

public class SingleLinkedList2<E> extends AbstractList<E> {
		
	private Node<E> first;

	
	public SingleLinkedList2() {
		first = new Node<>(null, null);
	}
	
	private static class Node<E>{
		E element;
		Node<E> next;
		public Node(E element, Node<E> next) {
			this.element = element;
			this.next = next;
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
		//Node<E> prve=node(index-1);
		Node<E> prev = index == 0 ? first : node(index - 1);
		prev.next=new Node<>(element,prev.next);
		size++;
		
	}
	
	public E remove(int index) {
		rangeCheck(index);
		Node<E> node = first;
		Node<E> prev = index == 0 ? first : node(index - 1);
	    prev=node(index-1);
			node = prev.next;
			prev.next=node.next;	
		
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
		Node<E> node = first.next;
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
