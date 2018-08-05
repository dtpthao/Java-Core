package ua.nure.thao.Practice6.part5;

public class Tree<E extends Comparable<E>> {
	
	private static final String INDENT = "   ";

	private Node<E> root = null;
	
	public boolean remove(E element) {	
		if (element == null) return false;
		Node<E> node = root;
		Node<E> parent = new Node<E>(null,null,null);
		while (!element.equals(node.cur)) {
			parent = node;
			node = (element.compareTo(node.cur) > 0) ? node.gt : node.lt;
			if (node == null) return false;
		}
		if (node.gt == null) {
			if (parent.cur.compareTo(node.cur) < 0) {	
				parent.gt = null;
				return true;
			}
			parent.lt = null;
			return true;
		}
		Node<E> newnode = node.gt;
		if (newnode.lt == null) {
			node.cur = newnode.cur;
			node.gt = newnode.gt;
			return true;
		}
		while (newnode.lt != null) {
			parent = newnode;
			newnode = newnode.lt;
		}
		node.cur = newnode.cur;
		parent.lt = null;
		return true;
	}
	
	public void add(E[] elements) {
		for(E i : elements) {
			this.add(i);
		}
	}
	
	public boolean add(E e) {
		if (this.root == null) {
			this.root = new Node<E>(e, null, null);
			return true;
		}
		else {
			Node<E> node = this.root;
			while (true) {
				if (e.equals(node.cur)) return false;
				
				while (e.compareTo(node.cur) > 0) {
					if (node.gt == null) {
						node.gt = new Node<E>(e, null, null);
						return true;
					}
					node = node.gt;
				}
				
				while (e.compareTo(node.cur) < 0) {
					if (node.lt == null) {
						node.lt = new Node<E>(e, null, null);
						return true;
					}
					node = node.lt;
				}
			}
		}
	}	

	public void print() {
		this.root.print("");
	}
	
	private static class Node<E> {
		
		private E cur;
		private Node<E> lt;
		private Node<E> gt;
		
		Node (E cur, Node<E> lt, Node<E> gt) {
			 this.cur = cur;
			 this.lt = lt;
			 this.gt = gt;
		}
		
		public void print(String s) {
			
			if (this.lt != null) {
				String s2 = s + INDENT;
				this.lt.print(s2);
			}
			
			System.out.println(s + this.cur);
			if (this.gt != null) {
				String s1 = s + INDENT;
				this.gt.print(s1);
			}
		}
		
	}	

}
