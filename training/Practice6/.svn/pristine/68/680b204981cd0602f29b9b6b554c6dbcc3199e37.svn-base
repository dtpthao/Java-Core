package ua.nure.thao.Practice6.part6;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class Part6 {
	
	public static String getInput(String fileName){
		StringBuilder sb = new StringBuilder();
		try{
			Scanner scanner = new Scanner(new File(fileName),"UTF-8");
			while(scanner.hasNextLine()){
				sb.append(scanner.nextLine()).append(System.lineSeparator());
			}
			return sb.toString().trim();
		} catch (IOException ex) {}
		return sb.toString();
	}
	
	private static SortedSet<Map.Entry<Integer, Integer>> 
						SortedSetByValue(Map<Integer, Integer> map) {
		
		SortedSet<Map.Entry<Integer, Integer>> sortedset = new TreeSet<Map.Entry<Integer, Integer>>(
	            new Comparator<Map.Entry<Integer, Integer>>() {
	                @Override
	                public int compare(Map.Entry<Integer, Integer> e1,
	                        Map.Entry<Integer, Integer> e2) {
	                		int value = e2.getValue().compareTo(e1.getValue());
	                		if (value != 0) return value;
	                    return e1.getKey().compareTo(e2.getKey());
	                }
	            });
		
		sortedset.addAll(map.entrySet());
		return sortedset;
	}
	
	public static void frequency(String input) {
		
		String[] strings = input.toString().split("[\\s,]+");
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		List<String> sList = new ArrayList<String>();
		int freq;
		for(int i = 0; i < strings.length; i++) {
			if (sList.contains(strings[i])) continue;
			sList.add(strings[i]);
			freq = 0;
			int j = input.indexOf(strings[i]);
			while (j != -1) {
				freq++;
				j = input.indexOf(strings[i], j + 1);
			}
			map.put(i, freq);
		}
		SortedSet<Map.Entry<Integer, Integer>> sortedset = SortedSetByValue(map);
		
		Map<String, Integer> treeMap = new TreeMap<String, Integer>(
				new Comparator<String>() {
                    @Override
                    public int compare(String s1, String s2) {
                        return s2.compareTo(s1);
                    }
                });
		int i = 0;
		for(Entry<Integer, Integer> ent : sortedset) {
			treeMap.put(strings[ent.getKey()], ent.getValue());
			if (i++ == 2) break;
		}
		for(Entry<String, Integer> ent : treeMap.entrySet()) {
			System.out.println(ent.getKey() + " : " + ent.getValue());
		}
	}
	
	public static void length(String input) {
		
		String[] strings = input.toString().split("[\\s,]+");
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
		List<String> list = new ArrayList<String>();
		for(int i = 0; i< strings.length; i++) {
			if (list.contains(strings[i])) continue;
			list.add(strings[i]);
			map.put(i, strings[i].length());
		}
		
		SortedSet<Map.Entry<Integer, Integer>> sortedset = SortedSetByValue(map);
		
		int i = 0;
		for(Entry<Integer, Integer> ent : sortedset) {
			System.out.println(strings[ent.getKey()] + " : " + ent.getValue());
			if (i == 2) break;
			i++;
		}
	}
	
	public static void duplicates(String input) {
		
		String[] strings = input.toString().split("[\\s,]+");
		List<String> sList = new ArrayList<String>();
		for(String s : strings) {
			if (sList.contains(s)) continue;
			
			int i = input.indexOf(s);
			i = input.indexOf(s, i + 1); 
			if (i != -1) sList.add(s);
			if (sList.size() == 3) break;
		}
		for(String s : sList) {
			String reverse = new StringBuffer(s.toUpperCase()).reverse().toString();
			System.out.println(reverse);
		}
	}
	
	public static void main(String[] args) {
		
		String s = args[1];
		String method = args[3];
		String input = Part6.getInput(s);
		
		System.out.println("=====" + method);
		if (method == "frequency") {
			Part6.frequency(input);
		}
		else if (method == "length") {
			Part6.length(input);
		}
		else Part6.duplicates(input);
		
		s = Part6.getInput("txt.txt");
	}
}
