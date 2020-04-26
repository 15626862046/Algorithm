package com.bj.tree;



import com.bj.printer.BinaryTrees;
import com.bj.tree.BinaryTree.Visitor;
import com.bj.trie.Asserts;

public class Main {
	
	public static void main(String[] args) {
		Integer data[] = new Integer[] {
				7, 4, 9, 2, 5, 8, 11
		};
		
		BST<Integer> bst = new BST<>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		BinaryTrees.println(bst);
		StringBuffer sBuffer = new StringBuffer();
		Visitor<Integer> visitor = new Visitor<Integer>() {	
			@Override
			public boolean visit(Integer element) {
				sBuffer.append(element).append(" ");
				return false;
			}
		};
		sBuffer.delete(0, sBuffer.length());
		bst.preorder(visitor);
		Asserts.test(sBuffer.toString().equals("7 4 2 5 9 8 11 "));
		
		sBuffer.delete(0, sBuffer.length());
		bst.inorder(visitor);
		Asserts.test(sBuffer.toString().equals("2 4 5 7 8 9 11 "));
		
		sBuffer.delete(0, sBuffer.length());
		bst.postorder(visitor);
		Asserts.test(sBuffer.toString().equals("2 5 4 8 11 9 7 "));
	}
}
