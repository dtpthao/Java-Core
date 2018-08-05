package ua.nure.thao.Practice6.part5;

public class Part5 {
	
	public static void main(String[] args) {
		Tree<Integer> tree = new Tree<>();
		
		System.out.println(tree.add(7));
		System.out.println(tree.add(7));
				
		System.out.println("~~~~~~~");
		tree.add(new Integer[] {3, 1, 10, 2, 5, 4, 7, 8, 11, 9, 6, 0});
		tree.print();
				
		System.out.println("~~~~~~~");
		System.out.println(tree.remove(0));
		System.out.println(tree.remove(0));	
		System.out.println(tree.remove(9));	
		System.out.println(tree.remove(5));	
		System.out.println(tree.remove(11));	
		System.out.println(tree.remove(3));	
		System.out.println(tree.remove(7));	
		System.out.println(tree.remove(null));	

		System.out.println("~~~~~~~");
		tree.print();
	}

}
