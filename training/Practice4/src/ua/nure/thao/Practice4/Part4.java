package ua.nure.thao.Practice4;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Part4 implements Iterable<Object>{
	
	private static String input;
	private String regex;
	private Pattern pattern;
	private Matcher matcher;
	
	public void setInput(String input) {
		this.input = input;
	}

	public static void main(String[] args) {

		input = Demo.getInput("part4.txt");
		String s;
		Iterator<Object> it = new Part4().iterator();
		while ((s = (String) it.next()) != null) {
			s = s.replaceAll("\\n", "");
			System.out.println(s);
		}
	}

	public Iterator<Object> iterator() {
		return new IteratorImpl();
	}

	private class IteratorImpl implements Iterator<Object> {
		
		public IteratorImpl() {
			Part4.this.regex = "(?Um)([^\\.]*[\\.$])[ $]?";
			Part4.this.pattern = Pattern.compile(Part4.this.regex);
			Part4.this.matcher = Part4.this.pattern.matcher(Part4.input);
		}
		
		public boolean hasNext() {
			return Part4.this.matcher.find(); 
		}
	 
		public Object next() {
			if (this.hasNext()) {
				return Part4.this.matcher.group(1);
			}
			return null;
		}
	      
		public void remove() {
			throw new UnsupportedOperationException();
		}    	
	}



}
