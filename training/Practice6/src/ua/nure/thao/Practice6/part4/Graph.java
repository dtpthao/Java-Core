package ua.nure.thao.Practice6.part4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

	Map<Integer, List<Integer>> map;

	Graph(int number) {
		map = new HashMap<Integer, List<Integer>>();
		for (int i = 0; i < number; i++) {
			map.put(i, new ArrayList<Integer>());
		}
	}

	void add(int node1, int node2) {
		if (node1 != node2) {
			List<Integer> list = map.get(node1);
			if (!list.contains(node2)) {
				list.add(node2);
				map.put(node1, list);
				list = map.get(node2);
				list.add(node1);
				map.put(node2, list);
			}
		}
	}

	boolean remove(int node1, int node2) {

		if (node1 == node2) return false;
		List<Integer> list = map.get(node1);
		if (list.indexOf(node2) == -1) return false;
		list.remove(list.indexOf(node2));
		list = map.get(node2);
		list.remove(list.indexOf(node1));
		return true;
	}

}
