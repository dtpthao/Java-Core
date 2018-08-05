package ua.nure.thao.Practice6.part4;

public class Part4 {
	
	final static int size = 10;
	
	static void printAdd(Graph graph, int node1, int node2) {
		System.out.print(node1 + " " + graph.map.get(node1) +" ====> " + node2);
		graph.add(node1, node2);
		System.out.println(" ====> " + graph.map.get(node1));
	}
	
	static void printRemove(Graph graph, int node1, int node2) {
		System.out.print(node1 + " " + graph.map.get(node1) +" ==x==> " + node2);
		System.out.print(": " + graph.remove(node1, node2));
		System.out.println(" ====> "+ graph.map.get(node1));
	}
	
	public static void main(String[] args) {
		
		Graph graph = new Graph(size);
		
		System.out.println("Add:");
		printAdd(graph, 1, 9);
		printAdd(graph, 7, 7);
		printAdd(graph, 5, 3);
		printAdd(graph, 3, 7);
		printAdd(graph, 2, 3);
		printAdd(graph, 6, 7);
		printAdd(graph, 0, 4);
		printAdd(graph, 6, 2);
		
		System.out.println("Remove:");
		printRemove(graph, 1, 9);
		printRemove(graph, 7, 2);
		printRemove(graph, 4, 6);
		printRemove(graph, 8, 8);
		printRemove(graph, 6, 7);
		printRemove(graph, 5, 8);
		
	}

}
