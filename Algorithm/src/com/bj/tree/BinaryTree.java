package com.bj.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.bj.printer.BinaryTreeInfo;

public class BinaryTree <E> implements BinaryTreeInfo{
	protected int size;//元素的数量
	protected Node<E> root;//根节点
	//节点类
	protected static class Node<E> {
		E element;
		Node<E> left;
		Node<E> right;
		Node<E> parent;
		public Node(E element, Node<E> parent) {
			this.element = element;
			this.parent = parent;
		}
		
		public boolean isLeaf() {
			return left == null && right == null;
		}
		
		public boolean hasTwoChildren() {
			return left != null && right != null;
		}
		
		public boolean isLeftChild() {
			return parent != null && this == parent.left;
		}
		
		public boolean isRightChild() {
			return parent != null && this == parent.right;
		}
		
		public Node<E> sibling() {
			if (isLeftChild()) {
				return parent.right;
			}
			
			if (isRightChild()) {
				return parent.left;
			}
			
			return null;
		}
	}
	protected Node<E> createNode(E element, Node<E> parent) {
		return new Node<>(element, parent);
	}
	//元素的数量
	public int size() {
		return size;
	}
    //是否为空
	public boolean isEmpty() {
		return size == 0;
	}
    //清空
	public void clear() {
		root = null;
		size = 0;
	}
	/*
	 * 前序遍历
	public void preorderTraversal() {
		preorderTraversal(root);
	}
	private void preorderTraversal(Node<E> node) {
		if(node ==null) return;
		System.out.println(node.element);
		preorderTraversal(node.left);
		preorderTraversal(node.right);
		
	} 
     * 层序遍历 
	public void levelOrderTraversal() {
		if(root==null) return;
	    Queue<Node<E>> queue = new LinkedList<>();
	    queue.offer(root);
		while(!queue.isEmpty()) {
			Node<E> node=queue.poll();
			if(node.left!=null) {
				queue.offer(node.left);
			}
			if(node.right!=null) {
				queue.offer(node.right);
			}
		}
	}
	*/

	//遍历接口 防止将遍历写死  提供遍历结果给外界  允许外界自由使用
	public static interface Visitor<E> {
		boolean visit(E element);//外界需要实现接口 内部类
	}
	/*递归
	public void preorder(Visitor<E> visitor) {
		preorder(root, visitor);
	}
	private void preorder(Node<E> node, Visitor<E> visitor) {
		if(node==null || visitor ==null) return;
		visitor.visit(node.element);
		preorder(node.left,visitor);
		preorder(node.right, visitor);
	}
	*/
	//迭代1
	public void preorder1(Visitor<E> visitor) {
		if(root == null || visitor ==null) return;
		Node<E> node = root;
		Stack<Node<E>> stack = new Stack<>();
		while(true) {
			if(node !=null) {
				//访问node节点
				if(visitor.visit(node.element)) return;
				//将右子节入栈
				if(node.right!=null) {
					stack.push(node.right);
				}
				//向左走
				node = node.left;
			}else if(stack.isEmpty()) {
				return;
			}else {
				node = stack.pop();
			}
		}
	}
	//迭代2
	public void preorder(Visitor<E> visitor) {
		if(root == null || visitor ==null) return;
		Stack<Node<E>> stack = new Stack<>();
		stack.push(root);
		while(!stack.isEmpty()) {
			Node<E> node=stack.pop();
			visitor.visit(node.element);
			if(node.right!=null) {
				stack.push(node.right);
			}
			if(node.left!=null) {
				stack.push(node.left);
			}
		}
	}
	/*递归
	public void inorder(Visitor<E> visitor) {
		inorder(root, visitor);
	}
	private void inorder(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor == null) return;
		inorder(node.left, visitor);
		visitor.visit(node.element);
		inorder(node.right, visitor);
	}
	*/
	
	public void inorder(Visitor<E> visitor) {
		if(root == null || visitor ==null) return;
		Node<E> node = root;
		Stack<Node<E>> stack = new Stack<>();
		
		while(true) {
			if(node!=null) {
				stack.push(node);//将根节点或者左节点入栈
				node = node.left;
			}else if(stack.isEmpty()) {
				return;
			}else {
				node = stack.pop();
				//访问node节点
				if(visitor.visit(node.element)) return;
				//让右节点进行中序遍历
				node = node.right;
			}
		}
	}
	/*递归
	public void postorder(Visitor<E> visitor) {
		postorder(root, visitor);
	}
	private void postorder(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor == null) return;
		postorder(node.left, visitor);
		postorder(node.right, visitor);
		visitor.visit(node.element);
	}
	*/
	public void postorder(Visitor<E> visitor) {
		if(root == null || visitor ==null) return;
		Node<E> prev= null;
		Stack<Node<E>> stack = new Stack<>();
		stack.push(root);
		while(!stack.isEmpty()) {
			Node<E> top = stack.peek();
			if(top.isLeaf() || (prev !=null && prev.parent==top)) {
				prev = stack.pop();
				//访问节点
				if(visitor.visit(prev.element)) return;
			}else {
				if(top.right!=null) {
					stack.push(top.right);
				}
				if(top.left !=null) {
					stack.push(top.left);
				}
			}
		}
		
	}
	
	public void levelOrder(Visitor<E> visitor) {
		if (root == null || visitor == null) return;
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		while(!queue.isEmpty()) {
			Node<E> node=queue.poll();
			visitor.visit(node.element);
			if(node.left!=null) {
				queue.offer(node.left);
			}
			if(node.right!=null) {
				queue.offer(node.right);
			}
		}
	}
	//前驱节点
	protected Node<E> predecessor(Node<E> node) {
		if(node==null) return null;
		// 前驱节点在左子树当中（left.right.right.right....）
		Node<E> p = node.left;
		if(p!=null) {
			while(p.right!=null) {
				p=p.right;
			}
			return p;
		}
		// 从父节点、祖父节点中寻找前驱节点  node在parent右子树中
		while(node == node.parent.left && node.parent!=null) {
			node=node.parent;
		}
		// node.parent == null&&node == node.parent.right
		return node.parent;
	}
	//后继节点
	protected Node<E> successor(Node<E> node) {
        if (node == null) return null;
		// 后继节点在右子树当中（right.left.left.left....）
		Node<E> p = node.right;
		if (p != null) {
			while (p.left != null) {
				p = p.left;
			}
			return p;
		}
		// 从父节点、祖父节点中寻找后继节点
		while (node.parent != null && node == node.parent.right) {
			node = node.parent;
		}
		return node.parent;
	}
	//高度  迭代 层序遍历
	public int height() {
		if(root==null) return 0;
		// 树的高度
		int height = 0;
		// 存储着每一层的元素数量
		int levelSize = 1;
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			levelSize--;
			if (node.left != null) {
				queue.offer(node.left);
			}
			
			if (node.right != null) {
				queue.offer(node.right);
			}
			if (levelSize == 0) { // 意味着即将要访问下一层
				levelSize = queue.size();//下一层队列中元素的数量
				height++;
			}
		}
		return height;
	}
	//树的高度 
	public int height2() {
		return height(root);
	}
	//递归
	private int height(Node<E> node) {
		if (node == null) return 0;
		return 1+Math.max(height(node.left),  height(node.right));
	}
	//是否为完全二叉树
	public boolean isComplete() {
		if(root==null) return false;
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		boolean leaf = false;
		while(!queue.isEmpty()) {
			Node<E> node=queue.poll();
			if (leaf && !node.isLeaf()) return false;
			if(node.hasTwoChildren()) {
				queue.offer(node.left);
				queue.offer(node.right);
			}else if(node.left==null && node.right!=null) {
				return false;
			}else {// 后面遍历的节点都必须是叶子节点
				leaf = true;	
			}
		}
		return true;
	}
	//自定义打印二叉树
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toString(root, sb, "");
		return sb.toString();
	}
	
	private void toString(Node<E> node, StringBuilder sb, String prefix) {
		if (node == null) return;
		toString(node.left, sb, prefix + "L---");
		sb.append(prefix).append(node.element).append("\n");
		toString(node.right, sb, prefix + "R---");
	}
	
	//打印二叉树
	@Override
	public Object root() {
		return root;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object left(Object node) {
		return ((Node<E>)node).left;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object right(Object node) {
		return ((Node<E>)node).right;
	}

	@Override
	public Object string(Object node) {
		@SuppressWarnings("unchecked")
		Node<E> myNode = (Node<E>)node;
		String parentString = "null";
		if (myNode.parent != null) {
			parentString = myNode.parent.element.toString();
		}
		return myNode.element + "_p(" + parentString + ")";
	}
}
