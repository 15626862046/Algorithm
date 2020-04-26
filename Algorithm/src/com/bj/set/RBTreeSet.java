package com.bj.set;

import java.util.Comparator;

import com.bj.tree.BinaryTree;
import com.bj.tree.RBTree;

public class RBTreeSet<E> implements Set<E>{

	private RBTree<E> tree;
	
	public RBTreeSet() {
		this(null);
	}
	public RBTreeSet(Comparator<E>Comparator){
		tree = new RBTree<>();
	}

	public int size() {
		return tree.size();
	}

	public boolean isEmpty() {
		return tree.isEmpty();
	}

	public void clear() {
		tree.clear();
	}

	public boolean contains(E element) {
		return tree.contains(element);
	}

	@Override
	public void add(E element) {
		tree.add(element);
	}

	@Override
	public void remove(E element) {
		tree.remove(element);
		
	}

	@Override
	public void traversal(Visitor<E> visitor) {
		tree.inorder(new BinaryTree.Visitor<E>() {
			@Override
			public boolean visit(E element) {
				return visitor.visit(element);
			}
		});
	}

}
