package com.fb.graph;

import com.yahoo.algos.TreeNode;
/*
 * Replace nodes val with sum of all node vals less than yours. Lowest becomes max sum (sum of all) 
 * 
 *            50
           /      \
         30        70
        /   \      /  \
      20    40    60   80 

The above tree should be modified to following 

              260
           /      \
         330        150
        /   \       /  \
      350   300    210   80
 */
public class AddAllGreater<T> {

	Integer count=0;
	private void myOrder(TreeNode<Integer> node ){
		if(node==null) return;
		myOrder(node.getRight());
		
		System.out.print(node+" "); //newTreeNode; 
		count+=node.getKey();
		node.setKey(count);
		
		myOrder(node.getLeft());
	}
	public static void main(String[] args) {
		/*
		 *             50
		 *          /    \
		 *        30       70
		 *      /  \      / 
		 *    20     40  60  
		*/
		
		TreeNode<Integer> root = new TreeNode<Integer>(50); 
		TreeNode<Integer> thirty = new TreeNode<Integer>(30); 
		TreeNode<Integer> seventy = new TreeNode<Integer>(70); 
		TreeNode<Integer> twenty = new TreeNode<Integer>(20); 
		TreeNode<Integer> fourty = new TreeNode<Integer>(40); 
		TreeNode<Integer> sixty = new TreeNode<Integer>(60); 
		TreeNode<Integer> eighty = new TreeNode<Integer>(80); 
		
		root.setLeft(thirty);root.setRight(seventy);
		thirty.setLeft(twenty);thirty.setRight(fourty);
		seventy.setLeft(sixty);seventy.setRight(eighty);
		
		AddAllGreater<Integer> tree = new AddAllGreater<Integer>();
		System.out.println("\n my");tree.myOrder(root);
		System.out.println("\n new "); new InOrderTraversal<Integer>().inOrder(root);
	}

}
