package ua.nure.thao.Practice6.part2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class Part2 {
	
	static final int len = 50000;
	static final int range = 4;
	
	public static long removeIndex(List<Integer> list) {
		
		long t = System.currentTimeMillis();
		int index = range - 1;
		while (list.size() > 1) {
			list.remove(index);
			index += range - 1;
			while (index >= list.size()) 
				index -= list.size();
		}
		return System.currentTimeMillis() - t;
	}
	
	public static long removeIterator(List<Integer> list) {
		
		long t = System.currentTimeMillis();
		ListIterator<Integer> listItr = list.listIterator();
		while (list.size() > 1) {
			for(int i = 0; i < range; i++) {
				if (!listItr.hasNext()) {
					listItr = list.listIterator();
				}
				listItr.next();
			}
			listItr.remove();
		}
		return System.currentTimeMillis() - t;
	}
	
	public static void main(String[] args) {
		
		List<Integer> array = new ArrayList<Integer>();
		List<Integer> link = new LinkedList<Integer>();
		List<Integer> array2 = new ArrayList<Integer>();
		List<Integer> link2 = new LinkedList<Integer>();
		Random rd = new Random();
		int x;
		for(int i = 0; i < len; i++) {
			x = rd.nextInt(10000);
			array.add(x);
			link.add(x);
			array2.add(x);
			link2.add(x);
		}
	
		System.out.println("ArrayList#Index: " + removeIndex(array) + " ms");
		System.out.println("LinkedList#Index: " + removeIndex(link) + " ms");
		
		System.out.println("ArrayList#Iterator: " + removeIterator(array2)+ " ms");
		System.out.println("LinkedList#Iterator: " + removeIterator(link2)+ " ms");
	}

}
