package com.fb.graph;

import java.util.HashMap;
import java.util.HashSet;

import com.yahoo.algos.MyServer;
import com.yahoo.algos.Stack;

public class StronglyConnected<T> {

	/**
	 * a-->b-->c-->d  is NOT strongly connected since if i start from c i cant reach all verticies
	 * ways:
	 *  (a) DFS from all veriticies and see you visited all verticies from all O(V*(V+E))
	 *  (b) Good way is Do DFS from a vertex if it does NOT visit all vertex return false; else continue to step (c)
	 *  (c) Reverse the graph and do dfs from same vertex if it visits all vertex graph is strongly connected.
	 *  
	 * 
	 * If graph has PATH between u->v and PATH between v->u then u & u are strongly connected. 
	 * Equivalence also true if u v strongly connected and v-w strongly connected than u and w are strongly connected.
	 * 
	 * To find strongly connected components
	 * 
	 * (a) Do DFS of Graph and collect the reverse post order (build stack) Topological sort
	 * (b) Reverse the Graph and on reversed graph vist vertices poping from stack above in other words
	 * (b) Use elements popped from stack (reverse post order) and do DFS all DFS graphs are Strongly connected
	 * 
	 * NOTE
	 * ->Assuming all Strongly connected components are one node then graph we get is DAG ( removed cycle)
	 * ->Strongly components in G is same as reverse of Graph G (G^R). Since we need edges in both directions
	 * 
	 * Removing a node doesnt cause lot of isssue, like Social graph or network where some nodes are strongly connected.
	 */
	
	private final DirectedGraph<T> graph;
	private DirectedGraph<T> revGraph;
	private final Stack<T> postOrder;
	private HashSet<T> visitedNodes = new HashSet<T>();
	private HashMap<Integer,HashSet<T>> stronglyConnectedComponents = new HashMap<Integer, HashSet<T>>();
	
	public StronglyConnected(DirectedGraph<T> graph){
		this.graph = graph;
		postOrder = new Stack<T>(graph.size());
	}
	
	public void stronglyConnected(){
		//Run through all vertexes and do DFS to create the reverse post order
		revGraph = graph.reverseGraph();
		
		
		for(T node: graph){
			System.out.println(node+" "+ graph.getAdjacentNodes(node));
			System.out.println(node+" "+ revGraph.getAdjacentNodes(node));
			//run dfs on revGraph for all nodes
			if(!visitedNodes.contains(node)){
				dfs(node);
			}
		}
		//Now run DFS on post Graph reverse order to get Strongly connected components
		int count=0;
		visitedNodes = new HashSet<T>();
		while(!postOrder.isEmpty()){
			T node = postOrder.pop();
			if(!visitedNodes.contains(node)){
				stronglyConnectedComponents.put(count, new HashSet<T>());
				dfs(node, count++);
			}
		}
		System.out.println(stronglyConnectedComponents);
	}
	
	public void dfs(T node, int count){
		visitedNodes.add(node);stronglyConnectedComponents.get(count).add(node);
		for(T leaf : revGraph.getAdjacentNodes(node)){
			if(!visitedNodes.contains(leaf)){
				dfs(leaf, count);
			}
		}
	}
	
	public void dfs(T node){
		visitedNodes.add(node);
		for(T leaf : graph.getAdjacentNodes(node)){
			if(!visitedNodes.contains(leaf)){
				dfs(leaf);
			}
		}
		postOrder.push(node);
	}
	
	public static void main(String[] args) {
		DirectedGraph<GraphNode> dg = new DirectedGraph<GraphNode>();
		GraphNode zero = new GraphNode("0");GraphNode one = new GraphNode("1");GraphNode two = new GraphNode("2");GraphNode three = new GraphNode("3");GraphNode four = new GraphNode("4");
		GraphNode five = new GraphNode("5");GraphNode six = new GraphNode("6");GraphNode seven = new GraphNode("7");GraphNode eight = new GraphNode("8");GraphNode nine = new GraphNode("9");
		GraphNode ten = new GraphNode("10");GraphNode eleven = new GraphNode("11");GraphNode twelve = new GraphNode("12");
		dg.addNode(zero);dg.addNode(one);dg.addNode(two);dg.addNode(three);dg.addNode(four);dg.addNode(five);dg.addNode(six);dg.addNode(seven);
		dg.addNode(eight);dg.addNode(nine);dg.addNode(ten);dg.addNode(eleven);dg.addNode(twelve);
		
//		dg.addEdge(zero, two);dg.addEdge(zero, six);
//		dg.addEdge(one,zero);
//		dg.addEdge(two, three);dg.addEdge(two, four);
//		dg.addEdge(three, four);dg.addEdge(three, two);
//		dg.addEdge(four, eleven);dg.addEdge(four, five);dg.addEdge(four,six);
//		dg.addEdge(five, zero);dg.addEdge(five, three);
//		dg.addEdge(six, eight);dg.addEdge(six, seven);
//		dg.addEdge(eight,six);
//		dg.addEdge(nine, six);dg.addEdge(nine, seven);dg.addEdge(nine, twelve);
//		dg.addEdge(ten,nine);
//		dg.addEdge(eleven, nine);
//		dg.addEdge(twelve, ten);dg.addEdge(twelve, eleven);
		
		dg.addEdge(zero, one);dg.addEdge(zero, five);
		dg.addEdge(two, three);dg.addEdge(two, zero);
		dg.addEdge(three, five);dg.addEdge(three, two);
		dg.addEdge(four, two);dg.addEdge(four, three);
		dg.addEdge(five, four);
		dg.addEdge(six, zero);dg.addEdge(six, four);dg.addEdge(six, eight);dg.addEdge(six, nine);
		dg.addEdge(seven,six);dg.addEdge(seven,nine);
		dg.addEdge(eight,six);
		dg.addEdge(nine, ten);dg.addEdge(nine, eleven);
		dg.addEdge(ten,twelve);
		dg.addEdge(eleven, twelve);dg.addEdge(eleven, four);
		dg.addEdge(twelve, nine);
		
		//Slide 58 3-5 Strong Components.mp3
		
		StronglyConnected<GraphNode> sc = new StronglyConnected<GraphNode>(dg);
		sc.stronglyConnected();
		
	}
}
