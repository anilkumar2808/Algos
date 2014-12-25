package com.fb.graph;

/*
 * http://www.geeksforgeeks.org/618/
 * DFS types Pre/post/in order traversal
 * pre: To take copy of tree.  sorted order. Polix notation
 * POST: To delete the tree.
 */
import com.yahoo.algos.TreeNode;

public class InOrderTraversal<T>{

	private void inOrder(TreeNode<T> node){
		if(node==null) return;
		inOrder(node.getLeft());
		System.out.print(node+" ");
		inOrder(node.getRight());
	}
	
	private void preOrder(TreeNode<T> node){
		if(node==null) return;
		System.out.print(node+" ");
		preOrder(node.getLeft());
		preOrder(node.getRight());
	}
	
	private void postOrder(TreeNode<T> node){
		if(node==null) return;
		postOrder(node.getLeft());
		postOrder(node.getRight());
		System.out.print(node+" ");
	}
	
	public static void main(String[] args) {
		/*
		 *             0
		 *          /    \
		 *        5       6
		 *      /  \      / 
		 *    3     4    7
		*/
		
		TreeNode<Integer> root = new TreeNode<Integer>(0); 
		TreeNode<Integer> five = new TreeNode<Integer>(5); 
		TreeNode<Integer> six = new TreeNode<Integer>(6); 
		TreeNode<Integer> three = new TreeNode<Integer>(3); 
		TreeNode<Integer> four = new TreeNode<Integer>(4); 
		TreeNode<Integer> seven = new TreeNode<Integer>(7); 
		
		root.setLeft(five);root.setRight(six);
		five.setLeft(three);five.setRight(four);
		six.setLeft(seven);
		
		InOrderTraversal<Integer> tree = new InOrderTraversal<Integer>();
		System.out.println( "\nIN ORDER");tree.inOrder(root);
		System.out.println( "\nPOST ORDER");tree.postOrder(root);
		System.out.println( "\nPRE ORDERR");tree.preOrder(root);
		
	}
}
