package ua.nure.thao.Practice6.part1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class WordContainer extends ArrayList<Word>{  

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean add(Word e) {
		return super.add(e);
	}
	
	public static void main(String[] args) {
		
		WordContainer wc = new WordContainer();
		Scanner sc = new Scanner(System.in);
		StringBuffer sb = new StringBuffer();
		String s = "";
		while (sc.hasNext()) {
			s = sc.next();
			System.out.print(s + " ");
			if ("stop".equals(s)) {
				break;
			}
			sb.append(s).append(" ");
		}
		
		sc.close();
		
		int frequency;
		Map<String, Integer> map = new HashMap<String, Integer>();
		String[] strings = sb.toString().split("[\\s,]+");
		for(String i : strings) {
			wc.add(new Word(i));
			if (map.containsKey(i) == true) {
				frequency = map.get(i);
				frequency++;
				map.put(i,frequency);
			}
			else map.put(i,1);
		}
		WordContainer newlist = new WordContainer();
		Set<Entry<String, Integer>> entries = map.entrySet();
		System.out.println();
		
        for (Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
            newlist.add(new Word(entry.getKey(), entry.getValue()));
        }
        System.out.println("Output: ");
		Collections.sort(newlist);
		for(Word w : newlist) {
			w.printWord();
		}
		
	}
	
}